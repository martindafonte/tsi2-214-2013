package negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import persistencia.AplicacionDAOLocal;
import persistencia.CanalDAOLocal;
import persistencia.UsuarioDAOLocal;
import presentacion.AppSesBean;
import presentacion.CanalSesBean;
import modelo.Aplicacion;
import modelo.Canal;
import modelo.Desarrollador;
import modelo.Registro;
import modelo.Usuario;

/**
 * Session Bean implementation class Servicios
 */
@Stateless
@LocalBean
public class Servicios implements ServiciosLocal {

    /**
     * Default constructor. 
     */
	@EJB
	private UsuarioDAOLocal ul;
	
	@EJB
	private CanalDAOLocal cl;
	
	@EJB
	private AplicacionDAOLocal al;
	
    public Servicios() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void altaUsuario(String nick, String pass, String nombre,
			String apellido) {
		// TODO Auto-generated method stub
		Usuario u = new Usuario();
//		u.setId(appinfo.getId("Usuario"));
		u.setNick(nick);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);
		
		ul.altaUsuario(u);
	}

	@Override
	public Usuario getUsuario(String nick, String pass) {
		// TODO Auto-generated method stub
		
		
		return ul.getUsuario(nick, pass);	
	}

	@Override
	public Boolean existeUsuario(String nick, String pass) {
		// TODO Auto-generated method stub
		
		if (ul.getUsuario(nick, pass) != null){
			return true;
		}
		
		return false;
	}

	@Override
	public void altaDesarrollador(String nick, String pass, String nombre,
			String apellido) {
		// TODO Auto-generated method stub
		
		Desarrollador u = new Desarrollador();
//		u.setId(appinfo.getId("Desarrollador"));
		u.setNick(nick);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);
		ul.altaDesarrollador(u);
		
		
		
	}

	@Override
	public Desarrollador getDesarrollador(String nick, String pass) {
		// TODO Auto-generated method stub
		return ul.getDesarrollador(nick, pass);
	}

	@Override
	public Boolean existeDesarollador(String nick, String pass) {
		// TODO Auto-generated method stub
		if (ul.getDesarrollador(nick, pass) != null){
			return true;
		}
		
		return false;
	}

	@Override
	public void altaAplicacion(String nombre, String descripcion, String nick,
			String pass) {
		// TODO Auto-generated method stub
		
		Desarrollador d = ul.getDesarrollador(nick, pass);
		if (d == null) {
			return;
		}
		
		Aplicacion a = new Aplicacion();
		
//		a.setId(appinfo.getId("Aplicacion"));
		a.setNombre(nombre);
		a.setDescripcion(descripcion);
		al.altaApliacion(a,d);
		
	}

	@Override
	public List<Aplicacion> getAplicaciones(Desarrollador d) {
		// TODO Auto-generated method stub
		
		return al.getAplicaciones(d);
		
	}

//	@Override
//	public boolean altaCanal(Canal c, Aplicacion a) {
//		// TODO Auto-generated method stub
////		cl.altaCanal(c);
////		return al.agregarCanalAplicacion(c, a);
//		return al.agregarCanalAplicacion(c.getCodigo(), a.getId());
//	}

	@Override
	public Canal getCanal(String cod) {
		// TODO Auto-generated method stub
		cl.getCanal(cod);
		return null;
	}

	@Override
	public Aplicacion getApliacion(long id) {
		// TODO Auto-generated method stub
		
		return al.getAplicacion(id);
	}

	@Override
	public void cambiarSingleLogin(long id, boolean valor) {
		// TODO Auto-generated method stub
		al.cambiarSingleLogin(id, valor);
	}
	
	@Override
	public void cambiarSingleLogin(long id) {
		// TODO Auto-generated method stub
		al.cambiarSingleLogin(id);
	}

	@Override
	public List<Canal> getCanales(Aplicacion a) {
		// TODO Auto-generated method stub
		return al.getCanales(a);
	}

//	@Override
//	public Canal crearCanal(String codigo, Aplicacion a) {
//		// TODO Auto-generated method stub
//		Canal c = new Canal();
//		c.setCodigo(codigo);
////		cl.altaCanal(c, a);
//		return c;
//	}

	@Override
	public List<AppSesBean> getAplicaciones(String nick, String pass) {
		// TODO Auto-generated method stub
		List<AppSesBean> la = new ArrayList<AppSesBean>();
		Desarrollador d = this.getDesarrollador(nick, pass);
		Iterator<Aplicacion> it = d.getLa().iterator();
		AppSesBean ap = null;
		Aplicacion a = null;
		int num = 1;
		while( it.hasNext()){
			ap = new AppSesBean();
			a = it.next();
			ap.setAplicacionid(a.getId());
			ap.setDescripcion(a.getDescripcion());
			ap.setNombre(a.getNombre());
			ap.setSingleLogin(a.isSingleLogin());
			ap.setNum(num);
			num++;
			List<CanalSesBean> lc = new ArrayList<CanalSesBean>();
			Canal c = null;
			CanalSesBean ca = null;
			Iterator<Canal> itc = a.getCanales().iterator();
			while(itc.hasNext()){
				
				c = itc.next();
				ca = new CanalSesBean();
				ca.setCodigo(c.getCodigo());
				ca.setRegistrados(c.getRegistrados().size());
				lc.add(ca);
				
			}
			ap.setCanales(lc);
			la.add(ap);			
		}
		
		return la;
		
		
	}

	@Override
	public List<CanalSesBean> getCanales(long id) {
		// TODO Auto-generated method stub
		
		List<CanalSesBean> lc = new ArrayList<CanalSesBean>();
		Aplicacion a = al.getAplicacion(id);
		Iterator<Canal> itc = a.getCanales().iterator();
		Canal c;
		CanalSesBean ca;
		while(itc.hasNext()){
			
			c = itc.next();
			ca = new CanalSesBean();
			ca.setCodigo(c.getCodigo());
			ca.setRegistrados(c.getRegistrados().size());
			lc.add(ca);
		}
			
		return lc;
	}

	@Override
	public void crearCanal(String codigo, AppSesBean app) {
		// TODO Auto-generated method stub
		
		Aplicacion a = al.getAplicacion(app.getAplicacionid());
		Canal c = new Canal();
		c.setCodigo(codigo);
		c.setApp(a);
		c.setRegistrados(new ArrayList<Registro>());
		cl.altaCanal(c);
	}

	@Override
	public void borrarCanal(String codigo, AppSesBean app) {
		// TODO Auto-generated method stub
		al.quitarCanalAplicacion(codigo, app.getAplicacionid());
	}



}
