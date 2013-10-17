package modelo;

import java.io.Serializable;
import java.lang.String;
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
   
}
