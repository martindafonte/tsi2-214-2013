/**
 * 
 */
package presentacion;

import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import nz.co.kevindoran.googlechartsjsf.Column;
import nz.co.kevindoran.googlechartsjsf.DefaultGoogleChartModel;
import nz.co.kevindoran.googlechartsjsf.GoogleChartModel;
import nz.co.kevindoran.googlechartsjsf.Row;


import com.google.gson.JsonObject;

/**
 * @author bruno
 * 
 */

public class AppSesBean {

	private Long aplicacionid;
	private String nombre;
	private Integer num;

	public GoogleChartModel getPedidos() {
		UserLogin u = getUserLogin();
		List<PedSesBean> lp = u.getPedidos(aplicacionid);
		GoogleChartModel chartModel = new DefaultGoogleChartModel("corechart","PieChart");
		chartModel.addColumn(new Column(Column.JavaScriptType.string,"Servicio"));
		chartModel.addColumn(new Column(Column.JavaScriptType.number,"Cantidad de Pedidos"));
		int noOfRows = 2;
		Iterator<PedSesBean> it = lp.iterator();
		PedSesBean p;
		while (it.hasNext()) {
			p = it.next();
			Row row = new Row(noOfRows);
			String aux = "\'" + p.getServicio() + "\'"; 
			row.addEntry(aux);
			row.addEntry(p.getCantidad().toString());
			chartModel.addRow(row);
		}		
		chartModel.setOptions("'title' : 'Cantidad de pedidos HTTP realizados por Servicio','width' : 400,'height' : 300"); 
		return chartModel;
	}

	public JsonObject getPedidosJson() {
		UserLogin u = getUserLogin();
		return u.getPedidosJson(aplicacionid);
	}

	public List<RolSesBean> getRoles() {

		UserLogin user = this.getUserLogin();
		List<RolSesBean> lr = user.getRoles(aplicacionid);
		if (lr != null && lr.size() > 0) {

			RolSesBean r = lr.get(0);
			FacesContext context = FacesContext.getCurrentInstance();
			if (!context.getExternalContext().getSessionMap().containsKey("rolSesBean")) {
						
				context.getExternalContext().getSessionMap()
						.remove("rolSesBean");
				context.getExternalContext().getSessionMap()
						.put("rolSesBean", r);
			}

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

	public String showAppInfo() {

		AppSesBean app = this.clone();
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("appSesBean", app);
		context.getExternalContext().getSessionMap().remove("rolSesBean");

		return "/WebUserManager/showAplicaciones.xhtml";
	}

	public List<CanalSesBean> getCanales() {
		UserLogin user = this.getUserLogin();
		return user.getCanales(aplicacionid);
	}

	public void changeSingleLogin() {

		UserLogin user = this.getUserLogin();
		user.cambiarSingleLogin(aplicacionid, singleLogin);
		this.refresh();
	}

	public AppSesBean clone() {

		AppSesBean app = new AppSesBean();
		app.aplicacionid = new Long(aplicacionid.longValue());
		app.descripcion = new String(descripcion);
		app.nombre = new String(nombre);
		app.num = num;
		app.singleLogin = new Boolean(singleLogin.booleanValue());

		return app;

	}

	public void refresh() {

		UserLogin user = this.getUserLogin();
		user.refresh();

	}

	private UserLogin getUserLogin() {

		FacesContext context = FacesContext.getCurrentInstance();
		return (UserLogin) context.getExternalContext().getSessionMap()
				.get("userLogin");
	}
}
