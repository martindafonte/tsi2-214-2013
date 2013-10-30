package persistencia;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.Desarrollador;
import modelo.Usuario;
import persistencia.ConstantesPersistencia;

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
	
	@PersistenceContext(unitName="WebUserManager")
	private EntityManager em;
	
    public UsuarioDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int altaUsuario(Usuario u) {
		try{
			em.persist(u);
			return ConstantesPersistencia.Exito;
			
		}catch(Throwable ex){
			return ConstantesPersistencia.Error;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Usuario getUsuario(String nick, String pass) {
		try{
			Query q = em.createQuery("SELECT x FROM Usuario x WHERE x.nick = ?1 and pass = ?2");
			q.setParameter(1, nick).setParameter(2, pass);
			List<Usuario> us = q.getResultList();
			if (us.size() == 1 ){
				Usuario u = us.remove(0);
				return u;
				
			}
			
			
		}catch(Exception e){
			return null;
			
		}
		return null;
	}

	@Override
	public int altaDesarrollador(Desarrollador u) {
		// TODO Auto-generated method stub
		try{
			em.persist(u);
			return ConstantesPersistencia.Exito;
		}catch(Exception ex){
			return ConstantesPersistencia.Error;
		}
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Desarrollador getDesarrollador(String nick, String pass) {
		// TODO Auto-generated method stub

		try{
			Query q = em.createQuery("SELECT x FROM Desarrollador x WHERE x.nick = ?1 and pass = ?2");
			q.setParameter(1, nick).setParameter(2, pass);
			List<Desarrollador> us = q.getResultList();
			if (us.size() == 1 ){
				Desarrollador d = us.remove(0);
				return d;
				
			}
			
			
		}catch(Exception e){
			return null;
			
		}
		return null;
	}

}
