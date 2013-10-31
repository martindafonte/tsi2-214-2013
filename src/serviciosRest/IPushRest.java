package serviciosRest;

import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mensajesRest.Mensaje;

@Path("/Push")
@Stateless
public interface IPushRest {

	@POST
	@Path("{apid}/{chanel}/User")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Mensaje registrar(@PathParam("apid")long p_appId, @FormParam("id") String p_regId, @PathParam("chanel") String p_canal);
	
	@DELETE
	@Path("{apid}/{chanel}/User/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public abstract Mensaje desregistrar(@PathParam("apid")long p_appId, @PathParam("id") String p_regId, @PathParam("chanel") String p_canal);
	
	@POST
	@Path("{apid}/{chanel}/Message")
	@Produces(MediaType.APPLICATION_JSON)
	//Envia a todos los canales de una aplicacion un mensaje
	public abstract Mensaje enviarACanal(@PathParam("apid")long p_appId, @PathParam("chanel") String p_canal, @FormParam("msg") String p_msj);
	
	//enviar mensaje a usuario
}
