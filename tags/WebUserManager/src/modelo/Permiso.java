package modelo;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

import presentacion.PermSesBean;

/**
 * Entity implementation class for Entity: Permiso
 *
 */

@Entity
public class Permiso implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perm_seq_gen")
	@SequenceGenerator(name = "perm_seq_gen", sequenceName = "perm_id_seq", allocationSize=1)
	private long id;
	private String nombre;
	private static final long serialVersionUID = 1L;

	public Permiso() {
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
	
	public PermSesBean getPermSesBean(){
		
		PermSesBean p = new PermSesBean();
		p.setId(id);
		p.setNombre(nombre);		
		return p;
		
	}
	   
}
