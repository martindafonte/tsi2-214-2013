package negocio;

import javax.ejb.Local;

@Local
public interface AppInfoLocal {
	
	public long getId(String seq);
}
