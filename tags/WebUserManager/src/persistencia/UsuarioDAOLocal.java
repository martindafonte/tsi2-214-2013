package persistencia;

import javax.ejb.Local;

import modelo.Desarrollador;
import modelo.Persona;
import modelo.Usuario;

@Local
public interface UsuarioDAOLocal {
	
	public void altaUsuario(Usuario u);
	public void altaDesarrollador(Desarrollador u);
	public Persona getUsuario(String nick, String pass, Boolean isDes);
}
