/**
 * 
 */
package presentacion;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import negocio.ServiciosLocal;

/**
 * @author bruno
 *
 */
public class CanalBean {
	
	@EJB
	private ServiciosLocal serv;
	
	private java.lang.String codigo;

	public CanalBean() {
		
	}

	public java.lang.String getCodigo() {
		return codigo;
	}

	public void setCodigo(java.lang.String codigo) {
		this.codigo = codigo;
		
	}
	
	
	public void altaCanal(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		AppSesBean app = (AppSesBean)context.getExternalContext().getSessionMap().get("appSesBean");
		serv.crearCanal(codigo, app);
//		CanalSesBean capp = new CanalSesBean();
//		capp.setCodigo(codigo);
//		capp.setRegistrados(0);
//		context.getExternalContext().getSessionMap().put("canalSesBean", capp);
//		if(app.getCanales() == null){
//			List<CanalSesBean> lc = new ArrayList<CanalSesBean>();
//			app.setCanales(lc);
//		}
//		app.getCanales().add(capp);
		
		UserLogin user = (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
		user.refresh();
		context.getExternalContext().getSessionMap().put("userLogin", user);
		app.refresh();
		context.getExternalContext().getSessionMap().put("appSesBean", app);

	}
}
