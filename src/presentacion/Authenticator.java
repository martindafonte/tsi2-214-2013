package presentacion;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.brickred.socialauth.cdi.SocialAuth;



public class Authenticator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(Authenticator.class);

	@Inject
	private SocialAuth socialauth;

	private String openID;

	public static Logger getLog() {
		return log;
	}

	public void updateId(ActionEvent ae) {

		String btnClicked = ae.getComponent().getId();
		log.info("*************login method called ************"
				+ socialauth.getId());

		ExternalContext context = javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext();

		String viewUrl = context.getInitParameter("successUrl");
		socialauth.setViewUrl(viewUrl);

		if (btnClicked.indexOf("facebook") != -1) {

			socialauth.setId("facebook");
			log.info("***facebook*********" + socialauth.getId());

		} else if (btnClicked.indexOf("twitter") != -1) {

			socialauth.setId("twitter");
			log.info("***twitter*********" + socialauth.getId());

		} else if (btnClicked.indexOf("yahoo") != -1) {

			socialauth.setId("yahoo");
			log.info("***yahoo*********" + socialauth.getId());

		} else if (btnClicked.indexOf("hotmail") != -1) {

			socialauth.setId("hotmail");
			log.info("***hotmail*********" + socialauth.getId());

		} else if (btnClicked.indexOf("google") != -1) {

			socialauth.setId("google");
			log.info("***google*********" + socialauth.getId());

		} else if (btnClicked.indexOf("linkedin") != -1) {

			socialauth.setId("linkedin");
			log.info("***linkedin*********" + socialauth.getId());

		} else if (btnClicked.indexOf("foursquare") != -1) {

			socialauth.setId("foursquare");
			log.info("***foursquare*********" + socialauth.getId());

		} else {

			socialauth.setId(openID);
			log.info("***openID*********" + socialauth.getId());
		}

	}

	public String mainPage() {
		return "/home.xhtml";
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

	public void verify(ComponentSystemEvent cse) {

		boolean ajaxRequest = javax.faces.context.FacesContext
				.getCurrentInstance().getPartialViewContext().isAjaxRequest();
		if (!ajaxRequest) {
			try {
				
				socialauth.connect();
				
			} catch (Exception e) {
				log.warn(e);
			}
		}
	}

}
