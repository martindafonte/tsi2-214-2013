package persistencia;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
//import javax.persistence.PersistenceUnit;

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
			em.persist(a);
			d.getLa().add(a);
			a.setD(d);
			
		}catch(Throwable ex){
			System.out.println("ERROR EN ALTA APLICACION!!!");
			
		}
		
	}


}
