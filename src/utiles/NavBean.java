/**
 * 
 */
package utiles;

import java.util.Map;

import javax.faces.context.FacesContext;
import presentacion.UserLogin;

/**
 * @author bruno
 * 
 */
public class NavBean {

	
	private FacesContext getContext(){ return FacesContext.getCurrentInstance();}
	
	private Map<String, Object> getSessionMap(){ return this.getContext().getExternalContext().getSessionMap();}
	
	public NavBean() {}
	
	public String getBannerTop() {

		if (this.getSessionMap().containsKey("userLogin")) {
			UserLogin user = (UserLogin)this.getSessionMap().get("userLogin");
			if(user.getLogin().booleanValue()){
				return "BannerTop.xhtml";
			}
		}
		return "BannerTopWL.xhtml";
	}

	public String getInfoAplicacion() {
		// TODO
		if (this.getSessionMap().containsKey("userLogin")) {
			UserLogin user = (UserLogin)this.getSessionMap().get("userLogin");
			if(user.getApps().size() > 0){
				return "/infoAplicacion.xhtml";
			}else{
				return "/errorNoHayApps.xhtml";
			}
			
		}else{
			return "/index.xhtml";
		}	
		
	}

	public String showRoles() {
		// TODO
		if (this.getSessionMap().containsKey("userLogin")) {
			UserLogin user = (UserLogin)this.getSessionMap().get("userLogin");
			if(user.getApps().size() > 0){
				return "/showSeguridad.xhtml";
			}else{
				return "/errorNoHayApps.xhtml";
			}
			
		}else{
			return "/index.xhtml";
		}		

	}
	


}
