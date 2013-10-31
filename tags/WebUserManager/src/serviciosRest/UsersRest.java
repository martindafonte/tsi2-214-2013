package serviciosRest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.PathParam;

import mensajesRest.Mensaje;
import mensajesRest.MensajeUsuario;
import modelo.Usuario;
import negocio.ServiciosLocal;
import persistencia.AplicacionDAOLocal;
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
	
		
    public UsersRest() {
    }
    
    @Override
    public Mensaje login(String nick,String pass){
    	Mensaje msj = new Mensaje();
    	Usuario d = serv.getUsuario(nick, pass);
    	if(d != null){
    		msj.codigo=Constantes.Cte_Exito;
    		msj.descripcion="Ok";
    	}else{
    	msj.codigo=Constantes.User_Error_loginfail;
    	msj.descripcion="El usuario o la contrae�a no son correctos";
    	}
    	return msj;
    }

	@Override
	public Mensaje registrar(String user, String pass, String nombre,
			String apellido) {
		Usuario u = new Usuario();
		u.setNick(user);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);
		Mensaje msj = new Mensaje();
		if (ul.altaUsuario(u) == 0)
		{
			msj.codigo=Constantes.Cte_Exito;
			msj.descripcion = "Registro exitoso";
		}else{
			msj.codigo = Constantes.User_Error_registration;
		}
		return msj;
	}

	@Override
	public MensajeUsuario obtenerUsuario(String nick, long app) {
		
		return null;
	}

}
