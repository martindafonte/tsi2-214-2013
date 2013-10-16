package negocio;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import persistencia.AplicacionDAOLocal;
import persistencia.UsuarioDAOLocal;
import modelo.Aplicacion;
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
	private AppInfoLocal appinfo;
	
	@EJB
	private UsuarioDAOLocal ul;
	
	@EJB
	private AplicacionDAOLocal al;
	
    public Servicios() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void altaUsuario(String nick, String pass, String nombre,
			String apellido) {
		// TODO Auto-generated method stub
		Usuario u = new Usuario();
		u.setId(appinfo.getId("Usuario"));
		u.setNick(nick);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);
		
		ul.altaUsuario(u);
	}

	@Override
	public Usuario getUsuario(String nick, String pass) {
		// TODO Auto-generated method stub
		
		
		return ul.getUsuario(nick, pass);	
	}

	@Override
	public Boolean existeUsuario(String nick, String pass) {
		// TODO Auto-generated method stub
		
		if (ul.getUsuario(nick, pass) != null){
			return true;
		}
		
		return false;
	}

	@Override
	public void altaDesarrollador(String nick, String pass, String nombre,
			String apellido) {
		// TODO Auto-generated method stub
		
		Desarrollador u = new Desarrollador();
		u.setId(appinfo.getId("Desarrollador"));
		u.setNick(nick);
		u.setNombre(nombre);
		u.setPass(pass);
		u.setApellido(apellido);
		
		ul.altaDesarrollador(u);
		
		
		
	}

	@Override
	public Desarrollador getDesarrollador(String nick, String pass) {
		// TODO Auto-generated method stub
		return ul.getDesarrollador(nick, pass);
	}

	@Override
	public Boolean existeDesarollador(String nick, String pass) {
		// TODO Auto-generated method stub
		if (ul.getDesarrollador(nick, pass) != null){
			return true;
		}
		
		return false;
	}

	@Override
	public void altaAplicacion(String nombre, String descripcion, String nick,
			String pass) {
		// TODO Auto-generated method stub
		
		Desarrollador d = ul.getDesarrollador(nick, pass);
		if (d == null) {
			return;
		}
		
		Aplicacion a = new Aplicacion();
		
		a.setId(appinfo.getId("Aplicacion"));
		a.setNombre(nombre);
		a.setDescripcion(descripcion);
		al.altaApliacion(a,d);	
		
	}


}
