package modelo;

import java.io.Serializable;
import java.lang.String;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: Pedido
 *
 */
@Entity
public class Pedido implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq_gen")
	@SequenceGenerator(name = "pedido_seq_gen", sequenceName = "pedido_id_seq")
	private long id;
	private String ip;
	private String url;
	private String method;
	private static final long serialVersionUID = 1L;
	
	public Pedido() {
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
   
}
