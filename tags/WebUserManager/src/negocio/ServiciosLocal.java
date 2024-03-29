package negocio;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

import com.google.gson.JsonObject;

import presentacion.AppSesBean;
import presentacion.CanalSesBean;
import presentacion.PermSesBean;
import presentacion.RolSesBean;
import presentacion.PedSesBean;
import modelo.Canal;
import modelo.Desarrollador;
import modelo.Aplicacion;

@Local
public interface ServiciosLocal {

	public int altaDesarrollador(String nick, String pass, String nombre, String apellido);
	public int altaDesarrollador(String nick, String pass, String nombre, String apellido, String provider);
	public void altaUsuario(String nick, String pass, String nombre, String apellido, long appId);
	public Desarrollador getDesarrollador(String nick, String pass);
	public Desarrollador getDesarrollador(String nick, String pass, String provider);

	public Boolean existeDesarollador(String nick, String pass, String provider);
	public Boolean existeDesarollador(String nick, String pass);
	public Boolean existeDesarollador(String nick);
	
	public int altaAplicacion(String nombre, String descripcion, String nick, String pass);
	public int altaAplicacion(String nombre, String descripcion, String nick, String pass, String provider);
	
	public Aplicacion getApliacion(long id);
	public List<Aplicacion> getAplicaciones(Desarrollador d);
	public void cambiarSingleLogin(long id);
	public void cambiarSingleLogin(long id, boolean valor);
	
	
//	################ de prueba
	//anduvo
	public List<AppSesBean> getAplicaciones(String nick, String pass);
	public List<AppSesBean> getAplicaciones(String nick, String pass, String provider);
	
	public List<CanalSesBean> getCanales(long id);
	
	public List<RolSesBean> getRoles(long id);
	
	public List<PermSesBean> getPermsHave(long rolId);
	
	public List<PermSesBean> getPermsDontHave(long rolId);
	
	
	
	public int crearCanal(String codigo, AppSesBean app);
	
	public void borrarCanal(String codigo, AppSesBean app);
	
//	################
	

	public Canal getCanal(String cod);
	public List<Canal> getCanales(Aplicacion a);
	
	
	public int altaPermiso(String nombre, long appId);
	
	public int agregarPermisoRol(String nombre, long rolId);
	
	public int agregarPermisoRol(long permId, long rolId);
	
	public int quitarPermisoRol(long permId, long rolId);
	
	public int quitarPermisoRol(String nombre, long rolId);
	
	public int agregarRol(String nombre, long appId);
	
	
	
//	################ pedidos
	
	@Asynchronous
	public int crearPedidoJson(String http, String metodo, long app, int jsonId);
	
	@Asynchronous
	public int crearPedidoPush(String http, String metodo, long app);
	
	@Asynchronous
	public int crearPedidoUser(String http, String metodo, long app, String userId);
	
	@Asynchronous
	public int crearPedidoMsj(long app,	String canId);
	
	public List<PedSesBean> getPedidos(long app);
	public JsonObject getPedidosJson(long app);
	public int getMensajes(long app, String  canId);
	
	//estadisticas
	public List<PedSesBean> cantPedidosAPI(long aplicacionid);
	public List<PedSesBean> cantDespRegCanales(long appId);
	public List<PedSesBean> cantMsjEnvPush(long appId);
	
	
	
	
	public int getCantUsuarios(Long aplicacionid);
	

	
}
