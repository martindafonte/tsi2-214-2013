package modelo;

import java.io.Serializable;
import java.lang.String;

/**
 * ID class for entity: Canal
 *
 */ 
public class CanalPK  implements Serializable {   
   
	         
	private String codigo;         
	private long aplicacionId;
	private static final long serialVersionUID = 1L;

	public CanalPK() {}

	

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	

	public long getAplicacionId() {
		return this.aplicacionId;
	}

	public void setAplicacionId(long aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof CanalPK)) {
			return false;
		}
		CanalPK other = (CanalPK) o;
		return true
			&& (getCodigo() == null ? other.getCodigo() == null : getCodigo().equals(other.getCodigo()))
			&& getAplicacionId() == other.getAplicacionId();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getCodigo() == null ? 0 : getCodigo().hashCode());
		result = prime * result + ((int) (getAplicacionId() ^ (getAplicacionId() >>> 32)));
		return result;
	}
   
   
}
