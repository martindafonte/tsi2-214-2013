package persistencia;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.Aplicacion;
import modelo.Canal;
import modelo.Registro;

/**
 * Session Bean implementation class CanalDAO
 */
@Stateless
@LocalBean
public class CanalDAO implements CanalDAOLocal {

    /**
     * Default constructor. 
     */
	@EJB
	private AplicacionDAOLocal al;
	
	
	@PersistenceContext(unitName="WebUserManager")
	private EntityManager em;
	
    public CanalDAO() {
    }

	@Override
	public void altaCanal(Canal c) {
		
		try{
			em.persist(c);
		}	catch(Throwable ex){
			System.out.println("ERROR EN ALTA CANAL!!!");
		
		}
	}

	@Override
	public void borrarCanal(Canal c) {
		List<Registro> lr = c.getRegistrados();
		Iterator<Registro> itlr = lr.iterator();
		while(itlr.hasNext()){
			Registro r = itlr.next();
			r.quitarCanal(c);
		}
		c.setApp(null);
	}

	@Override
	public void agregarRegistroCanal(Canal c, Registro r) {
		c.getRegistrados().add(r);
		r.getCanales().add(c);
		em.flush();
	}

	@Override
	public void quitarRegistroCanal(Canal c, Registro r) {
		r.quitarCanal(c);
		c.quitarRegistro(r);
		em.flush();

	}


	@Override
	public Aplicacion getAplicacionCanal(Canal c) {
		Query q = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
		q.setParameter(1, c.getApp().getId());
		@SuppressWarnings("unchecked")
		List<Aplicacion> l = q.getResultList();
		Iterator<Aplicacion> it = l.iterator();
		if(it.hasNext()){			
			return it.next();
		}
		return null;
		
	}

	@Override
	public Canal getCanal(String id) {
		Query q = em.createQuery("SELECT x FROM Canal x WHERE x.codigo = ?1");
		q.setParameter(1, id);
		@SuppressWarnings("unchecked")
		List<Canal> lc = q.getResultList();
		if(lc.size() == 1){
			return lc.iterator().next();
		}		
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean existeCanal(String cod) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT x FROM Canal x WHERE x.codigo = ?1");
		q.setParameter(1, cod);
		List l = q.getResultList();
		return ((l != null) && (l.size() > 0));
	}


    
    

}
