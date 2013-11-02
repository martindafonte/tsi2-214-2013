package persistencia;

import java.util.List;

import javax.ejb.Local;

import modelo.Desarrollador;
import modelo.Registro;
import modelo.Usuario;

@Local
public interface UsuarioDAOLocal {
	
	public int altaUsuario(Usuario u, long appId);
	public int altaDesarrollador(Desarrollador u);
	public Usuario chequearUsuario(String nick, String pass, long appid);
	public Desarrollador getDesarrollador(String nick, String pass);
	public List<Registro> obtenerRegistrosUsuario(String nick, long app_id);
	public int agregarRegistroUsuario(String nick, long app_id, String regid);
	public int quitarRegistroUsuario(String nick, long app_id, String regid);
	public Usuario obtenerUsuario(String nick, long appid);
}
