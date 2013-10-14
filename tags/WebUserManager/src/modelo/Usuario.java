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
public class Usuario extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aplicacion")
	private List<Aplicacion> la;
	
	
	public Usuario() {
	}
	
	
	public List<Aplicacion> getLa() {
		return la;
	}

	public void setLa(List<Aplicacion> la) {
		this.la = la;
	}



	@Override
	public Boolean soyDesarrollador() {
		// TODO Auto-generated method stub
		return false;
	}
	
}