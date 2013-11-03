package persistencia;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import presentacion.PedSesBean;
import modelo.Aplicacion;
import modelo.Canal;
import modelo.PedidoJson;
import modelo.PedidoMsj;
import modelo.PedidoPush;
import modelo.PedidoUser;
import modelo.Usuario;

/**
 * Session Bean implementation class PedidosDAO
 */
@Stateless
@LocalBean
public class PedidosDAO implements PedidosDAOLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(unitName="WebUserManager")
	EntityManager em;
	
    public PedidosDAO() {
    }

	@Override
	public void altaPedidoJSONAplicacion(long app, PedidoJson p) {
		try{
			em.persist(p);
			Aplicacion a = em.find(Aplicacion.class, app);
			List<PedidoJson> lp = a.getPedidosJson();
			lp.add(p);
			a.setPedidosJson(lp);
			em.flush();
			
		}catch(Throwable ex){
			System.out.println("ERROR EN ALTA PEDIDO JSON!!!");
			
		}
	}

	@Override
	public void altaPedidoPUSHAplicacion(long app, PedidoPush p) {
		try{
			em.persist(p);
			Aplicacion a = em.find(Aplicacion.class, app);
			List<PedidoPush> lp = a.getPedidosPush();
			lp.add(p);
			a.setPedidosPush(lp);
			em.flush();
			
		}catch(Throwable ex){
			System.out.println("ERROR EN ALTA PEDIDO PUSH!!!");
		}
	}

	@Override
	public void altaPedidoUMAplicacion(long app, PedidoUser p, String user) {
		try{
			Query q = em.createQuery("SELECT x FROM Usuario x WHERE x.nick = ?1 and aplicacion_id = ?2");
			q.setParameter(1, user).setParameter(2, app);
			Usuario u = (Usuario) q.getSingleResult();
			p.setUsuario(u);
			em.persist(p);
			Aplicacion a = em.find(Aplicacion.class, app);
			List<PedidoUser> lp = a.getPedidosUM();
			lp.add(p);
			a.setPedidosUM(lp);
			em.flush();
			
		}catch(Throwable ex){
			System.out.println("ERROR EN ALTA PEDIDO USER!!!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void altaPedidoMSJ(PedidoMsj p, String canId, long app) {
		
		try{
			Query q = em.createQuery("SELECT x FROM Canal x WHERE x.codigo = ?1 and app_id = ?2");
			q.setParameter(1, canId).setParameter(2, app);
			List<Canal> lc = q.getResultList();
			Canal c = lc.get(0);
			p.setCanal(c);
			em.persist(p);
			
		}catch(Throwable ex){
			System.out.println("ERROR EN ALTA PEDIDO MSJ!!!");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PedSesBean> getPedidos(long app) {
		try{
			Query q = em.createQuery("SELECT p.method, p.url, COUNT(p) FROM PedidosJson p WHERE aplicacion_id = ?1 GROUP BY p.url");
			q.setParameter(1, app);
			List<PedSesBean> lpj = q.getResultList();
			q = em.createQuery("SELECT p.method, p.url, COUNT(p) FROM PedidosPush p WHERE aplicacion_id = ?1 GROUP BY p.url");
			q.setParameter(1, app);
			List<PedSesBean> lpp = q.getResultList();
			q = em.createQuery("SELECT p.method, p.url, COUNT(p) FROM PedidosUser p WHERE aplicacion_id = ?1 GROUP BY p.url");
			q.setParameter(1, app);
			List<PedSesBean> lpu = q.getResultList();
			lpp.addAll(lpu);
			lpj.addAll(lpp);
			return lpj;
			
		}catch(Throwable ex){
			System.out.println("ERROR EN ALTA PEDIDO USER!!!");
		}
		return null;
	}
	
	@Override
	public int getMensajes(long app, String canId) {
		try{
			Query q = em.createQuery("SELECT COUNT(p) FROM PedidosMsj p WHERE aplicacion_id = ?1 and x.codigo = ?2");
			q.setParameter(1, app).setParameter(2, canId);
			int msj = (int) q.getSingleResult();
			return msj;
			
		}catch(Throwable ex){
			System.out.println("ERROR EN ALTA PEDIDO USER!!!");
		}
		return 0;
	}
}
