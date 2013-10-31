package persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import javax.persistence.Query;

import modelo.Aplicacion;
import modelo.Canal;
import modelo.Desarrollador;

/**
 * Session Bean implementation class AplicacionDAO
 */
@Stateless
@LocalBean
public class AplicacionDAO implements AplicacionDAOLocal {

	
	@PersistenceContext(unitName="WebUserManager")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public AplicacionDAO() {
    }


	@Override
	public void altaApliacion(Aplicacion a, Desarrollador d) {
		try{
			d.getLa().add(a);
			em.persist(a);
			a.setD(d);
			
		}catch(Throwable ex){
			System.out.println("ERROR EN ALTA APLICACION!!!");
			
		}
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Aplicacion> singleLoginAplicaciones() {
		Query q = em.createQuery("SELECT x FROM Aplicacion x WHERE x.singleLogin");
		return (List<Aplicacion>)q.getResultList();
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Aplicacion> getAplicaciones(Desarrollador d) {
		Query q = em.createQuery("SELECT x FROM Aplicacion x WHERE x.d = ?1");
		q.setParameter(1, d);
		return (List<Aplicacion>)q.getResultList();
		
	}


	@Override
	public long getIdJSON(Aplicacion a) {
		return a.getJSONID();
	}


	@Override
	public Aplicacion getAplicacion(long id) {
		Query q = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
		q.setParameter(1, id);
		@SuppressWarnings("unchecked")
		List<Aplicacion> la = q.getResultList();
		if (la.size() == 1){
		
			return la.iterator().next();
			
		}		
		return null;
	}


	@Override
	public void quitarCanalAplicacion(Canal c, Aplicacion a) {
		List<Canal> lc = a.getCanales();
		List<Canal> lcAux = new ArrayList<Canal>();
		Iterator<Canal> itlc = lc.iterator();
		while(itlc.hasNext()){
			Canal c2 = itlc.next();
			Canal cAux = new Canal();
			c2.setApp(c.getApp());
			if(! c2.getCodigo().equals(c.getCodigo())){
				lcAux.add(cAux);
			}
			
		}
		
		a.setCanales(lcAux);
		em.remove(c);
//		em.flush();
		
	}


	@Override
	public boolean agregarCanalAplicacion(Canal c, Aplicacion a) {
		boolean esta = false;
		List<Canal> lc = a.getCanales();
		Iterator<Canal>itlc = lc.iterator();
		while(itlc.hasNext()){
			Canal ca = itlc.next();
			if(ca.getCodigo().equals(c.getCodigo())){
				esta = true;
			}
			
		}
		
		if (!esta ){
			c.setApp(a);
			lc.add(c);
			a.setCanales(lc);
//			em.flush();
		}
//		em.persist(c);
		return !esta;
	}


	@Override
	public void cambiarSingleLogin(long id) {
		Aplicacion a = this.getAplicacion(id);
		a.setSingleLogin(!a.isSingleLogin());
		em.flush();
	}


	@Override
	public void cambiarSingleLogin(long id, boolean valor) {
		Aplicacion a = this.getAplicacion(id);
		a.setSingleLogin(valor);
		em.flush();
	}


	@Override
	public List<Canal> getCanales(Aplicacion a) {
		return a.getCanales();
	}


	@Override
	public boolean agregarCanalAplicacion(String codigo, long id) {
		Query q = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
		q.setParameter(1, id);
		@SuppressWarnings("rawtypes")
		List res = q.getResultList();
		if(( res != null)&&(res.size() == 0)){
		
			Aplicacion a = (Aplicacion)res.iterator().next();
			
			q = em.createQuery("SELECT x FROM Canal x WHERE x.codigo = ?1");
			q.setParameter(1, codigo);
			@SuppressWarnings("rawtypes")
			List res2 = q.getResultList();
			if(( res2 != null)&&(res2.size() == 0)){
			
				Canal c = (Canal)res.iterator().next();
				
				c.setApp(a);
				a.getCanales().add(c);
			}
			
		}
		em.flush();
		
		return false;
	}


	@Override
	public void quitarCanalAplicacion(String cod, long id) {
		Aplicacion a = this.getAplicacion(id);
		Canal c = a.quitarCanal(cod);
		em.remove(c);
		
		
	}

	
	

}
