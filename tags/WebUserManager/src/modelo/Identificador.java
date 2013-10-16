package modelo;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Identificador
 *
 */

@Entity
public class Identificador implements Serializable {

	   
	@Id
	private String name;
	private long value;
	private static final long serialVersionUID = 1L;

	public Identificador() {
		super();
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public long getValue() {
		return this.value;
	}

	public void setValue(long value) {
		this.value = value;
	}
   
}
