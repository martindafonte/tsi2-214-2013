package utiles;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import negocio.ServiciosLocal;

public class ValDesarrollador implements Validator {

	@EJB
	private ServiciosLocal serv;

//	@SuppressWarnings("unused")
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {

		String nick = arg2.toString();
		if(arg0.getExternalContext().getRequestParameterMap().containsKey("userBean")){
			if (serv.existeDesarollador(nick)) {
				FacesMessage msg = new FacesMessage("Error al ingresar usuario","Ya existe un usuario con este nick");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
				
			}
		}
		@SuppressWarnings("unused")
		String algo = arg2.toString();

	}

}
