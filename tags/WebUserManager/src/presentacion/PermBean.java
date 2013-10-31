/**
 * 
 */
package presentacion;

import javax.faces.context.FacesContext;

/**
 * @author bruno
 *
 */
public class PermBean {
	public PermBean() {
	}
	
	private String nombre;
	private Long id;
	private Long rolId;

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
	
	public void altaPermiso(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		UserLogin user = (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
		user.agregarPermiso(nombre, rolId);
	}
}
