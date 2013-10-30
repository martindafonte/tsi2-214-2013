package serviciosRest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

import mensajesRest.Mensaje;
import modelo.Desarrollador;
import negocio.ServiciosLocal;
import persistencia.AplicacionDAOLocal;
//import javax.inject.Inject;

@Stateful
@LocalBean
@SessionScoped
public class UsersRest implements IUsersRest {
	
	@EJB
	private AplicacionDAOLocal app;
	
	@EJB
	private ServiciosLocal serv;
	
	private int count =0;
	
    public UsersRest() {
    }
    
    @Override
    public Mensaje login(String user,String pass){
    	Mensaje msj = new Mensaje();
    	Desarrollador d = serv.getDesarrollador(user, pass);
    	if(d != null){
    		msj.codigo=0;
    	}else{
    	msj.codigo=++count;}
    	return msj;
    }

}
