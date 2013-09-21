package negocio;

import javax.ejb.Local;

@Local
public interface ServiciosLocal {

	public void altaUsuario(String nick, String pass, String nombre, String apellido);
	
}
