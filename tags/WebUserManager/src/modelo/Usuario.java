package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;



/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
//@TableGenerator(table="usuario", name="usuario", initialValue=0, allocationSize=1000)
public class Usuario implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
	@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_id_seq")
	private long id;
	private String nick;
	private String pass;
	private String nombre;
	private String apellido;	

	public Usuario() {
	}
	
//	@OneToMany(mappedBy="d")
//	private List<Aplicacion> la;
//	
//	
//	public List<Aplicacion> getLa() {
//		return la;
//	}
//
//	public void setLa(List<Aplicacion> la) {
//		this.la = la;
//	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}