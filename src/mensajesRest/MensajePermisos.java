package mensajesRest;


import java.util.LinkedList;
import java.util.List;

public class MensajePermisos extends Mensaje {

	public List<String> permisos;
	public MensajePermisos() {
		permisos = new LinkedList<String>();
	}
	
	public MensajePermisos(Mensaje m){
		this.codigo = m.codigo;
		this.descripcion = m.descripcion;
		permisos = new LinkedList<String>();
	}
}
