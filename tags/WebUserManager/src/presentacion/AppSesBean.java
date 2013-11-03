/**
 * 
 */
package presentacion;

import java.util.Iterator;
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
	
//	private List<CanalSesBean> canales;
//	private List<RolSesBean> roles;
//	
//	public List<RolSesBean> getRoles() {
//		return roles;
//	}
//
//	public void setRoles(List<RolSesBean> roles) {
//		this.roles = roles;
//	}

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

//	public List<CanalSesBean> getCanales() {
//		return ;
//	}
//
//	public void setCanales(List<CanalSesBean> canales) {
//		this.canales = canales;
//	}
	
	public void changeSingleLogin(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		UserLogin user = (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
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
		CanalSesBean ca = null;
		CanalSesBean c = null;
//		app.canales = new ArrayList<CanalSesBean>();
//		Iterator<CanalSesBean> itc = canales.iterator();
//		while(itc.hasNext()){
//			c = itc.next();
//			ca = new CanalSesBean();
//			ca.setCodigo(c.getCodigo());
//			ca.setRegistrados(c.getRegistrados());
////			app.canales.add(ca);
//		}
//		
//		app.roles = new ArrayList<RolSesBean>(this.roles);
		
		return app;
		
	}
	
	public void refresh(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		UserLogin user = (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
		user.refresh();
		Iterator<AppSesBean> ita = user.getApps().iterator();
		AppSesBean ap = null;
		while(ita.hasNext()){
			
			ap = ita.next();
			if(ap.getAplicacionid().longValue() == aplicacionid.longValue()){
				
//				canales = new ArrayList<CanalSesBean>(ap.getCanales());
//				roles = new ArrayList<RolSesBean>(ap.getRoles());
			}
			
		}
		
	}
}
