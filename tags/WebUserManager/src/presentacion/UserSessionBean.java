package presentacion;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;




public class UserSessionBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String FACEBOOK_APP_ID = "712326902129246";
	private String FACEBOOK_APP_SECRET = "dabec4c840e8a68b338c21cd68b8b182";
	
	private SocialAuthManager manager;
	private String providerID;	
	private String originalURL;
	private Profile profile;	
	
	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}
	
	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	
	public UserSessionBean() {
		
	}	

	public void socialConnect(){
		
		// 	Put your keys and secrets from the providers here
		try{
			
			Properties props = System.getProperties();
			
			props.put("graph.facebook.com.consumer_key", FACEBOOK_APP_ID);
			props.put("graph.facebook.com.consumer_secret", FACEBOOK_APP_SECRET);
			
			// Define your custom permission if needed
			SocialAuthConfig config = SocialAuthConfig.getDefault();
			config.load(props);
			
			manager = new SocialAuthManager();
			manager.setSocialAuthConfig(config);
			
			// 'successURL' is the page you'll be redirected to on successful login
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//			"http://localhost:8080"
			String successURL = "http://localhost:8080" + externalContext.getRequestContextPath() + "/socialLoginSuccess.xhtml";
//			String successURL = "/socialLoginSuccess.xhtml";
			String authenticationURL = manager.getAuthenticationUrl(providerID, successURL);
			FacesContext.getCurrentInstance().getExternalContext().redirect(authenticationURL);
			
		}catch(Exception e){
			
		}
		
	}
	

	public void pullUserInfo() {
		
		try {
			
				// Pull user's data from the provider
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			java.util.Map<String, String> map = SocialAuthUtil.getRequestParametersMap(request);

			if (this.manager != null) {
				
				AuthProvider provider = manager.connect(map);
				this.profile = provider.getUserProfile();
				// Do what you want with the data (e.g. persist to the database, etc.)
				System.out.println("User's Social profile: " + profile);
				// Redirect the user back to where they have been before logging in
				FacesContext.getCurrentInstance().getExternalContext().redirect(originalURL);
				
			} else
				
				FacesContext.getCurrentInstance().getExternalContext().redirect(externalContext.getRequestContextPath() + "home.xhtml");
			
		} catch (Exception ex) {
			System.out.println("UserSession - Exception: " + ex.toString());
		}
		
	}


	public void logOut() {
		
		try {
			// Disconnect from the provider
			manager.disconnectProvider(providerID);
			// Invalidate session
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			this.invalidateSession(request);
			// Redirect to home page
			FacesContext.getCurrentInstance().getExternalContext().redirect(externalContext.getRequestContextPath() + "home.xhtml");
			
		} catch (IOException ex) {
			
			System.out.println("UserSessionBean - IOException: " + ex.toString());
		}
	}


	private void invalidateSession(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	

}
