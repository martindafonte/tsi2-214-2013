/**
 * 
 */
package presentacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import persistencia.ConstantesPersistencia;

import com.google.gson.JsonObject;

import negocio.ServiciosLocal;

/**
 * @author bruno
 *
 */
public class UserLogin {
	
	private java.lang.String nick;
	private java.lang.String pass;
	private Integer appActual;

	
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
		
		List<AppSesBean> la =  serv.getAplicaciones(nick, pass);
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
		login = true;
//		apps = serv.getAplicaciones(nick, pass);

//		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String ret = req.getRequestURL().toString();
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
//		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		String ret = req.getRequestURL().toString();
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
		
	
}
