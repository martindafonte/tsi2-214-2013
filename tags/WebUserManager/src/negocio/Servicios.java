package negocio;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.google.gson.JsonObject;

import persistencia.AplicacionDAOLocal;
import persistencia.CanalDAOLocal;
import persistencia.ConstantesPersistencia;
import persistencia.RolDAOLocal;
import persistencia.PedidosDAOLocal;
import persistencia.UsuarioDAOLocal;
import presentacion.AppSesBean;
import presentacion.CanalSesBean;
import presentacion.PermSesBean;
import presentacion.RolSesBean;
import presentacion.PedSesBean;
import modelo.Aplicacion;
import modelo.Canal;
import modelo.Desarrollador;
import modelo.Permiso;
import modelo.PedidoJson;
import modelo.PedidoMsj;
import modelo.PedidoPush;
import modelo.PedidoUser;
import modelo.Registro;
import modelo.Rol;
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
	private RolDAOLocal rl;

	@EJB
	private UsuarioDAOLocal ul;

	@EJB
	private CanalDAOLocal cl;

	@EJB
	private AplicacionDAOLocal al;

	@EJB
	private PedidosDAOLocal pl;

	public Servicios() {

	}

	@Override
	public void altaUsuario(String nick, String pass, String nombre,
			String apellido, long appId) {
		Usuario u = new Usuario();
		// u.setId(appinfo.getId("Usuario"));
		u.setNick(nick);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);

		ul.altaUsuario(u, appId);
	}

	/*
	 * @Override public Usuario getUsuario(String nick, String pass) {
	 * 
	 * 
	 * return ul.getUsuario(nick, pass); }
	 * 
	 * @Override public Boolean existeUsuario(String nick, String pass) { if
	 * (ul.getUsuario(nick, pass) != null){ return true; }
	 * 
	 * return false; }
	 */

	@Override
	public int altaDesarrollador(String nick, String pass, String nombre,
			String apellido) {

		Desarrollador u = new Desarrollador();
		// u.setId(appinfo.getId("Desarrollador"));
		u.setNick(nick);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);
		return ul.altaDesarrollador(u);
	}

	@Override
	public Desarrollador getDesarrollador(String nick, String pass) {
		return ul.getDesarrollador(nick, pass);
	}

	@Override
	public Boolean existeDesarollador(String nick, String pass) {
		if (ul.getDesarrollador(nick, pass) != null) {
			return true;
		}

		return false;
	}

	@Override
	public int altaAplicacion(String nombre, String descripcion, String nick,
			String pass) {
		Desarrollador d = ul.getDesarrollador(nick, pass);
		if (d == null) {
			return 0;
		}

		Aplicacion a = new Aplicacion();

		// a.setId(appinfo.getId("Aplicacion"));
		a.setNombre(nombre);
		a.setDescripcion(descripcion);
		return al.altaApliacion(a, d);

	}

	@Override
	public List<Aplicacion> getAplicaciones(Desarrollador d) {

		return al.getAplicaciones(d);

	}

	// @Override
	// public boolean altaCanal(Canal c, Aplicacion a) {
	// // cl.altaCanal(c);
	// // return al.agregarCanalAplicacion(c, a);
	// return al.agregarCanalAplicacion(c.getCodigo(), a.getId());
	// }

	@Override
	public Canal getCanal(String cod) {
		cl.getCanal(cod);
		return null;
	}

	@Override
	public Aplicacion getApliacion(long id) {
		return al.getAplicacion(id);
	}

	@Override
	public void cambiarSingleLogin(long id, boolean valor) {
		al.cambiarSingleLogin(id, valor);
	}

	@Override
	public void cambiarSingleLogin(long id) {
		al.cambiarSingleLogin(id);
	}

	@Override
	public List<Canal> getCanales(Aplicacion a) {
		return al.getCanales(a);
	}

	// @Override
	// public Canal crearCanal(String codigo, Aplicacion a) {
	// Canal c = new Canal();
	// c.setCodigo(codigo);
	// // cl.altaCanal(c, a);
	// return c;
	// }

	@Override
	public List<AppSesBean> getAplicaciones(String nick, String pass) {
		List<AppSesBean> la = new ArrayList<AppSesBean>();
		Desarrollador d = this.getDesarrollador(nick, pass);
		Iterator<Aplicacion> it = d.getLa().iterator();
		AppSesBean ap = null;
		Aplicacion a = null;
		int num = 1;
		while (it.hasNext()) {
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
			while (itc.hasNext()) {

				c = itc.next();
				ca = new CanalSesBean();
				ca.setCodigo(c.getCodigo());
				ca.setRegistrados(c.getRegistrados().size());
				lc.add(ca);

			}
			// ap.setRoles(a.getRolSesBeans());
			// ap.setCanales(lc);
			la.add(ap);
		}

		return la;

	}

	@Override
	public List<CanalSesBean> getCanales(long id) {

		Aplicacion a = al.getAplicacion(id);
		if(a != null){
			return a.getCanalesAppSes();
		}

		return new ArrayList<CanalSesBean>();
	}

	@Override
	public int crearCanal(String codigo, AppSesBean app) {

		try {
			
			Aplicacion a = al.getAplicacion(app.getAplicacionid());
			
			if(a == null){
				return ConstantesPersistencia.Error;
			}
			
			
			Canal c = new Canal();
			c.setCodigo(codigo);
			if(a.existeCanal(c) == ConstantesPersistencia.Error){
				return ConstantesPersistencia.Error;
			}
			
			c.setApp(a);
			c.setRegistrados(new ArrayList<Registro>());
			cl.altaCanal(c);
			
			return ConstantesPersistencia.Exito;

		} catch (Exception e) {

			return ConstantesPersistencia.Error;
		}
	}

	@Override
	public void borrarCanal(String codigo, AppSesBean app) {
		al.quitarCanalAplicacion(codigo, app.getAplicacionid());
	}

	@Override
	public int agregarPermisoRol(String nombre, long rolId) {

		try {

			Permiso p = new Permiso();
			p.setNombre(nombre);
			rl.agregarPermRol(p, rolId);

		} catch (Exception e) {
			return 0;
		}

		return 1;
	}

	@Override
	public int agregarRol(String nombre, long appId) {

		try {
			Rol r = new Rol();
			r.setNombre(nombre);
			List<Permiso> lp = new ArrayList<Permiso>();
			r.setPerms(lp);
			return rl.altaRolAplicacion(r, appId);

		} catch (Exception e) {
			return ConstantesPersistencia.Error;
		}

	}

	@Override
	public int crearPedidoJson(String http, String metodo, long app, int jsonId) {
		try {
			PedidoJson p = new PedidoJson();
			p.setJsonId(jsonId);
			p.setMethod(metodo);
			p.setUrl(http);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			p.setTime(time);

			pl.altaPedidoJSONAplicacion(app, p);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int crearPedidoPush(String http, String metodo, long app) {
		try {
			PedidoPush p = new PedidoPush();
			p.setMethod(metodo);
			p.setUrl(http);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			p.setTime(time);

			pl.altaPedidoPUSHAplicacion(app, p);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int crearPedidoUser(String http, String metodo, long app,
			String userId) {
		try {
			PedidoUser p = new PedidoUser();
			p.setMethod(metodo);
			p.setUrl(http);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			p.setTime(time);

			pl.altaPedidoUMAplicacion(app, p, userId);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public int crearPedidoMsj(long app, String canId) {
		try {
			PedidoMsj p = new PedidoMsj();
			Timestamp time = new Timestamp(System.currentTimeMillis());
			p.setTime(time);

			pl.altaPedidoMSJ(p, canId, app);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<RolSesBean> getRoles(long id) {

		Aplicacion a = al.getAplicacion(id);
		if (a != null) {
			return a.getRolSesBeans();
		}

		return new ArrayList<RolSesBean>();
	}

	@Override
	public List<PermSesBean> getPermsHave(long rolId) {

		return rl.getPermsHave(rolId);

	}

	@Override
	public List<PermSesBean> getPermsDontHave(long rolId) {

		return rl.getPermsDontHave(rolId);

	}

	@Override
	public int agregarPermisoRol(long permId, long rolId) {

		int res;

		try {

			res = rl.agregarPermRol(permId, rolId);

		} catch (Exception ex) {

			return 0;
		}

		return res;
	}

	@Override
	public int quitarPermisoRol(long permId, long rolId) {

		int res;

		try {

			res = rl.quitarPermRol(permId, rolId);

		} catch (Exception ex) {

			return 0;
		}

		return res;

	}

	@Override
	public int quitarPermisoRol(String nombre, long rolId) {

		int res;

		try {

			res = rl.quitarPermRol(nombre, rolId);

		} catch (Exception ex) {

			return 0;
		}

		return res;

	}

	@Override
	public List<PedSesBean> getPedidos(long app) {
		try {
			return pl.getPedidos(app);
		} catch (Exception e) {
		}
		return new ArrayList<PedSesBean>();
	}

	@Override
	public JsonObject getPedidosJson(long app) {
		try {
			return pl.getPedidosJson(app);
		} catch (Exception e) {
		}
		return new JsonObject();
	}

	@Override
	public int getMensajes(long app, String canId) {
		try {
			return pl.getMensajes(app, canId);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public int altaPermiso(String nombre, long appId) {
		try {

			rl.altaPermiso(nombre, appId);

		} catch (Exception ex) {

			return 0;
		}

		return 1;
	}

	@Override
	public Boolean existeDesarollador(String nick) {

		return ul.existeDesarrollador(nick);
	}
}
