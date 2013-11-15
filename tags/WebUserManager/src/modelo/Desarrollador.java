package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.*;


/**
 * Entity implementation class for Entity: Desarrollador
 */

@Entity
public class Desarrollador implements Serializable {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "des_seq_gen")
	@SequenceGenerator(name = "des_seq_gen", sequenceName = "des_id_seq", allocationSize=1)
	private long id;
	
	private String nick;
	private String pass;
	private String nombre;
	private String apellido;
	private String providerID;
	
	public String getProviderID() {
		return providerID;
	}


	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy ="d", cascade=CascadeType.ALL)
	private Collection<Aplicacion> la;
	
	public Collection<Aplicacion> getLa() {
		return la;
	}


	public void setLa(Collection<Aplicacion> la) {
		this.la = la;
	}

	public Desarrollador(){
	}
	
	@PostConstruct
	public void init(){
		
		la = new ArrayList<Aplicacion>();
		
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
	
	
	public List<Aplicacion> getAplicaciones(String nombre){
		
		List<Aplicacion> laRes = new LinkedList<Aplicacion>();
		Iterator<Aplicacion> it = this.la.iterator();
		while (it.hasNext()){
			
			Aplicacion a = it.next();
			if (a.getNombre().equals(nombre)){
				
				laRes.add(a);
			}			
		}
		
		return laRes;
		
	} 	
	
	public List<Aplicacion> filtrarAplicaciones(String nombre){
		
		List<Aplicacion> laRes = new LinkedList<Aplicacion>();
		Iterator<Aplicacion> it = this.la.iterator();
		while (it.hasNext()){
			
			Aplicacion a = it.next();
			if (a.getNombre().startsWith(nombre)){
				
				laRes.add(a);
			}			
		}
		
		return laRes;
		
	}
	   
}
