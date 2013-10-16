package modelo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Desarrollador
 */

@Entity
@TableGenerator(name="desarrollador", initialValue=0, allocationSize=1000)
public class Desarrollador implements Serializable {

	
	

	@Id
	@Column(name = "id_desarrollador")
	@GeneratedValue(strategy=GenerationType.AUTO , generator="desarrollador")
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
//		id = getGenID();
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
