package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;



/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
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
	
	@OneToOne(cascade=CascadeType.ALL)
	private Canal canal;

	
	@ManyToOne(cascade=CascadeType.ALL)
	private Aplicacion aplicacion;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Rol> roles;
	
	
	public List<Rol> getRoles() {
		return roles;
	}


	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}


	public Aplicacion getAplicacion() {
		return aplicacion;	}


	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	

	public Usuario() {
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
	
	public Canal getCanal() {
		return canal;
	}


	public void setCanal(Canal canal) {
		this.canal = canal;
	}
	

	public void quitarRolUsuario(long rolId){
		
		List<Rol> lr = new ArrayList<Rol>();
		Iterator<Rol> itr = roles.iterator();
		Rol r = null;
		while(itr.hasNext()){
			r = itr.next();
			if(r.getId() != rolId){

				lr.add(r);
			}
		}
		
		roles = new ArrayList<>(lr);
		
	}
	
	public void agregarRolUsuario(Rol r){
		
		boolean esta = false;
		Rol ro;
		Iterator<Rol> itr = roles.iterator(); 
		while(itr.hasNext()){
			ro = itr.next();
			if(ro.getId() == r.getId()){
				esta = true;
				break;
			}			
		}
		
		if (! esta){
			
			roles.add(r);
			
		}
				
	}
	
}