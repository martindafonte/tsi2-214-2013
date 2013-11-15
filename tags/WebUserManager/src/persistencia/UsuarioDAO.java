package persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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

import modelo.Aplicacion;
import modelo.Canal;
import modelo.Desarrollador;
import modelo.Permiso;
import modelo.Registro;
import modelo.Rol;
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

    }

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int altaUsuario(Usuario u, long appId) {
		try{
			Canal c  = new Canal();
			Aplicacion a = em.find(Aplicacion.class, appId);
			c.setApp(a);
			c.setCodigo(u.getNick());
			c.setPropietario(true);
			List<Registro> lr = new ArrayList<Registro>(); 
			List<Rol> lrol = new ArrayList<Rol>();
			c.setRegistrados(lr);
			c.setUser(u);
			a.getCanales().add(c);
			u.setRoles(lrol);
			u.setAplicacion(a);
			u.setCanal(c);
			em.persist(u);
			a.getUsers().add(u);
			
			return ConstantesPersistencia.Exito;
			
		}catch(Throwable ex){
			return ConstantesPersistencia.Error;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Usuario chequearUsuario(String nick, String pass, long appId) {
		try{
			Query q2 = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
			q2.setParameter(1, appId);
			List<Aplicacion> la = q2.getResultList();
			Query q = em.createQuery("SELECT x FROM Usuario x WHERE x.nick = ?1 and pass = ?2 and aplicacion = ?3");
			q.setParameter(1, nick).setParameter(2, pass).setParameter(3, la.get(0));
			List<Usuario> us = q.getResultList();
			if (us.size() == 1 ){
				Usuario u = us.get(0);
				return u;
				
			}	
		}catch(Exception e){
			return null;
			
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Usuario obtenerUsuario(String nick,long appId) {
		try{
			Query q2 = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
			q2.setParameter(1, appId);
			List<Aplicacion> la = q2.getResultList();
			Query q = em.createQuery("SELECT x FROM Usuario x WHERE x.nick = ?1 and aplicacion = ?2");
			q.setParameter(1, nick).setParameter(2, la.get(0));
			List<Usuario> us = q.getResultList();
			if (us.size() == 1 ){
				Usuario u = us.get(0);
				u.getCanal().getCodigo();
				return u;
				
			}	
		}catch(Exception e){
			return null;
			
		}
		return null;
	}
	
	@Override
	public int altaDesarrollador(Desarrollador u) {

		try{
			if(existeDesarrollador(u.getNick())){
				return ConstantesPersistencia.Error;
			}
			u.setProviderID("baas");
			em.persist(u);
			return ConstantesPersistencia.Exito;
		}catch(Exception ex){
			return ConstantesPersistencia.Error;
		}
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Desarrollador getDesarrollador(String nick, String pass) {


		try{
			Query q = em.createQuery("SELECT x FROM Desarrollador x WHERE x.nick = ?1 and x.pass = ?2 and x.providerID = ?3");
			q.setParameter(1, nick).setParameter(2, pass).setParameter(3, "baas");			
			List<Desarrollador> us = q.getResultList();
			if (us.size() == 1 ){
				Desarrollador d = us.get(0);
				return d;
			}
		}catch(Exception e){
			return null;
			
		}
		return null;
	}

	@Override
	public List<Registro> obtenerRegistrosUsuario(String nick, long app_id) {
		try{
			Query q2 = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
			q2.setParameter(1, app_id);
			@SuppressWarnings("unchecked")
			List<Aplicacion> la = q2.getResultList();
			Query q = em.createQuery("SELECT x FROM Usuario x WHERE x.nick = ?1 and x.aplicacion = ?2");
			q.setParameter(1, nick).setParameter(2, la.get(0));
			@SuppressWarnings("unchecked")
			List<Usuario> us = q.getResultList();
			if (us.size() == 1 ){
				Usuario u = us.get(0);
				List<Registro> registros = u.getCanal().getRegistrados();
				registros.size();
				return registros;	
			}			
		}catch(Exception e){
			return null;
		}
		return null;
	}

	@Override
	public int agregarRegistroUsuario(String nick, long app_id, String regid) {
		try{
//			Query q2 = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
//			q2.setParameter(1, app_id);
//			@SuppressWarnings("unchecked")
//			List<Aplicacion> la = q2.getResultList();
			Aplicacion a = em.find(Aplicacion.class, app_id);
			if( a == null){
				return ConstantesPersistencia.Error;
			}
			
//			Query q = em.createQuery("SELECT x FROM Usuario x WHERE x.nick = ?1 and x.aplicacion = ?2");
//			q.setParameter(1, nick).setParameter(2, a);
//			@SuppressWarnings("unchecked")
//			List<Usuario> us = q.getResultList();
			List<Usuario> us = a.getUsers();
			Usuario u = null;
			Iterator<Usuario> itu = us.iterator();
			while(itu.hasNext()){
				u = itu.next();
				if(u.getNick().equals(nick)){
					break;
				}
			}

			if( u != null && regid != ""){
//			if (us.size() == 1 && regid!=""){
//				Usuario u = us.get(0);
				boolean existe = false;
				for(Registro r:u.getCanal().getRegistrados()){
					if(r.getRegistrer().equals(regid)){
						existe =true;
						break;
					}
				}
				
				if(!existe){
					Registro nuevo_r = new Registro();
					nuevo_r.setRegistrer(regid);
					List<Canal> lc = new ArrayList<Canal>();
					nuevo_r.setCanales(lc);
					em.persist(nuevo_r);
					u.getCanal().getRegistrados().add(nuevo_r);
					nuevo_r.getCanales().add(u.getCanal());
//					em.flush();u
				}
			}			
		}catch(Exception e){
			return 0;
		}
		return 0;
	}
	
	@Override
	public int quitarRegistroUsuario(String nick, long app_id, String regid) {
		try{
			Query q2 = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
			q2.setParameter(1, app_id);
			@SuppressWarnings("unchecked")
			List<Aplicacion> la = q2.getResultList();
			Query q = em.createQuery("SELECT x FROM Usuario x WHERE x.nick = ?1 and x.aplicacion = ?2");
			q.setParameter(1, nick).setParameter(2, la.get(0));
			@SuppressWarnings("unchecked")
			List<Usuario> us = q.getResultList();
			if (us.size() == 1 ){
				Usuario u = us.get(0);
				for(Registro r:u.getCanal().getRegistrados()){
					if(r.getRegistrer()==regid){
						u.getCanal().quitarRegistro(r);
						em.flush();
					}
				}
			}			
		}catch(Exception e){
			return 0;
		}
		return 0;
	}

	@Override
	public List<String> obtenerPermisosUsuario(String nick, long appid) {
		try{
			List<String> permisos = new LinkedList<String>();
			Query q2 = em.createQuery("SELECT x FROM Aplicacion x WHERE x.id = ?1");
			q2.setParameter(1, appid);
			@SuppressWarnings("unchecked")
			List<Aplicacion> la = q2.getResultList();
			Query q = em.createQuery("SELECT x FROM Usuario x WHERE x.nick = ?1 and aplicacion = ?2");
			q.setParameter(1, nick).setParameter(2, la.get(0));
			@SuppressWarnings("unchecked")
			List<Usuario> us = q.getResultList();
			if (us.size() == 1 ){
				Usuario u = us.get(0);
				for(Rol r:u.getRoles()){
					for(Permiso p:r.getPerms()){
						permisos.add(p.getNombre());
					}
				}
			}	
			return permisos;
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean existeDesarrollador(String nick) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT x FROM Desarrollador x WHERE x.nick = ?1 and x.providerID = ?2");
		q.setParameter(1, nick).setParameter(2, "baas");
		return (q.getResultList().size() > 0);
	}
	
	@Override
	public boolean existeDesarrollador(String nick, String provider) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT x FROM Desarrollador x WHERE x.nick = ?1 and x.providerID = ?2");
		q.setParameter(1, nick).setParameter(2, provider);
		return (q.getResultList().size() > 0);
	}


	@Override
	public int altaDesarrollador(Desarrollador u, String provider) {
		// TODO Auto-generated method stub
		
		try{
			if(existeDesarrollador(u.getNick(), provider)){
				return ConstantesPersistencia.Error;
			}
			u.setProviderID(provider);
			em.persist(u);
			return ConstantesPersistencia.Exito;
		}catch(Exception ex){
			return ConstantesPersistencia.Error;
		}		
	}

	@Override
	public Desarrollador getDesarrollador(String nick, String pass,
			String provider) {
		
		try{
			Query q = em.createQuery("SELECT x FROM Desarrollador x WHERE x.nick = ?1 and x.pass = ?2 and x.providerID = ?3");
			q.setParameter(1, nick).setParameter(2, pass).setParameter(3, provider);			
			@SuppressWarnings("unchecked")
			List<Desarrollador> us = q.getResultList();
			if (us.size() == 1 ){
				Desarrollador d = us.get(0);
				return d;
			}
		}catch(Exception e){
			return null;
			
		}
		return null;
	}
	
}
