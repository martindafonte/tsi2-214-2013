package modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Aplicacion
 *
 */
@Entity
public class Aplicacion implements Serializable {

	
	private String nombre;
	private String descripcion;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_seq_gen")
	@SequenceGenerator(name = "app_seq_gen", sequenceName = "app_id_seq")
	private long id;

	private long jsonid;
	
	private boolean singleLogin;
	
	public boolean isSingleLogin() {
		return singleLogin;
	}
	public void setSingleLogin(boolean singleLogin) {
		this.singleLogin = singleLogin;
	}

	@ManyToOne
	private Desarrollador d;
	
	@OneToMany(mappedBy="app")
	private List<Canal> canales;
	
	@OneToMany
	private List<Pedido> pedidosJson;
	
	@OneToMany
	private List<Pedido> pedidosPush;
	
	@OneToMany
	private List<Pedido> pedidosUM;
	
	
	public List<Canal> getCanales() {
		return canales;
	}
	public void setCanales(List<Canal> canales) {
		this.canales = canales;
	}
	public Desarrollador getD() {
		return d;
	}
	public void setD(Desarrollador d) {
		this.d = d;
	}
	
	private static final long serialVersionUID = 1L;
	
	public Aplicacion() {
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}   
	
	public List<Pedido> getPedidosJson() {
		return pedidosJson;
	}
	public void setPedidosJson(List<Pedido> pedidosJson) {
		this.pedidosJson = pedidosJson;
	}
	public long getJsonid() {
		return jsonid;
	}
	public void setJsonid(long jsonid) {
		this.jsonid = jsonid;
	}
	
	public List<Pedido> getPedidosPush() {
		return pedidosPush;
	}
	public void setPedidosPush(List<Pedido> pedidosPush) {
		this.pedidosPush = pedidosPush;
	}
	public List<Pedido> getPedidosUM() {
		return pedidosUM;
	}
	public void setPedidosUM(List<Pedido> pedidosUM) {
		this.pedidosUM = pedidosUM;
	}
	
	public synchronized long getJSONID(){
		return jsonid++;
	}
}
