package negocio;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import persistencia.UsuarioDAO;
import modelo.Aplicacion;
import modelo.Usuario;

/**
 * Session Bean implementation class ServicioLogin
 */
@Stateful
@LocalBean
public class ServicioLogin implements ServicioLoginLocal {

    /**
     * Default constructor. 
     */
	
	@EJB
	private UsuarioDAO servuser;
	
	private boolean login = false;
	private Usuario user;
	private List<Aplicacion> apps;
	
	public List<Aplicacion> getApps() {
		return apps;
	}

	public void setApps(List<Aplicacion> apps) {
		this.apps = apps;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}


    public ServicioLogin() {
        // TODO Auto-generated constructor stub
    }
    
//    public Usuario loginUser(String app, String nick, String pass){
//    	
//    	
//    	if(login){
//	    	Iterator<Aplicacion> ita = apps.iterator();
//	    	while( ita.hasNext() ){
////	    		if
//	    		
//	    	}
//    	}else{
//    		
//    	}
//    }

}
