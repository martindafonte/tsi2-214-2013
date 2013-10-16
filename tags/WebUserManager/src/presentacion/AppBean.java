/**
 * 
 */
package presentacion;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import negocio.ServiciosLocal;

/**
 * @author bruno
 *
 */
public class AppBean {
		
	private String nombre;
	private String descripcion;
	
	@EJB
	private ServiciosLocal serv; 
	
	
	public AppBean() {
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String altaAplicacion(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		UserLogin user = (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
		if(user == null){
			return "/WebUserManager/index.xhtml";			
		}
		
		serv.altaAplicacion(nombre, descripcion, user.getNick(), user.getPass());
		return "";
		
	}
	
}
