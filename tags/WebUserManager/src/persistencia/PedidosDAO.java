package persistencia;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
			Usuario u = em.find(Usuario.class, user);
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

	@Override
	public void altaPedidoMSJ(PedidoMsj p, String canId) {
		
		try{
			Canal c = em.find(Canal.class, canId);
			p.setCanal(c);
			em.persist(p);
			
		}catch(Throwable ex){
			System.out.println("ERROR EN ALTA PEDIDO MSJ!!!");
		}
	}
}
