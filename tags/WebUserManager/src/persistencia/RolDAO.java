package persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import presentacion.PermSesBean;
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
			return 1;
		}catch(Exception ex){
			return 0;
		}
	}


	@Override
	public int agregarPermRol(Permiso p, long rolId) {
		try{
			
			em.persist(p);
			Rol r = em.find(Rol.class, rolId);
			Aplicacion a = r.getAplicacion();
			r.getPerms().add(p);
			a.getPermisos().add(p);
			return 1;
		}catch(Exception ex){
			return 0;
		}
	}


	@Override
	public int asignarRolUsuario(String nick, long appid, String rol) {

		
		try{
			Aplicacion a = em.find(Aplicacion.class, appid);
			if (a != null){
				Usuario u = a.getUsuario(nick);
				if( u != null){
					Rol r = a.getRol(rol);
					if( r != null){
						u.agregarRolUsuario(r);
						return 1;
					}
				}
			}
			return 0;
		}catch(Exception e){
			
			return 0;
		}
	}


	@Override
	public int quitarRolUsuario(String nick, long appid, String rol) {
	
		try{
			
			Aplicacion a = em.find(Aplicacion.class, appid);
			if (a != null){
				Usuario u = a.getUsuario(nick);
				if( u != null){
					Rol r = a.getRol(rol);
					if(r != null){
						u.quitarRolUsuario(r.getId());
						return 1;
					}
				}
			}
			return 0;
		}catch(Exception e){
			
			return 0;
		}
	}


	@Override
	public List<PermSesBean> getPermsHave(long rolid) {

		Rol r = em.find(Rol.class, rolid);
		if( r != null ){
			
			return r.getPermsHave();
			
		}
		
		return new ArrayList<PermSesBean>();
	}


	@Override
	public List<PermSesBean> getPermsDontHave(long rolid) {

		Rol r = em.find(Rol.class, rolid);
		if( r != null ){
			
			return r.getPermsDontHave();
			
		}
		
		return new ArrayList<PermSesBean>();
	}


	@Override
	public int agregarPermRol(long permId, long rolId) {
		// TODO Auto-generated method stub
		try{
			
			
			Rol r = em.find(Rol.class, rolId);
			Permiso p = em.find(Permiso.class, permId);
			r.agregarPermiso(p);
			
		}catch(Exception e){
			
			return 0;
		}
		
		return 1;
	}


	@Override
	public int quitarPermRol(Permiso p, long rolId) {
		
		try{
			
			
			Rol r = em.find(Rol.class, rolId);
			r.quitarPermiso(p);
			
		}catch(Exception e){
			
			return 0;
		}
		
		return 1;
	}


	@Override
	public int quitarPermRol(long permId, long rolId) {

		try{
			
			
			Rol r = em.find(Rol.class, rolId);
			Permiso p = em.find(Permiso.class, permId);
			r.quitarPermiso(p);
			
		}catch(Exception e){
			
			return 0;
		}
		
		return 1;
	}


	@Override
	public int agregarPermRol(String nombre, long rolId) {
		
		
		try{
			
			
			Rol r = em.find(Rol.class, rolId);
			Aplicacion a = r.getAplicacion();
			Permiso p = a.getPermiso(nombre);
			if(p != null){
				
				r.agregarPermiso(p);
			}
			
		}catch(Exception e){
			
			return 0;
		}
		
		return 1;
		
		
	}


	@Override
	public int quitarPermRol(String nombre, long rolId) {
		// TODO Auto-generated method stub
		try{
			
			
			Rol r = em.find(Rol.class, rolId);
			Aplicacion a = r.getAplicacion();
			Permiso p = a.getPermiso(nombre);
			if(p != null){
				
				r.quitarPermiso(p);
			}
			
		}catch(Exception e){
			
			return 0;
		}
		
		return 1;
	}


	@Override
	public int altaPermiso(String nombre, long appId) {
		// TODO Auto-generated method stub
		
		try{
			
			
			Aplicacion a = em.find(Aplicacion.class, appId);
			if (a!= null && !a.existePermiso(nombre)){
			
				Permiso p = new Permiso();
				p.setNombre(nombre);
				em.persist(p);
				a.agregarPermiso(p);
			}
			
		}catch(Exception ex){
			
			return 0;
		}
		
		return 1;
	}
    
    
    
    

}
