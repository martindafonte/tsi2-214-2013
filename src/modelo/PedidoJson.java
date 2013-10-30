package modelo;

import java.sql.Timestamp;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class PedidoJson implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidojson_seq_gen")
	@SequenceGenerator(name = "pedidojson_seq_gen", sequenceName = "pedidojson_id_seq")
	private long id;
	private String url;
	private String method;
	private int jsonId;
	private Timestamp time;
	
	private static final long serialVersionUID = 1L;
	
	public PedidoJson() {
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
	
	
	public int getJsonId() {
		return jsonId;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setJsonId(int jsonId) {
		this.jsonId = jsonId;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
