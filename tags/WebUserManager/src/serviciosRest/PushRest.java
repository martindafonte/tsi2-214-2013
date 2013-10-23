package serviciosRest;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/Push")
@Stateless
public class PushRest {

	@GET
	@Produces("text/plain")
	@Path("/Get")
	public String holaMundo(){
		return "Hola PEPE";
	}
}
