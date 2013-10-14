package modelo;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity

public abstract class Persona implements Serializable {

	   
	@Id
	@GeneratedValue
	@Column(name = "id_persona")
	private long id;
	private String nick;
	private String pass;
	private String nombre;
	private String apellido;
	private static final long serialVersionUID = 1L;

	public Persona() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}   
	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public abstract Boolean soyDesarrollador();
   
}
