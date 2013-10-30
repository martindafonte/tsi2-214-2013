package modelo;

import java.sql.Timestamp;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class PedidoMsj implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedidomsj_seq_gen")
	@SequenceGenerator(name = "pedidomsj_seq_gen", sequenceName = "pedidomsj_id_seq")
	private long id;
	private Timestamp time;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Canal canal;
	
	public PedidoMsj() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Canal getCanal() {
		return canal;
	}
	public void setCanal(Canal canal) {
		this.canal = canal;
	}
	
}
