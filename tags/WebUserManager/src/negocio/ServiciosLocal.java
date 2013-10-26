package negocio;

import java.util.List;

import javax.ejb.Local;

import modelo.Desarrollador;
import modelo.Usuario;
import modelo.Aplicacion;

@Local
public interface ServiciosLocal {

	public void altaDesarrollador(String nick, String pass, String nombre, String apellido);
	public void altaUsuario(String nick, String pass, String nombre, String apellido);
	public Desarrollador getDesarrollador(String nick, String pass);
	public Usuario getUsuario(String nick, String pass);
	public Boolean existeUsuario(String nick, String pass);
	public Boolean existeDesarollador(String nick, String pass);
	
	public void altaAplicacion(String nombre, String descripcion, String nick, String pass);
	public List<Aplicacion> getAplicaciones(Desarrollador d);
	
}
