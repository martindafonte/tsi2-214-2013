package persistencia;

import javax.ejb.Local;

import modelo.Permiso;
import modelo.Rol;

@Local
public interface RolDAOLocal {

	public int altaRolAplicacion(Rol r, long appId);
	public int agregarPermRol(Permiso p, long rolId);
	public int asignarRolUsuario(String nick, long appid, String rol);
	public int quitarRolUsuario(String nick, long appid, String rol);
	
}
