package presentacion;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.brickred.socialauth.cdi.SocialAuth;

public class UpdateStatus implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(Authenticator.class);

    @Inject
    private SocialAuth socialauth;

    private String statusText;

    /**
     * Method which updates the status on profile.
     * 
     * @param ActionEvent
     * @throws Exception
     */

    public void updateStatus() throws Exception {
            final HttpServletRequest request = (HttpServletRequest) FacesContext
                            .getCurrentInstance().getExternalContext().getRequest();
            String statusText = request.getParameter("statusMessage");
            if (statusText != null && !statusText.equals("")) {
                    socialauth.setStatus(statusText);
                    socialauth.updateStatus();
                    setStatus("Status Updated Successfully");
                    System.out.println("status text:" + statusText);
            }
    }

    public String getStatus() {
            return statusText;
    }

    public void setStatus(String statusText) {
            this.statusText = statusText;
    }

	public static Logger getLog() {
		return log;
	}

	public java.lang.String getStatusText() {
		return statusText;
	}

	public void setStatusText(java.lang.String statusText) {
		this.statusText = statusText;
	}
}