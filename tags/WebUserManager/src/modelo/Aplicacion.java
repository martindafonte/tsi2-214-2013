package modelo;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.*;

import persistencia.ConstantesPersistencia;
import presentacion.CanalSesBean;
import presentacion.PedSesBean;
import presentacion.PermSesBean;
import presentacion.RolSesBean;

/**
 * Entity implementation class for Entity: Aplicacion
 * 
 */
@Entity
public class Aplicacion implements Serializable {

	private String nombre;
	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_seq_gen")
	@SequenceGenerator(name = "app_seq_gen", sequenceName = "app_id_seq", allocationSize = 1)
	private long id;

	private long jsonid;

	private boolean singleLogin;

	public boolean isSingleLogin() {
		return singleLogin;
	}

	public void setSingleLogin(boolean singleLogin) {
		this.singleLogin = singleLogin;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "aplicacion")
	private List<Rol> roles;

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	@OneToMany(mappedBy = "aplicacion")
	private List<Usuario> users;

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}

	@OneToMany(cascade = CascadeType.ALL)
	private List<Permiso> permisos;

	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private Desarrollador d;

	@OneToMany(mappedBy = "app", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Canal> canales;

	@OneToMany(cascade = CascadeType.ALL)
	private List<PedidoJson> pedidosJson;

	@OneToMany(cascade = CascadeType.ALL)
	private List<PedidoPush> pedidosPush;

	@OneToMany(cascade = CascadeType.ALL)
	private List<PedidoUser> pedidosUM;

	@OneToMany(cascade = CascadeType.ALL)
	private List<PedidoMsj> pedidosMsj;

	public List<Canal> getCanales() {
		return canales;
	}

	public void setCanales(List<Canal> canales) {
		this.canales = canales;
	}

	public Desarrollador getD() {
		return d;
	}

	public void setD(Desarrollador d) {
		this.d = d;
	}

	private static final long serialVersionUID = 1L;

	public Aplicacion() {
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<PedidoJson> getPedidosJson() {
		return pedidosJson;
	}

	public void setPedidosJson(List<PedidoJson> pedidosJson) {
		this.pedidosJson = pedidosJson;
	}

	public long getJsonid() {
		return jsonid;
	}

	public void setJsonid(long jsonid) {
		this.jsonid = jsonid;
	}

	public List<PedidoMsj> getPedidosMsj() {
		return pedidosMsj;
	}

	public void setPedidosMsj(List<PedidoMsj> pedidosMsj) {
		this.pedidosMsj = pedidosMsj;
	}

	public List<PedidoPush> getPedidosPush() {
		return pedidosPush;
	}

	public void setPedidosPush(List<PedidoPush> pedidosPush) {
		this.pedidosPush = pedidosPush;
	}

	public List<PedidoUser> getPedidosUM() {
		return pedidosUM;
	}

	public void setPedidosUM(List<PedidoUser> pedidosUM) {
		this.pedidosUM = pedidosUM;
	}

	public synchronized long getJSONID() {
		return jsonid++;
	}

	public List<CanalSesBean> getCanalesAppSes() {
		List<CanalSesBean> la = new ArrayList<CanalSesBean>();
		if (canales != null) {
			Iterator<Canal> itc = canales.iterator();
			while (itc.hasNext()) {
				Canal ca = itc.next();
				if (! ca.isPropietario()) {
					CanalSesBean cs = new CanalSesBean();
					cs.setCodigo(ca.getCodigo());
					cs.setRegistrados(ca.getRegistrados().size());
					la.add(cs);
				}
			}

		}
		return la;
	}

	public Canal quitarCanal(String codigo) {

		List<Canal> lc = new ArrayList<Canal>();
		Iterator<Canal> itc = canales.iterator();
		Canal c = null;
		Canal rest = null;
		while (itc.hasNext()) {

			c = itc.next();
			if (!codigo.equals(c.getCodigo())) {
				lc.add(c);
			} else {
				rest = c;
			}
		}

		canales = lc;
		return rest;

	}

	public List<RolSesBean> getRolSesBeans() {

		List<RolSesBean> lr = new ArrayList<RolSesBean>();
		Iterator<Rol> itr = roles.iterator();
		while (itr.hasNext()) {

			lr.add(itr.next().getRolSesBean());

		}

		return lr;

	}

	public List<PermSesBean> getPermSesBeans() {

		List<PermSesBean> lr = new ArrayList<PermSesBean>();
		Iterator<Permiso> itr = permisos.iterator();
		while (itr.hasNext()) {

			lr.add(itr.next().getPermSesBean());

		}

		return lr;

	}

	public Usuario getUsuario(String nick) {

		Usuario u = null;
		Iterator<Usuario> itu = users.iterator();
		while (itu.hasNext()) {

			u = itu.next();
			if (u.getNick().equals(nick)) {
				return u;
			}

		}
		return null;
	}

	public Rol getRol(String nombre) {

		Rol r = null;
		Iterator<Rol> itr = roles.iterator();
		while (itr.hasNext()) {

			r = itr.next();
			if (r.getNombre().equals(nombre)) {
				return r;
			}

		}
		return null;
	}

	public Permiso getPermiso(String nombre) {

		Iterator<Permiso> itp = permisos.iterator();
		while (itp.hasNext()) {

			if (nombre.equals(itp.next().getNombre())) {

				return itp.next();
			}

		}
		return null;
	}

	public boolean existePermiso(String nombre) {

		Iterator<Permiso> itp = permisos.iterator();
		while (itp.hasNext()) {
			if (itp.next().getNombre().equals(nombre)) {

				return true;
			}

		}
		return false;
	}

	// precondicion no existe otro permiso con mismo nombre
	public void agregarPermiso(Permiso p) {

		permisos.add(p);

	}

	public int existeRol(Rol r) {

		for (Iterator<Rol> itr = roles.iterator(); itr.hasNext();) {

			if (itr.next().getNombre().equals(r.getNombre())) {

				return ConstantesPersistencia.Error;

			}

		}

		return ConstantesPersistencia.Exito;
	}

	public int existeCanal(Canal c) {

		for (Iterator<Canal> itc = canales.iterator(); itc.hasNext();) {

			if (itc.next().getCodigo().equals(c.getCodigo())) {

				return ConstantesPersistencia.Error;

			}

		}

		return ConstantesPersistencia.Exito;

	}

	public List<CanalSesBean> getCanalesAppSesAll() {
		
		List<CanalSesBean> la = new ArrayList<CanalSesBean>();
		if (canales != null) {
			Iterator<Canal> itc = canales.iterator();
			while (itc.hasNext()) {
				Canal ca = itc.next();
				CanalSesBean cs = new CanalSesBean();
				cs.setCodigo(ca.getCodigo());
				cs.setRegistrados(new Integer(ca.getRegistrados().size()));
				la.add(cs);
			}
		}
		return la;
	}
	
	public List<PedSesBean> getCanalesAppSesAllMsj() {
		
		List<PedSesBean> la = new ArrayList<PedSesBean>();
		TreeMap<String,Integer> map = new TreeMap<String, Integer>();
				
		Canal c;
		if(pedidosMsj != null){
			
			Iterator<PedidoMsj> itp = pedidosMsj.iterator(); 
			while(itp.hasNext()){
				
				PedidoMsj ped = itp.next();
				String key = ped.getCanal().getCodigo();
				if (map.containsKey(key)){
					Integer val = map.get(key);
					val = new Integer(val.intValue() + 1);
					map.put(key, val);
				}else{
					map.put(key, new Integer(1));
				}
			}
			
			Iterator<Integer> itv = map.values().iterator();
			Iterator<String> itk = map.keySet().iterator();
			while (itv.hasNext()){
				
				PedSesBean p = new PedSesBean();
				p.setCantidad(itv.next());
				p.setServicio(itk.next());
				la.add(p);
			}
			
		}		
		return la;
	}
}
