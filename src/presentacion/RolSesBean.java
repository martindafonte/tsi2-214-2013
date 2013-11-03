/**
 * 
 */
package presentacion;

import java.util.List;

import javax.faces.context.FacesContext;

/**
 * @author bruno
 *
 */
public class RolSesBean {

	
	private Long id;
	private String nombre;

	
	public RolSesBean() {
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

	public List<PermSesBean> getPermsDontHave() {

		UserLogin user = this.getUserLogin();
		return user.getPermsDontHave(id);
	}
	
	public List<PermSesBean> getPermsHave(){
		
		UserLogin user = this.getUserLogin();
		return user.getPermsHave(id);
	}
	
	private UserLogin getUserLogin(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		return (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
	}
	
	public void showPerms(){

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("rolSesBean", this);
		
	}
	
}
