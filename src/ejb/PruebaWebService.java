package ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
//import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import persistencia.AplicacionDAOLocal;

/**
 * Session Bean implementation class PruebaWebService
 */
@Stateless
@LocalBean
@Path("/prueba1")
public class PruebaWebService {

    /**
     * Default constructor. 
     */
	@EJB
	private AplicacionDAOLocal app;
	
    public PruebaWebService() {
        // TODOAuto-generated constructor stub
    }
    
    @GET
    @Produces("text/plain")
    public String algo(){
    	if(app != null){
    		return "Hola";
    	}
    	return "a la mierda";
    } 
    

}
