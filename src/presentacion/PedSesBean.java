package presentacion;

import javax.ejb.EJB;

import negocio.ServiciosLocal;

public class PedSesBean {

	@EJB
	private ServiciosLocal serv;
	
	private String metodo;
	private String servicio;
	private Integer cantidad;
	
	public PedSesBean() {
	}
	
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
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
