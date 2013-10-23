package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: AplicacionUserManager
 *
 */
@Entity
public class AplicacionUserManager implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_seq_gen")
	@SequenceGenerator(name = "app_user_seq_gen", sequenceName = "app_user_id_seq")
	private long id;
	private static final long serialVersionUID = 1L;

	public AplicacionUserManager() {
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
