package mensajesRest;

import javax.xml.bind.annotation.XmlElement;

import modelo.Usuario;

public class MensajeUsuario extends Mensaje {

	@XmlElement
	public String nick;
	@XmlElement
	public String pass;
	@XmlElement
	public String nombre;
	@XmlElement
	public String apellido;
	@XmlElement
	public String codigoCanal;

	
	public MensajeUsuario() {		
	}
	public MensajeUsuario(Mensaje m){
		this.codigo = m.codigo;
		this.descripcion = m.descripcion;
	}
	public MensajeUsuario(Usuario u){
		nick = u.getNick();
		nombre = u.getNombre();
		apellido = u.getApellido();
		codigoCanal = u.getCanal().getCodigo();
	}

}
