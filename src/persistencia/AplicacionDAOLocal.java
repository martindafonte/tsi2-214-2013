package persistencia;

import java.util.List;

import javax.ejb.Local;

import modelo.Aplicacion;
import modelo.Canal;
import modelo.Desarrollador;

@Local
public interface AplicacionDAOLocal {

	public int altaApliacion(Aplicacion a, Desarrollador d);
	public List<Aplicacion> singleLoginAplicaciones();
	public List<Aplicacion> getAplicaciones(Desarrollador d);
	public long getIdJSON(Aplicacion a);
	public Aplicacion getAplicacion(long id);
	
	public boolean agregarCanalAplicacion(Canal codigo, Aplicacion a);
	public boolean agregarCanalAplicacion(String codigo, long id);
	
	public void quitarCanalAplicacion(Canal c, Aplicacion a);
	public void quitarCanalAplicacion(String cod, long id);
	public void cambiarSingleLogin(long id);
	public void cambiarSingleLogin(long id, boolean valor);
	public List<Canal> getCanales(Aplicacion a);
	public int cantUsuarios(Long aplicacionid);
	
}
