package persistencia;

import javax.ejb.Local;

import modelo.Usuario;

@Local
public interface UsuarioDAOLocal {
	
	public void altaUsuario(Usuario u);
	
}
