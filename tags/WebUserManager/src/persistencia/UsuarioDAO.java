package persistencia;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import modelo.Usuario;

/**
 * Session Bean implementation class UsuarioDAO
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioDAO implements UsuarioDAOLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceUnit(unitName = "WebUserManager")
	private EntityManagerFactory emf;
	
    public UsuarioDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void altaUsuario(Usuario u) {
		// TODO Auto-generated method stub
//		EntityManager em = Persistence.createEntityManagerFactory("WebUserManager").createEntityManager();
		EntityManager em = emf.createEntityManager();
		try{
			em.persist(u);
			em.close();
			
		}catch(Throwable ex){}
		
	}

}
