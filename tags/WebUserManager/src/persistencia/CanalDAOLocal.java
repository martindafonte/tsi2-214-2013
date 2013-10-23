package persistencia;

import javax.ejb.Local;

import modelo.Canal;
import modelo.Registro;

@Local
public interface CanalDAOLocal {

	
	public void altaCanal(Canal c);
	public void borrarCanal(Canal c);
	public void agregarRegistroCanal(Canal c, Registro r);
	public void quitarRegistroCanal(Canal c, Registro r);

}
