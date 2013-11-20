/**
 * 
 */
package presentacion;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

/**
 * @author bruno
 *
 */
public class RolSesBean {

	
	private Long id;
	private String nombre;
//	private boolean load = false;

	
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
		
		if (id != null){
			UserLogin user = this.getUserLogin();
			return user.getPermsDontHave(id);
		}
		return new ArrayList<PermSesBean>();
	}
	
	public List<PermSesBean> getPermsHave(){
		
		if (id != null){
			UserLogin user = this.getUserLogin();
			return user.getPermsHave(id);
		}
		return new ArrayList<PermSesBean>();
	}
	
	private UserLogin getUserLogin(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		return (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
	}
	
	public void showPerms(){

		RolSesBean r = new RolSesBean();
		r.setId(id);
		r.setNombre(nombre);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("rolSesBean", r);
		
	}
	
}
