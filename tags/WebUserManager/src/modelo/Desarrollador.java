package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Desarrollador
 */


@Entity
public class Desarrollador implements Serializable {

	
	

	@Id
	@Column(name = "id_desarrollador")
	private long id;
	private String nick;
	private String pass;
	private String nombre;
	private String apellido;
	
	private static final long serialVersionUID = 1L;
	
	private static long autoID;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aplicacion")
	private List<Aplicacion> la;
	
	public Desarrollador(){
		id = getGenID();
	}
	
	
	public List<Aplicacion> getLa() {
		return la;
	}

	public void setLa(List<Aplicacion> la) {
		this.la = la;
	}
	
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
	
	public synchronized static long getGenID(){
		
		return autoID++;
	}
   
}
