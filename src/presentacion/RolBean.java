/**
 * 
 */
package presentacion;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import persistencia.ConstantesPersistencia;

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
		if(app != null && user != null ){
			if (user.agregarRol(nombre, app.getAplicacionid().longValue()) == ConstantesPersistencia.Error){

				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage msg = new FacesMessage("Ya tienes un rol con ese nombre para "+app.getNombre(), "Error");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				context = FacesContext.getCurrentInstance();
				context.addMessage("rolForm:rolNom", msg);
				
			}
		}else{
			
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("[ERROR!] No estas logueado, o la no has seleccionado una Aplicación", "Error");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context = FacesContext.getCurrentInstance();
			context.addMessage("rolForm:rolNom", msg);
			
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
