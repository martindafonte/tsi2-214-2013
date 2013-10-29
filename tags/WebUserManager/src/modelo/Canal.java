package modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Canal
 *
 */
@Entity
public class Canal implements Serializable {

	   
	@Id
	private String codigo;
	
	private static final long serialVersionUID = 1L;

	public Canal() {
		super();
	}   
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}   
	
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
	private Aplicacion app; 
	
	@ManyToMany(mappedBy="canales", cascade = { CascadeType.MERGE, CascadeType.PERSIST})
	private List<Registro> registrados;
	
	
	public List<Registro> getRegistrados() {
		return registrados;
	}
	
	public void setRegistrados(List<Registro> registrados) {
		this.registrados = registrados;
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
	
//	public boolean equalKey(String cod, long appid){
//		
//		return ((cod.equals(codigo)) && (appid == app.getId()));
//	}
	
	public Aplicacion getApp() {
		return app;
	}
	public void setApp(Aplicacion app) {
		this.app = app;
	}
	
	public boolean existeRegistroCanal(Registro reg){
		
		Iterator<Registro> itr = registrados.iterator();
		while(itr.hasNext()){
			if(itr.next().getRegistrer().equals(reg.getRegistrer())){
				return true;
			}
		}
		
		return false;
	}
	
}
