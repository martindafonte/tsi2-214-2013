/**
 * 
 */
package presentacion;

import javax.faces.context.FacesContext;

/**
 * @author bruno
 *
 */
public class RolBean {
	
	
	
	public RolBean() {
	}
	
	private Long id;
	private String nombre;
	
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
	
	public void altaRol(){
		
		UserLogin user = this.getUserLogin();
		AppSesBean app = this.getApp();
		if(app != null && user != null){
			user.agregarRol(nombre, app.getAplicacionid().longValue());
		}
		
	}
	
	private UserLogin getUserLogin(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		return (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
	}
	
	private AppSesBean getApp(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		return (AppSesBean)context.getExternalContext().getSessionMap().get("appSesBean");
	}
	
}
