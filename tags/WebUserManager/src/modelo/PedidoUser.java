package modelo;

import java.sql.Timestamp;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class PedidoUser implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidouser_seq_gen")
	@SequenceGenerator(name = "pedidouser_seq_gen", sequenceName = "pedidouser_id_seq", allocationSize=1)
	private long id;
	private String url;
	private String method;
	private Timestamp time;
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Usuario usuario;
	
	public PedidoUser() {
		super();
	}  
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}   
	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
