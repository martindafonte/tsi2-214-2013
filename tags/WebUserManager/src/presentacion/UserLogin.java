/**
 * 
 */
package presentacion;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import negocio.ServiciosLocal;

/**
 * @author bruno
 *
 */
public class UserLogin {
	
	private java.lang.String nick;
	private java.lang.String pass;

//	private List<AppBean> apps;
	
	
	private Boolean login = false;
	
	public Boolean getLogin() {
		return login;
	}

	public void setLogin(Boolean login) {
		this.login = login;
	}

	
//	@PostConstruct
//	private void init(){
//	
//		Desarrollador d = serv.getDesarrollador(nick, pass);
//		List<Aplicacion> la = serv.getAplicaciones(d);
//		Iterator<Aplicacion> ita = la.iterator();
//		while(ita.hasNext()){
//			
//			Aplicacion a = ita.next();
//			AppBean ap = new AppBean();
//			ap.setDescripcion(a.getDescripcion());
//			ap.setNombre(a.getNombre());
//			apps.add(ap);
//		}
//	}
	

	@EJB
	private ServiciosLocal serv; 
	

	public UserLogin() {
//		this.login = false;
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
			System.out.println("ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return "errorPage.xhtml";			
		}
		login = true;
//		return"/MasterPage/logout.xhtml";
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String ret = req.getRequestURL().toString();
		return ret;
		
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
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String ret = req.getRequestURL().toString();
		return ret;
		
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
	
}
