package serviciosRest;

import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONException;



@Path("/MongoServicios")
public interface IMongoPublico  {
	@GET
	@Produces({"application/json","text/plain",} )
	@Path("{id}")
	String ObtenerJson(@PathParam("id") int ClienteId) throws UnknownHostException, JSONException;
	
	@POST
	@Path("{id}")
	@Consumes("application/json")
	@Produces({"application/json","text/plain"} )
	String IngresarJson(@PathParam("id") int ClienteId, String json) throws UnknownHostException;
	
	@DELETE
	@Path("{id}")
	@Produces("text/plain")
	String EliminarJson(@PathParam("id") int ClienteId) throws UnknownHostException;
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	@Produces("text/plain")
	String ActualizarJson(@PathParam("id") int ClienteId, String json) throws UnknownHostException;
}