package persistencia;

import java.util.List;

import javax.ejb.Local;

import modelo.Aplicacion;
import modelo.Desarrollador;

@Local
public interface AplicacionDAOLocal {

	public void altaApliacion(Aplicacion a, Desarrollador d);
	public List<Aplicacion> singleLoginAplicaciones();
	public List<Aplicacion> getAplicaciones(Desarrollador d);
	public long getIdJSON(Aplicacion a);
	public Aplicacion getAplicacion(long id);
	
}
