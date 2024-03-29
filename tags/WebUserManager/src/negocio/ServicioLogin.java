package negocio;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class ServicioLogin
 */
@Stateful
@LocalBean
public class ServicioLogin implements ServicioLoginLocal {

    /**
     * Default constructor. 
     */
	
	/*@EJB
	private UsuarioDAOLocal servuser;
	
	@EJB
	private AplicacionDAOLocal servapp;
	
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
    }
    
    @PostConstruct
    public void init(){
    	
    	apps = servapp.singleLoginAplicaciones();
    
    }
    
    public Usuario loginUser(String app, String nick, String pass){

    	
    	if(login){
	    	Iterator<Aplicacion> ita = apps.iterator();
	    	while( ita.hasNext() ){
	    		if( ita.next().getNombre() == app){
	    			if((user.getNick() == nick)&&(user.getPass() == pass)){
	    				return user;
	    			}else{
	    				return null;
	    			}
	    			
	    		}	    		
	    	}
    	}else{
    		
    		
    		return servuser.getUsuario(nick, pass);
    		
    	}
    	
    	return null;
    }
*/
}
