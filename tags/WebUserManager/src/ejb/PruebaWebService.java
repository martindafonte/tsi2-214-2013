package ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
//import javax.ejb.StatefulTimeout;
//import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.FormParam;
//import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mensajesRest.Mensaje;
import modelo.Desarrollador;
import negocio.ServiciosLocal;
import persistencia.AplicacionDAOLocal;

/**
 * Session Bean implementation class PruebaWebService
 */
//@Stateless
@Stateful
@LocalBean
@SessionScoped
//@StatefulTimeout(600000)
@Path("/prueba1")
public class PruebaWebService {

    /**
     * Default constructor. 
     */
	
	private int count;
	
	@EJB
	private AplicacionDAOLocal app;
	
	@EJB
	private ServiciosLocal serv;
	
    public PruebaWebService() {
        // TODOAuto-generated constructor stub
    }
    
    @GET
    @Path("/hola")
    @Produces("text/plain")
    public String algo(){
    	if(app != null){
    		count++;
    		return "Hola " + count;
    		
    	}
    	return "a la mierda";
    } 
    
    @POST
    //@Path("{user}/{pass}")
//    @Produces("text/plain")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje login(@FormParam("user") String user, @FormParam("pass")String pass){
    	Mensaje msj = new Mensaje();
    	Desarrollador d = serv.getDesarrollador(user, pass);
    	if(d != null){
    		msj.codigo=0;
    	}else{
    	msj.codigo=1;}
    	return msj;
    }

}
