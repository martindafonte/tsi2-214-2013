package persistencia;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import modelo.Aplicacion;

/**
 * Session Bean implementation class AplicacionDAO
 */
@Stateless
@LocalBean
public class AplicacionDAO implements AplicacionDAOLocal {

	
	@PersistenceUnit(unitName = "WebUserManager")
	private EntityManagerFactory emf;
	
    /**
     * Default constructor. 
     */
    public AplicacionDAO() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public void altaApliacion(Aplicacion a) {
		// TODO Auto-generated method stub
		
		EntityManager em = emf.createEntityManager();
		try{
			em.persist(a);
			em.clear();
			em.close();
			
		}catch(Throwable ex){}
		
	}


}
