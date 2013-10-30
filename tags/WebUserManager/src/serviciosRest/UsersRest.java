package serviciosRest;

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

@Stateful
@LocalBean
@SessionScoped
@Path("/Users")
public class UsersRest {
	
	@EJB
	private AplicacionDAOLocal app;
	
	@EJB
	private ServiciosLocal serv;
	
	private int count =0;
	
    public UsersRest() {
    }
    
    @GET
    public String test(){
    	return String.valueOf(++count);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje login(@FormParam("user") String user, @FormParam("pass")String pass){
    	Mensaje msj = new Mensaje();
    	Desarrollador d = serv.getDesarrollador(user, pass);
    	if(d != null){
    		msj.codigo=0;
    	}else{
    	msj.codigo=++count;}
    	return msj;
    }

}
