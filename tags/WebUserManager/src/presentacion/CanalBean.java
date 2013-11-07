/**
 * 
 */
package presentacion;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import persistencia.ConstantesPersistencia;
import negocio.ServiciosLocal;

/**
 * @author bruno
 *
 */
public class CanalBean {
	
	@EJB
	private ServiciosLocal serv;
	
	private java.lang.String codigo;

	public CanalBean() {
		
	}

	public java.lang.String getCodigo() {
		return codigo;
	}

	public void setCodigo(java.lang.String codigo) {
		this.codigo = codigo;
		
	}
	
	
	public void altaCanal(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		AppSesBean app = (AppSesBean)context.getExternalContext().getSessionMap().get("appSesBean");
		
		
		int ret = serv.crearCanal(codigo, app);
		
		UserLogin user = (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
		user.refresh();
		context.getExternalContext().getSessionMap().put("userLogin", user);
		app.refresh();
		context.getExternalContext().getSessionMap().put("appSesBean", app);
		if(ret == ConstantesPersistencia.Error){
			
			context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Error, ya existe un canal con ese nombre", "Error");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context = FacesContext.getCurrentInstance();
			context.addMessage("canalForm:canalNom", msg);
			
			
		}

	}
}
