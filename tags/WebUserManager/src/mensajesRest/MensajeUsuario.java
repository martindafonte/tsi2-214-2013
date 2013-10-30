package mensajesRest;

import javax.xml.bind.annotation.XmlElement;

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
	public String canalUsuario;
	
	public MensajeUsuario() {		
	}
	public MensajeUsuario(Mensaje m){
		this.codigo = m.codigo;
		this.descripcion = m.descripcion;
	}

}
