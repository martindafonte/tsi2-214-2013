/**
 * 
 */
package presentacion;

import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

import persistencia.ConstantesPersistencia;
import utiles.FacebookProperties;

import com.google.gson.JsonObject;

import negocio.ServiciosLocal;

/**
 * @author bruno
 *
 */
public class UserLogin {
	
	private String FACEBOOK_APP_ID = "712326902129246";
	private String FACEBOOK_APP_SECRET = "dabec4c840e8a68b338c21cd68b8b182";
	
	private SocialAuthManager manager;
	private String providerID;
	
	private String originalURL;
	private Profile profile;
	
	public String getFACEBOOK_APP_ID() {
		return FACEBOOK_APP_ID;
	}

	public void setFACEBOOK_APP_ID(String fACEBOOK_APP_ID) {
		FACEBOOK_APP_ID = fACEBOOK_APP_ID;
	}

	public String getFACEBOOK_APP_SECRET() {
		return FACEBOOK_APP_SECRET;
	}

	public void setFACEBOOK_APP_SECRET(String fACEBOOK_APP_SECRET) {
		FACEBOOK_APP_SECRET = fACEBOOK_APP_SECRET;
	}

	public SocialAuthManager getManager() {
		return manager;
	}

	public void setManager(SocialAuthManager manager) {
		this.manager = manager;
	}


