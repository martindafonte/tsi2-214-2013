package persistencia;

import javax.ejb.Local;

import modelo.Desarrollador;
import modelo.Usuario;

@Local
public interface UsuarioDAOLocal {
	
	public void altaUsuario(Usuario u);
	public void altaDesarrollador(Desarrollador u);
	public Usuario getUsuario(String nick, String pass);
	public Desarrollador getDesarrollador(String nick, String pass);
}
