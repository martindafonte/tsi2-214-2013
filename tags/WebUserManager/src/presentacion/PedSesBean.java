package presentacion;

import javax.ejb.EJB;

import negocio.ServiciosLocal;

public class PedSesBean {

	@EJB
	private ServiciosLocal serv;
	
	private String servicio;
	private Integer cantidad;
	
	public PedSesBean() {
	}
	
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
