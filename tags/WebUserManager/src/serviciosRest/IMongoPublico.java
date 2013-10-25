package serviciosRest;

import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mensajesRest.Mensaje;
import mensajesRest.MensajeJson;
import mensajesRest.MensajeJsonId;

import org.json.JSONException;



@Path("/MongoServicios")
public interface IMongoPublico  {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{apid}/{jsonid}")
	MensajeJson ObtenerJson( @PathParam("apid") int app, @PathParam("jsonid") int jsonId) throws UnknownHostException, JSONException;

	@POST
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{apid}")
	MensajeJsonId IngresarJson(@PathParam("apid") int app,  String json) throws UnknownHostException;

	@DELETE
	@Path("{apid}/{jsonid}")
	@Produces(MediaType.APPLICATION_JSON)
	Mensaje EliminarJson(@PathParam("apid") int app,@PathParam("jsonid") int jsonId) throws UnknownHostException;
	
	@PUT
	@Path("{apid}/{jsonid}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	Mensaje ActualizarJson(@PathParam("apid") int app, @PathParam("jsonid") int jsonId, String json) throws UnknownHostException;
	
	@DELETE
	@Path("deleteDB/{apid}")
	@Produces(MediaType.APPLICATION_JSON)
	Mensaje BorrarBase(@PathParam("apid") int app) throws UnknownHostException;
}