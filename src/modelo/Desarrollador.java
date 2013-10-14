package modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import modelo.Persona;

/**
 * Entity implementation class for Entity: Desarrollador
 *
 */
@Entity

public class Desarrollador extends Persona implements Serializable {

	
	private static final long serialVersionUID = 1L;

	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aplicacion")
	private List<Aplicacion> la;
	
	public Desarrollador() {
		super();
	}

	@Override
	public Boolean soyDesarrollador() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public List<Aplicacion> getLa() {
		return la;
	}

	public void setLa(List<Aplicacion> la) {
		this.la = la;
	}
   
}
