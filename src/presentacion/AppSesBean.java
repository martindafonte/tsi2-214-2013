/**
 * 
 */
package presentacion;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import negocio.ServiciosLocal;

/**
 * @author bruno
 *
 */

public class AppSesBean {
	
	@EJB
	private ServiciosLocal serv;
	
	private Long aplicacionid;
	private String nombre;
	private Integer num;
	
	public List<PedSesBean> getPedidos() {
		return serv.getPedidos(aplicacionid);
	}

	public List<RolSesBean> getRoles() {
		
		UserLogin user = this.getUserLogin();
		List<RolSesBean> lr =  user.getRoles(aplicacionid);
		if(lr!= null && lr.size() > 0){
			
			RolSesBean r = lr.get(0);
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("rolSesBean",r);
			
		}
		return lr;
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getSingleLogin() {
		return singleLogin;
	}

	public void setSingleLogin(Boolean singleLogin) {
		this.singleLogin = singleLogin;
	}

	private String descripcion;
	private Boolean singleLogin; 

	public Long getAplicacionid() {
		return aplicacionid;
	}

	public void setAplicacionid(Long aplicacionid) {
		this.aplicacionid = aplicacionid;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	public String showAppInfo(){
		
		
		AppSesBean app = this.clone();
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("appSesBean", app);
		
		return "/infoAplicacion.xhtml";
	}

	public List<CanalSesBean> getCanales() {
		UserLogin user = this.getUserLogin();
		return user.getCanales(aplicacionid);
	}
	
	public void changeSingleLogin(){
		
		UserLogin user = this.getUserLogin();
		user.cambiarSingleLogin(aplicacionid , singleLogin);
		this.refresh();
	}
	
	
	public AppSesBean clone(){
		
		AppSesBean app = new AppSesBean();
		app.aplicacionid = new Long(aplicacionid.longValue());
		app.descripcion = new String(descripcion);
		app.nombre = new String(nombre);
		app.num = num;
		app.singleLogin = new Boolean(singleLogin.booleanValue());
	
		
		return app;
		
	}
	
	
	public void refresh(){
		
		UserLogin user = this.getUserLogin();
		user.refresh();
		
	}
	
	private UserLogin getUserLogin(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		return (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
	}
}
