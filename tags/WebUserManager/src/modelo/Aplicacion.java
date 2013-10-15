package modelo;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Aplicacion
 *
 */
@Entity
public class Aplicacion implements Serializable {

	
	private String nombre;
	
	@Id
	@GeneratedValue
	@Column(name="id_aplicacion")
	private long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_desarrollador")
	private Desarrollador d;
	
	
	public Desarrollador getD() {
		return d;
	}
	public void setD(Desarrollador d) {
		this.d = d;
	}
	private static final long serialVersionUID = 1L;
	
	public Aplicacion() {
		super();
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
	

   
}
