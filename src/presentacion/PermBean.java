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
public class PermBean {

	public PermBean() {
	}

	private String nombre;
	private Long id;

	// private Long rolId;

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

	public void altaPermiso() {

		FacesContext context = FacesContext.getCurrentInstance();
		
		if (this.nombre.equals("")) {
			FacesMessage msg = new FacesMessage(
					"Deben ingresar un nombre para el permiso", "Error");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context = FacesContext.getCurrentInstance();
			context.addMessage("infoRolForm:permNom", msg);

		} else {

			UserLogin user = (UserLogin) context.getExternalContext()
					.getSessionMap().get("userLogin");
			AppSesBean app = (AppSesBean) context.getExternalContext()
					.getSessionMap().get("appSesBean");
			if (user.altaPermiso(nombre, app.getAplicacionid().longValue()) == ConstantesPersistencia.Error) {

				FacesMessage msg = new FacesMessage(
						"Ya existe un permiso con ese nombre", "Error");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				context = FacesContext.getCurrentInstance();
				context.addMessage("infoRolForm:permNom", msg);

			}
		}

	}
}
