package modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Rol
 *
 */
@Entity
public class Rol implements Serializable {
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_seq_gen")
	@SequenceGenerator(name = "rol_seq_gen", sequenceName = "rol_id_seq")
	private long id;
	private String nombre;
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade={CascadeType.MERGE , CascadeType.REFRESH , CascadeType.PERSIST})
	private Aplicacion aplicacion;
	
	@OneToMany
	private List<Permiso> perms;
	
	public Rol() {
		super();
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
   
}
