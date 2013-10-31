package persistencia;

import javax.ejb.Local;

import modelo.Permiso;
import modelo.Rol;

@Local
public interface RolDAOLocal {

	public int altaRolAplicacion(Rol r, long appId);
	public int agregarPermRol(Permiso p, long rolId);
	public int asignarRolUsuario(long rolId, long userId);
	public int quitarRolUsuario(long rolId, long userId);
	
}
