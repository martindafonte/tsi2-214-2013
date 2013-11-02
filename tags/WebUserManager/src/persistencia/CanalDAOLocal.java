package persistencia;

import java.util.List;

import javax.ejb.Local;

import modelo.Aplicacion;
import modelo.Canal;
import modelo.Registro;

@Local
public interface CanalDAOLocal {

	
	public void altaCanal(Canal c);
	public void borrarCanal(Canal c);
	public boolean existeCanal(String cod);
	public void agregarRegistroCanal(Canal c, Registro r);
	public void quitarRegistroCanal(Canal c, Registro r);
	public Aplicacion getAplicacionCanal(Canal c);
	public Canal getCanal(String id);
	public List<Registro> getRegistroCanal(String id);

}