	private java.lang.String nick;
	private java.lang.String pass;
	private Integer appActual;

	
	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
	private Boolean login = false;
	
	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}
	

	@EJB
	private ServiciosLocal serv;

	
	public List<AppSesBean> getApps(){
		
		List<AppSesBean> la = null;
		if ((providerID != null) && providerID.equals("facebook") ){
			if (!login){
//				pullUserInfo();
			}
			la =  serv.getAplicaciones(nick, pass, providerID);
			
		}else{
			
			la =  serv.getAplicaciones(nick, pass);
		}		
		
		if((la != null && la.size() > 0 )&&
		  (! FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("appSesBean"))){
			
			AppSesBean ap = la.get(0);
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("appSesBean");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("appSesBean", ap);
				
		}
		
		return la;
	}
	

	public UserLogin() {
	}
	
	public java.lang.String getNick() {
		return nick;
	}

	public void setNick(java.lang.String nick) {
		this.nick = nick;
	}

	public java.lang.String getPass() {
		return pass;
	}

	public void setPass(java.lang.String pass) {
		this.pass = pass;
	}
	
	public String login(){
		
		Boolean error = serv.existeDesarollador(nick, pass);
		if(!error){
			return "errorPage.xhtml";			
		}
		providerID = "baas";
		login = true;
		return "/showAplicaciones.xhtml";
		
	}
	
	public String logout(){
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String ret = req.getRequestURL().toString();
		return ret;
	}
	
	public String logout2(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("userLogin");
		login = false;
		if( providerID.equals("facebook")){
			logOut();
		}
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		((HttpSession) externalContext.getSession(true)).invalidate();
		
		return "/index.xhtml";
		
	}
	
	
	public String go(){
		
		if (login == null){
			login = new Boolean(false);			
		}

		if (login){
			
			return "/MasterPage/logout.xhtml";
		}		
		
		return "/MasterPage/login.xhtml";
	}

	public Integer getAppActual() {
		return appActual;
	}

	public void setAppActual(Integer appActual) {
		this.appActual = appActual;
	}
	
	public void borrarCanal(String codigo, AppSesBean app){
		serv.borrarCanal(codigo, app);
//		apps = serv.getAplicaciones(nick, pass);
	}
	
	public void cambiarSingleLogin(long id , boolean valor){
		
		serv.cambiarSingleLogin(id, valor);
	}
	
	public void refresh(){
		
//		apps = serv.getAplicaciones(nick, pass);
	}
	
	public void refresh(AppSesBean app){
		
	}
	
	
	public int agregarRol(String nombre, long appId){
		
//		int res = serv.agregarRol(nombre, appId);
		return serv.agregarRol(nombre, appId);
		
	}
	
	public void agregarPermiso(String nombre, long rolId){
		
//		int res = serv.agregarPermisoRol(nombre, rolId);
		serv.agregarPermisoRol(nombre, rolId);
		
	}
	
	public int altaPermiso(String nombre, long appId){
		
		FacesContext context = FacesContext.getCurrentInstance();
		AppSesBean ap = (AppSesBean)context.getExternalContext().getSessionMap().get("appSesBean");
		if ( ap != null ){
			
			return serv.altaPermiso(nombre, ap.getAplicacionid().longValue());
		}
		
		return ConstantesPersistencia.Error;
	}
	
	public List<RolSesBean> getRoles(long id){
		
		List<RolSesBean> lr = serv.getRoles(id);
		if((lr != null && lr.size() > 0)&&(! FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("rolSesBean")))
		{
			
			RolSesBean r = lr.get(0);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rolSesBean", r);
		}
		return lr;
		
	}

	
	public List<CanalSesBean> getCanales(long id){
		
		return serv.getCanales(id);
	}
	
	public List<PermSesBean> getPermsDontHave(long rolId){

		return serv.getPermsDontHave(rolId);
	}
	
	public List<PermSesBean> getPermsHave(long rolId){
		
		return serv.getPermsHave(rolId);
	}
	
	public void agregarPermiso(long permId, long rolId){
		
		serv.agregarPermisoRol(permId, rolId);
	}
	
	public void quitarPermiso(long permId, long rolId){
		
		serv.quitarPermisoRol(permId, rolId);
	}
	
	public int hayRol(){
		RolSesBean r = (RolSesBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("rolSesBean"); 
		if (r != null){
			return 1;
		}
		return 0;
		
	}
	
	public List<PedSesBean> getPedidos(long appId){
		return serv.getPedidos(appId);
	}
	
	public JsonObject getPedidosJson(long appId){
		return serv.getPedidosJson(appId);
	}
	
	public Integer getMensajes(long app, String codigo){
		return serv.getMensajes(app, codigo);
	}
	
	
	public Boolean showAplicaciones(){
		
		return new Boolean(this.getApps().size() > 0);
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
			
			providerID = new String("facebook");
			
			// 'successURL' is the page you'll be redirected to on successful login
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//			"http://localhost:8080"
			String url = FacebookProperties.getProperties();
			String successURL = url + externalContext.getRequestContextPath();
//			successURL += "/showAplicaciones.xhtml";
			successURL += "/socialLoginSuccess.xhtml";
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
				
				//dar de alta al usuario si es que no existe
				this.nick = this.profile.getEmail();
				this.pass = "";
				this.providerID = this.profile.getProviderId();
				this.login = true;
			
				
				serv.altaDesarrollador(nick, pass, this.profile.getFirstName(), this.profile.getLastName(), "facebook");
				
				// Do what you want with the data (e.g. persist to the database, etc.)
				System.out.println("User's Social profile: " + profile);
				// Redirect the user back to where they have been before logging in
				FacesContext.getCurrentInstance().getExternalContext().redirect("showAplicaciones.xhtml");				
				
			} else
				
				FacesContext.getCurrentInstance().getExternalContext().redirect(externalContext.getRequestContextPath() + "index.xhtml");
			
		} catch (Exception ex) {
			System.out.println("UserSession - Exception: " + ex.toString());
		}
		
	}
	
	public void logOut() {
		
		// Disconnect from the provider
		manager.disconnectProvider(providerID);
		// Invalidate session
//		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
////		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//		((HttpSession) externalContext.getSession(true)).invalidate();
//			this.invalidateSession(request);
		// Redirect to home page
//			FacesContext.getCurrentInstance().getExternalContext().redirect(externalContext.getRequestContextPath() + "/index.xhtml");
	}
	
//	private void invalidateSession(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public List<PedSesBean> cantPedidosAPI(long aplicacionid){
		
		return serv.cantPedidosAPI(aplicacionid);
	}
	
	public List<PedSesBean> cantDespRegCanales(long appId){
		
		return serv.cantDespRegCanales(appId);
	}
	
	public List<PedSesBean> cantMsjEnvPush(long appId){
		
		return serv.cantMsjEnvPush(appId);
	}

	public int getCantUsuarios(Long aplicacionid) {

		return serv.getCantUsuarios(aplicacionid);
	}
	
		
	
}
