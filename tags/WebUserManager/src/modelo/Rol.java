package modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.*;

import presentacion.PermSesBean;
import presentacion.RolSesBean;

/**
 * Entity implementation class for Entity: Rol
 *
 */
@Entity
public class Rol implements Serializable {
	   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rol_seq_gen")
	@SequenceGenerator(name = "rol_seq_gen", sequenceName = "rol_id_seq")
	private long id;
	private String nombre;
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade={CascadeType.MERGE , CascadeType.REFRESH , CascadeType.PERSIST})
	private Aplicacion aplicacion;
	
	@ManyToMany
	private List<Permiso> perms;
	
	public Aplicacion getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;
	}

	public List<Permiso> getPerms() {
		return perms;
	}

	public void setPerms(List<Permiso> perms) {
		this.perms = perms;
	}

	public Rol() {
		super();
	}
	
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public RolSesBean getRolSesBean(){
		
				
		
		RolSesBean r = new RolSesBean();
		r.setId(id);
		r.setNombre(nombre);

		
		return r;
		
	}
	
	public List<PermSesBean> getPermsHave(){
		
		List<PermSesBean> lp = new ArrayList<PermSesBean>();
		Iterator<Permiso> itp = perms.iterator();
		while(itp.hasNext()){
			
			lp.add(itp.next().getPermSesBean());			
		}
		
		return lp;
				
	}
	
	public List<PermSesBean> getPermsDontHave(){
		
		Map<Long, PermSesBean> mperm = new TreeMap<Long,PermSesBean>();
		Iterator<Permiso> itp = aplicacion.getPermisos().iterator();
		PermSesBean pses = null;
		Permiso p = null;
		// agrego todos los permsesbean
		while(itp.hasNext()){
			
			p = itp.next();
			pses = p.getPermSesBean();
			mperm.put(pses.getId(), pses);
		}
		
		List<PermSesBean> lp = new ArrayList<PermSesBean>();
		List<PermSesBean> lpdnh = new ArrayList<PermSesBean>();

		itp = perms.iterator();
		while(itp.hasNext()){
			
			p = itp.next();
			pses = p.getPermSesBean();
			lp.add(pses);
			mperm.remove(pses.getId());
		}

		lpdnh.addAll(mperm.values());
		
		return lpdnh;

		
	}
	
	public void agregarPermiso(Permiso p){
		
		
		boolean esta = false;
		Iterator<Permiso> itp = perms.iterator();
		while(itp.hasNext()){
			
			if(itp.next().getId() == p.getId()){
				
				esta = true;
				break;
			}
			
		}
		
		if(!esta){
			perms.add(p);
		}
		
		
	}
	
	public void quitarPermiso(Permiso p){
		
		Iterator<Permiso> itp = perms.iterator();
		List<Permiso> lp = new ArrayList<>();
		Permiso pAux = null;
		while(itp.hasNext()){
			pAux = itp.next();
			if(pAux.getId() != p.getId()){
				
				lp.add(pAux);
			}
			
		}
		
		perms = lp;
	}
	
	public void quitarPermiso(String nombre){
		
		Iterator<Permiso> itp = perms.iterator();
		List<Permiso> lp = new ArrayList<>();
		Permiso pAux = null;
		while(itp.hasNext()){
			pAux = itp.next();
			if(! nombre.equals(pAux.getNombre())){
				
				lp.add(pAux);
			}
			
		}
		
		perms = lp;
	}
   
}
