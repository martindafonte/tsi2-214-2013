package serviciosRest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mensajesRest.Mensaje;
import mensajesRest.MensajeUsuario;
@Path("/Users")
public interface IUsersRest {
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public Mensaje login(@FormParam("nick") String user, @FormParam("pass")String pass, @FormParam("app")long app);
	
	@GET
	@Path("{nick}/{app}")
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeUsuario obtenerUsuario(@PathParam("nick") String nick, @PathParam("app") long app);

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje registrar(@FormParam("nick") String user, @FormParam("pass")String pass,@FormParam("nombre")String nombre,@FormParam("apellido")String apellido, @FormParam("app")long app);
	
	//TODO log out
	//TODO obtenerpermisosusuario
	//TODO asignarrolusuario
	//TODO bloquear usuario
}