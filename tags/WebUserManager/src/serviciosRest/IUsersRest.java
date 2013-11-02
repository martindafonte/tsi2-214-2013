package serviciosRest;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mensajesRest.Mensaje;
import mensajesRest.MensajePermisos;
import mensajesRest.MensajeUsuario;
@Path("/Users")
public interface IUsersRest {
	@POST
	@Path("{app}/{nick}/login")
    @Produces(MediaType.APPLICATION_JSON)
	public Mensaje login(@PathParam("nick") String user, @FormParam("pass")String pass, @FormParam("regid")String regid,@PathParam("app")long app);
	
	@POST
	@Path("{app}")
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje registrar(@FormParam("nick") String user, @FormParam("pass")String pass,@FormParam("nombre")String nombre,@FormParam("apellido")String apellido, @PathParam("app")long app);

	
	@GET
	@Path("{app}/{nick}")
	@Produces(MediaType.APPLICATION_JSON)
	public MensajeUsuario obtenerUsuario(@PathParam("nick") String nick, @PathParam("app") long app);

		
	@DELETE
	@Path("{app}/{nick}/login")
    @Produces(MediaType.APPLICATION_JSON)
	public Mensaje logout(@PathParam("nick") String user, @PathParam("app")long app, @FormParam("regid") String regid);
	
	@GET
	@Path("{app}/{nick}/Permisos")
	@Produces(MediaType.APPLICATION_JSON)
	public MensajePermisos obtenerPermisosUsuario(@PathParam("nick") String nick, @PathParam("app") long app);
	
	@POST
	@Path("{app}/Permisos/{rol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje agregarPermisoRol(@PathParam("rol") String rol, @PathParam("app") long app, @FormParam("permiso") String p_permiso);
	
	@POST
	@Path("{app}/{nick}/Permisos")
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje setearRolUsuario(@PathParam("nick") String nick, @PathParam("app") long app, @FormParam("rol") String p_rol);

	@DELETE
	@Path("{app}/{nick}/Permisos/{rol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje quitarRolUsuario(@PathParam("nick") String nick, @PathParam("app") long app, @PathParam("rol") String p_rol);
	//TODO bloquear usuario
}