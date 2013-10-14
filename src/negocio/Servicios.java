package negocio;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import persistencia.UsuarioDAOLocal;
import modelo.Desarrollador;
import modelo.Usuario;

/**
 * Session Bean implementation class Servicios
 */
@Stateless
@LocalBean
public class Servicios implements ServiciosLocal {

    /**
     * Default constructor. 
     */
	@EJB
	private UsuarioDAOLocal ul;
	
    public Servicios() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void altaUsuario(String nick, String pass, String nombre,
			String apellido) {
		// TODO Auto-generated method stub
		Usuario u = new Usuario();
		
		u.setNick(nick);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);
		
		ul.altaUsuario(u);
	}

	@Override
	public Usuario getUsuario(String nick, String pass) {
		// TODO Auto-generated method stub
		
		
		return (Usuario)ul.getUsuario(nick, pass, false);	
	}

	@Override
	public Boolean existeUsuario(String nick, String pass) {
		// TODO Auto-generated method stub
		
		if (ul.getUsuario(nick, pass, false) != null){
			return true;
		}
		
		return false;
	}

	@Override
	public void altaDesarrollador(String nick, String pass, String nombre,
			String apellido) {
		// TODO Auto-generated method stub
		
		Desarrollador u = new Desarrollador();
		
		u.setNick(nick);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);
		
		ul.altaDesarrollador(u);
		
		
		
	}

	@Override
	public Desarrollador getDesarrollador(String nick, String pass) {
		// TODO Auto-generated method stub
		return (Desarrollador)ul.getUsuario(nick, pass, true);
	}

	@Override
	public Boolean existeDesarollador(String nick, String pass) {
		// TODO Auto-generated method stub
		if (ul.getUsuario(nick, pass, true) != null){
			return true;
		}
		
		return false;
	}

}
