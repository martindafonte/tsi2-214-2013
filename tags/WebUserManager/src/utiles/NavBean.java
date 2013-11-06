/**
 * 
 */
package utiles;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.jboss.resteasy.spi.HttpRequest;

import presentacion.UserLogin;

import com.sun.faces.context.SessionMap;

/**
 * @author bruno
 * 
 */
public class NavBean {

	private FacesContext context;
	private Map<String, Object> sesmap;

	public NavBean() {

	}
	
	@PostConstruct
	public void init(){
		
		context = FacesContext.getCurrentInstance();
		sesmap = context.getExternalContext().getSessionMap();
		
	}

	// public String getIndex(){
	// //TODO
	// return null;
	// }

	public String getBannerTop() {

		if (sesmap.containsKey("userLogin")) {
			return "/BannerTop.xhtml";
		}
		return "/BannerTopWL.xhtml";
	}

	public String getShowAplicaciones() {

		return null;
	}

	public String getAltaAplicacion() {
		// TODO
		if (sesmap.containsKey("userLogin")) {
			return "/WebUserManager/altaAplicacion.xhtml";
		}
		return null;
	}

	public String getInfoAplicacion() {
		// TODO
		if (sesmap.containsKey("appSesBean")) {
			return "/WebUserManager/infoAplicacion.xhtml";
		}
		return "/WebUserManager/altaAplicacion.xhtml";
	}

	public String getShowRoles() {
		// TODO
		if (sesmap.containsKey("appSesBean")) 
		{
			return "/WebUserManager/showRoles.xhtml";
		}
		return "/WebUserManager/altaAplicacion.xhtml";

	}

	public String getInfoRol() {
		// TODO
		if ((sesmap.containsKey("appSesBean"))
				&& (sesmap.containsKey("rolSesBean"))
				)
		{
			return "/WebUserManager/infoRol.xhtml";
		}
		return "/WebUserManager/altaAplicacion.xhtml";
	}

}
