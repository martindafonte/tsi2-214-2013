package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AplicacionJson
 *
 */
@Entity

public class AplicacionJson implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_json_seq_gen")
	@SequenceGenerator(name = "app_json_seq_gen", sequenceName = "app_json_id_seq")
	private long id;
	private static final long serialVersionUID = 1L;

	public AplicacionJson() {
		super();
	}   
	
	
	@OneToMany
	private List<Pedido> pedidos;
	
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
   
}
