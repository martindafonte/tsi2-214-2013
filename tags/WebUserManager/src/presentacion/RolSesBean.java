/**
 * 
 */
package presentacion;

import java.util.List;

/**
 * @author bruno
 *
 */
public class RolSesBean {

	
	private Long id;
	private String nombre;

	private List<PermSesBean> perms;
	
	public List<PermSesBean> getPerms() {
		return perms;
	}

	public void setPerms(List<PermSesBean> perms) {
		this.perms = perms;
	}

	public RolSesBean() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
}
