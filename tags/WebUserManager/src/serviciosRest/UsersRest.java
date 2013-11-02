package serviciosRest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import mensajesRest.Mensaje;
import mensajesRest.MensajePermisos;
import mensajesRest.MensajeUsuario;
import modelo.Usuario;
import negocio.ServiciosLocal;
import persistencia.AplicacionDAOLocal;
import persistencia.RolDAOLocal;
//import javax.inject.Inject;
import persistencia.UsuarioDAOLocal;

@Stateful
@LocalBean
@SessionScoped
public class UsersRest implements IUsersRest {

	@EJB
	private AplicacionDAOLocal app;

	@EJB
	private ServiciosLocal serv;

	@EJB
	private UsuarioDAOLocal ul;
	
	@EJB
	private RolDAOLocal rl;

	public UsersRest() {
	}

	@Override
	public Mensaje login(String nick, String pass, String regid, long appId) {
		// TODO verificar que el usuario no este bloqueado
		Mensaje msj1 = new Mensaje();
		Usuario d = ul.chequearUsuario(nick, pass, appId);
		if (d != null) {
			msj1.codigo = Constantes.Cte_Exito;
			msj1.descripcion = "Ok";
		} else {
			msj1.codigo = Constantes.User_Error_loginfail;
			msj1.descripcion = "El usuario o la contrae�a no son correctos";
		}
		serv.crearPedidoUser("/Users", "POST", appId, nick);
		ul.agregarRegistroUsuario(nick, appId, regid);
		return msj1;
	}

	@Override
	public Mensaje registrar(String user, String pass, String nombre,
			String apellido, long appId) {
		Usuario u = new Usuario();
		u.setNick(user);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);
		Mensaje msj = new Mensaje();
		if (ul.altaUsuario(u, appId) == 0) {
			msj.codigo = Constantes.Cte_Exito;
			msj.descripcion = "Registro exitoso";
		} else {
			msj.codigo = Constantes.User_Error_registration;
		}
		serv.crearPedidoUser("/Users/register", "POST", appId, user);
		return msj;
	}

	@Override
	public MensajeUsuario obtenerUsuario(String nick, long app) {
		MensajeUsuario msj;
		try {
			serv.crearPedidoUser("/Users/nick/app", "GET", app, nick);
			msj = new MensajeUsuario(ul.obtenerUsuario(nick, app));
			msj.codigo = Constantes.Cte_Exito;
		} catch (Exception e) {
			msj = new MensajeUsuario();
			msj.codigo = Constantes.User_Exception_retrieving;
			msj.descripcion = e.getMessage();
		}
		return msj;
	}

	@Override
	public Mensaje logout(String nick, long appId, String regid) {
		ul.quitarRegistroUsuario(nick, appId, regid);
		Mensaje msj = new Mensaje(Constantes.Cte_Exito);
		return msj;
	}

	@Override
	public MensajePermisos obtenerPermisosUsuario(String nick, long app) {
		MensajePermisos mp = new MensajePermisos();
		try {
			mp.permisos = ul.obtenerPermisosUsuario(nick, app);
			mp.codigo = 0;
		} catch (Exception e) {
			mp.codigo = Constantes.User_Exception_retrieving;
			mp.descripcion = e.getMessage();
		}
		return mp;
	}


	@Override
	public Mensaje setearRolUsuario(String nick, long app, String p_rol) {
		Mensaje msj;
		try {
			rl.asignarRolUsuario(1,1,nick,app,p_rol);
			msj = new Mensaje(Constantes.Cte_Exito);
			return msj;
		} catch (Exception e) {
			msj = new Mensaje(Constantes.User_Exception_retrieving);
			msj.descripcion = e.getMessage();
			return msj;
		}
	}

	@Override
	public Mensaje quitarRolUsuario(String nick, long app, String p_rol) {
		Mensaje msj;
		try {
			rl.quitarRolUsuario(1,1,nick,app,p_rol);
			msj = new Mensaje(Constantes.Cte_Exito);
			return msj;
		} catch (Exception e) {
			msj = new Mensaje(Constantes.User_Exception_retrieving);
			msj.descripcion = e.getMessage();
			return msj;
		}
	}

}
