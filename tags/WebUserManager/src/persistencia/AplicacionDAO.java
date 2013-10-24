package persistencia;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



import javax.persistence.Query;

import modelo.Aplicacion;
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
        // TODO Auto-generated constructor stub
    }


	@Override
	public void altaApliacion(Aplicacion a, Desarrollador d) {
		// TODO Auto-generated method stub
		
		
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
		// TODO Auto-generated method stub
		
		Query q = em.createQuery("SELECT x FROM Aplicacion x WHERE x.singleLogin");
		return (List<Aplicacion>)q.getResultList();
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Aplicacion> getAplicaciones(Desarrollador d) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT x FROM Aplicacion x WHERE x.d = ?1");
		q.setParameter(1, d);
		return (List<Aplicacion>)q.getResultList();
		
	}


	@Override
	public long getIdJSON(Aplicacion a) {
		// TODO Auto-generated method stub
		return a.getJSONID();
	}


	@Override
	public Aplicacion getAplicacion(long id) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
		q.setParameter(1, id);
		@SuppressWarnings("unchecked")
		List<Aplicacion> la = q.getResultList();
		if (la.size() == 1){
		
			return la.iterator().next();
			
		}		
		return null;
	}

	
	

}
