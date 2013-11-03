package persistencia;

import java.util.List;

import javax.ejb.Local;

import presentacion.PermSesBean;
import modelo.Permiso;
import modelo.Rol;

@Local
public interface RolDAOLocal {

	public int altaRolAplicacion(Rol r, long appId);
	
	public int altaPermiso(String nombre, long appId);	
	
	public int agregarPermRol(Permiso p, long rolId);
	public int agregarPermRol(String nombre, long rolId);
	public int agregarPermRol(long permId, long rolId);
	
	public int quitarPermRol(Permiso p, long rolId);
	public int quitarPermRol(String nombre, long rolId);
	public int quitarPermRol(long permId, long rolId);
	
	public int asignarRolUsuario(String nick, long appid, String rol);
	public int quitarRolUsuario(String nick, long appid, String rol);
	
	public List<PermSesBean> getPermsHave(long rolid);
	public List<PermSesBean> getPermsDontHave(long rolid);
	
}
