package persistencia;

import javax.ejb.Local;

import modelo.Aplicacion;
import modelo.Desarrollador;

@Local
public interface AplicacionDAOLocal {

	public void altaApliacion(Aplicacion a, Desarrollador d);
	
}
