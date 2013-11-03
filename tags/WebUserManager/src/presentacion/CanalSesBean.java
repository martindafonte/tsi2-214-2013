/**
 * 
 */
package presentacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import negocio.ServiciosLocal;

/**
 * @author bruno
 *
 */
public class CanalSesBean {
	
	
	@EJB
	private ServiciosLocal serv;
	
	private java.lang.String codigo;
	private Integer registrados;

	public CanalSesBean() {
	}

	public java.lang.String getCodigo() {
		return codigo;
	}

	public void setCodigo(java.lang.String codigo) {
		this.codigo = codigo;
	}
	
	public void borrarCanal(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		AppSesBean app = (AppSesBean) context.getExternalContext().getSessionMap().get("appSesBean");
//		List<CanalSesBean> lc = app.getCanales();
//		List<CanalSesBean> lcAux = new ArrayList<CanalSesBean>();
//		Iterator<CanalSesBean> itlc = lc.iterator();
//		while(itlc.hasNext()){
//			CanalSesBean cc =itlc.next();
//			CanalSesBean ccAux;
//			if(! cc.getCodigo().equals(this.codigo)){
//				ccAux = new CanalSesBean();
//				ccAux.setCodigo(cc.codigo);
//				ccAux.setRegistrados(new Integer(cc.registrados));
//				lcAux.add(ccAux);
//			}
//		}
		
		context.getExternalContext().getSessionMap().remove("canalSesBean");
		UserLogin user = (UserLogin)context.getExternalContext().getSessionMap().get("userLogin");
//		app.setCanales(lcAux);
		user.borrarCanal(codigo, app);
		
	}

	public Integer getRegistrados() {
		return registrados;
	}

	public void setRegistrados(Integer registrados) {
		this.registrados = registrados;
	}
	
	
	
	
}
