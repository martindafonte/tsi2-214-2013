package persistencia;

import javax.ejb.Local;
import modelo.Aplicacion;

@Local
public interface AplicacionDAOLocal {

	public void altaApliacion(Aplicacion a);
	
}
