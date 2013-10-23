package modelo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

import modelo.Aplicacion;

/**
 * Entity implementation class for Entity: Canal
 *
 */
@Entity
public class Canal implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "canal_seq_gen")
	@SequenceGenerator(name = "canal_seq_gen", sequenceName = "canal_id_seq")
	private long id;
	
	private String codigo;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@ManyToOne
	private Aplicacion aplicacion;
	
	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy="canales")
	private List<Registro> registrados;
	
	
	public List<Registro> getRegistrados() {
		return registrados;
	}
	public void setRegistrados(List<Registro> registrados) {
		this.registrados = registrados;
	}
	public Canal() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public Aplicacion getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	public void quitarRegistro(Registro r){
		
		Iterator<Registro> itlr = registrados.iterator();
		int index = 0;
		while(itlr.hasNext()){
			if( itlr.next().getId() == r.getId()){
				registrados.remove(index);
				return;
			}
			index++;
		}
		
	}
	
   
}
