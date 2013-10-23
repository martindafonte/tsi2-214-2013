package modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Registro
 *
 */
@Entity
public class Registro implements Serializable {

	   
	@Id
	private long id;
	private String registrer;
	private static final long serialVersionUID = 1L;
	
	
	@ManyToMany
	private List<Canal> canales;

	public List<Canal> getCanales() {
		return canales;
	}
	public void setCanales(List<Canal> canales) {
		this.canales = canales;
	}
	public Registro() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public String getRegistrer() {
		return this.registrer;
	}

	public void setRegistrer(String registrer) {
		this.registrer = registrer;
	}
   
	public void quitarCanal(Canal c){
		
		Iterator<Canal> itlc = canales.iterator();
		int index = 0;
		while(itlc.hasNext()){
			if( itlc.next().getId() == c.getId()){
				canales.remove(index);
				return;
			}
			index++;
		}
		
	}
	
}
