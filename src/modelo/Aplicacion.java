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
	
	
	@ManyToOne
	private Desarrollador d;
	
	@OneToMany(mappedBy="aplicacion")
	private List<Canal> canales;
	
	
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
   
}
