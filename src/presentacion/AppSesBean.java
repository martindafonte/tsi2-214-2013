/**
 * 
 */
package presentacion;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

/**
 * @author bruno
 *
 */
public class AppSesBean {
	
	private Long aplicacionid;

	private List<String> caca;
	
	public AppSesBean() {
		
		
	}
	
	@PostConstruct
	private void init(){
		caca = new LinkedList<String>();
		caca.add("caca1");
		caca.add("caca2");
		caca.add("caca3");
		caca.add("caca4");
	}
	
	public List<String> getCaca() {
		return caca;
	}

	public void setCaca(List<String> caca) {
		this.caca = caca;
	}

	public Long getAplicacionid() {
		return aplicacionid;
	}

	public void setAplicacionid(Long aplicacionid) {
		this.aplicacionid = aplicacionid;
	}
}
