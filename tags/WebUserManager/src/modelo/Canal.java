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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "canal_seq_gen")
	@SequenceGenerator(name = "canal_seq_gen", sequenceName = "canal_id_seq", allocationSize=1)
	private long id;
	
	
	private String codigo;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<PedidoMsj> pedidosMsj;
	
	private static final long serialVersionUID = 1L;

	private boolean propietario;
	
	@OneToOne(mappedBy="canal")
	private Usuario user;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
	private Aplicacion app; 
	
	@ManyToMany(mappedBy="canales", cascade = { CascadeType.MERGE, CascadeType.PERSIST})
	private List<Registro> registrados;
	
	
	public Canal() {
		super();
	}   
	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}   
	
	
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
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isPropietario() {
		return propietario;
	}
	public void setPropietario(boolean propietario) {
		this.propietario = propietario;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	
}
