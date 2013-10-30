package serviciosRest;


import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


@Stateless
@Path("/estadisticas")
public interface IEstadisticasRest {

	@POST
	@Asynchronous
	@Path("json/{http}/{metodo}/{apid}/{jsonid}")
	public void registrarPedidoJson( @PathParam("http") String http, @PathParam("metodo") String metodo, @PathParam("apid") long app, @PathParam("jsonid") int jsonId);
	
	@POST
	@Asynchronous
	@Path("push/{http}/{metodo}/{http}/{metodo}/{apid}")
	public void registrarPedidoPush( @PathParam("http") String http, @PathParam("metodo") String metodo, @PathParam("apid") long app);
	
	@POST
	@Asynchronous
	@Path("user/{http}/{metodo}/{apid}/{userid}")
	public void registrarPedidoUser( @PathParam("http") String http, @PathParam("metodo") String metodo, @PathParam("apid") long app, @PathParam("userid") String userId);

	@POST
	@Asynchronous
	@Path("msj/{apid}/{canid}")
	public void registrarPedidoMsj( @PathParam("apid") long app, @PathParam("canid") String canId);
}
