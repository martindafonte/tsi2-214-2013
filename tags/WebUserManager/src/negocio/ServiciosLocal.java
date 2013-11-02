package negocio;

import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

import presentacion.AppSesBean;
import presentacion.CanalSesBean;
import modelo.Canal;
import modelo.Desarrollador;
import modelo.Aplicacion;

@Local
public interface ServiciosLocal {

	public void altaDesarrollador(String nick, String pass, String nombre, String apellido);
	public void altaUsuario(String nick, String pass, String nombre, String apellido, long appId);
	public Desarrollador getDesarrollador(String nick, String pass);
/*	public Usuario getUsuario(String nick, String pass);
	public Boolean existeUsuario(String nick, String pass);*/
	public Boolean existeDesarollador(String nick, String pass);
	
	public void altaAplicacion(String nombre, String descripcion, String nick, String pass);
	public Aplicacion getApliacion(long id);
	public List<Aplicacion> getAplicaciones(Desarrollador d);
	public void cambiarSingleLogin(long id);
	public void cambiarSingleLogin(long id, boolean valor);
	
	
//	################ de prueba
	//anduvo
	public List<AppSesBean> getAplicaciones(String nick, String pass);
	
	public List<CanalSesBean> getCanales(long id);
	
	public void crearCanal(String codigo, AppSesBean app);
	
	public void borrarCanal(String codigo, AppSesBean app);
	
//	################
	

	public Canal getCanal(String cod);
	public List<Canal> getCanales(Aplicacion a);
	
	
	public int agregarPermisoRol(String nombre, long rolId);
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

	
}
