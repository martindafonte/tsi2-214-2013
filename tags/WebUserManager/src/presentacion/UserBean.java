/**
 * 
 */
package presentacion;

import java.util.Iterator;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import negocio.ServiciosLocal;

/**
 * @author bruno
 * 
 */
public class UserBean{

	private java.lang.String nick;
	private java.lang.String pass;
	private java.lang.String nombre;
	private java.lang.String apellido;

	@EJB
	private ServiciosLocal serv;

	public UserBean() {
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

	public java.lang.String getNombre() {
		return nombre;
	}

	public void setNombre(java.lang.String nombre) {
		this.nombre = nombre;
	}

	public java.lang.String getApellido() {
		return apellido;
	}

	public void setApellido(java.lang.String apellido) {
		this.apellido = apellido;
	}

	public String go() {

		if (serv.altaDesarrollador(nick, pass, nombre, apellido) == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			Iterator<FacesMessage> iter = context.getMessages();
	    	while (iter.hasNext()) {
	    		iter.remove();
	    	}
			UserLogin u = (UserLogin) context.getExternalContext()
					.getSessionMap().get("userLogin");
			u.setNick(nick);
			u.setPass(pass);
			u.setLogin(true);

			return "/showAplicaciones.xhtml";
		} else{
			
			FacesMessage msg = new FacesMessage(
					"Error al ingresar usuario",
					"Ya existe un usuario con este nick");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("mensaje", msg);
//			throw new ValidatorException(msg);
			return null;
//			return "/index.xhtml";
		}
	}

	public String greeting() {

		return "Mensaje!!!";

	}

}
