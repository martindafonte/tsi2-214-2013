package mensajesRest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mensaje{
	@XmlElement
	public int codigo;
	@XmlElement
	public String descripcion;
}


//Esta clase es un ejemplo de lo que se debe devolver
//class MensajeExtendido extends Mensaje{
//	@XmlElement
//	String json;
//}

//Ejemplo de servicio
//@GET
//@Produces(MediaType.APPLICATION_JSON)
//public MensajeExtendido holaMundo(){
//	MensajeExtendido msj = new MensajeExtendido();
//	msj.codigo=0;
//	msj.descripcion = "exito";
//	msj.json ="";
//	return msj;
//}