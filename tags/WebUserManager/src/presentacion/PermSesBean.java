/**
 * 
 */
package presentacion;

import javax.faces.context.FacesContext;

/**
 * @author bruno
 *
 */
public class PermSesBean {
	
	private String nombre;
	private Long id;

	public PermSesBean() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void agregar(){
		
		RolSesBean r = this.getRolSesBean();
		UserLogin user = this.getUserLogin();
		user.agregarPermiso(id, r.getId().longValue());

	}
	
	public void quitar(){
		
		RolSesBean r = this.getRolSesBean();
		UserLogin user = this.getUserLogin();
		user.quitarPermiso(id, r.getId().longValue());

	}
	
	private RolSesBean getRolSesBean(){
		return (RolSesBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rolSesBean");
	}
	
	private UserLogin getUserLogin(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		return (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
	}
}
