package persistencia;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Aplicacion;
import modelo.Permiso;
import modelo.Rol;
import modelo.Usuario;

/**
 * Session Bean implementation class RolDAO
 */
@Stateless
@LocalBean
public class RolDAO implements RolDAOLocal {


	@PersistenceContext(unitName="WebUserManager")
	private EntityManager em;
	
	
    public RolDAO() {
    }


	@Override
	public int altaRolAplicacion(Rol r, long appId) {
		try{
			
			em.persist(r);
			Aplicacion a = em.find(Aplicacion.class, appId);
			r.setAplicacion(a);
			a.getRoles().add(r);
			
		}catch(Exception ex){
			return 0;
		}
		
		return 1;
	}


	@Override
	public int agregarPermRol(Permiso p, long rolId) {
		try{
			
			em.persist(p);
			Rol r = em.find(Rol.class, rolId);
			r.getPerms().add(p);
			
		}catch(Exception ex){
			return 0;
		}
		
		return 1;
	}


	@Override
	public int asignarRolUsuario(long rolId, long userId, String nick, long appid, String rol) {

		try{
			
			Usuario u = em.find(Usuario.class, userId);
			Rol r = em.find(Rol.class, rolId);
			u.getRoles().add(r);
			
		}catch(Exception e){
			
			return 0;
		}
		
		return 1;
	}


	@Override
	public int quitarRolUsuario(long rolId, long userId, String nick, long appid, String rol) {
	
		try{
			
			Usuario u = em.find(Usuario.class, userId);
			u.quitarRolUsuario(rolId);
			
		}catch(Exception e){
			
			return 0;
		}
		return 1;
	}
    
    
    
    

}
