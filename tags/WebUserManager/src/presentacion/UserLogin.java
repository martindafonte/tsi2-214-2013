/**
 * 
 */
package presentacion;

/**
 * @author bruno
 *
 */
public class UserLogin {
	private java.lang.String nick;
	private java.lang.String pass;

	public UserLogin() {
	}

	public java.lang.String getNick() {
		return nick;
	}

	public void setNick(java.lang.String nick) {
		this.nick = nick;
	}

	public java.lang.String getPass() {
		return pass;
	}

	public void setPass(java.lang.String pass) {
		this.pass = pass;
	}
	
	public String login(){
		
		Boolean error = false; // = existeUsuario(nick,pass);
		if(error)
			return "errorPage.xhtml";
	
		return "login.xhtml";
		
	}
	
	public String logout(){
		
		Boolean error = false; // = existeUsuario(nick,pass);
		if(error)
			return "errorPage.xhtml";
	
		return "logout.xhtml";
		
	}
	
}
