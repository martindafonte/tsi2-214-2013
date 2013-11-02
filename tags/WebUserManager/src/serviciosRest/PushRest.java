package serviciosRest;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;

import negocio.ServiciosLocal;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

import excepciones.MultipleRegistersExists;
import mensajesRest.Mensaje;
import modelo.Canal;
import modelo.Registro;
import persistencia.*;

public class PushRest implements IPushRest {

	@EJB
	private ServiciosLocal serv;

	@EJB
	private RegistroDAOLocal rdl;
	
	@EJB
	private UsuarioDAOLocal udl;

	@EJB
	private CanalDAOLocal cdl;

	@Override
	public Mensaje registrar(long p_appId, String p_regId, String p_canal) {
		serv.crearPedidoPush("/Push/apid/chanel/User", "POST", p_appId);
		Mensaje msj = new Mensaje();
		try {
			Registro r = rdl.existsDevice(p_regId);
			if (r == null) {
				r = rdl.register(p_regId);
			}
			cdl = new CanalDAO();
			Canal c = cdl.getCanal(p_canal);
			if (c == null) {
				msj.codigo = Constantes.Push_Error_No_Existe_Canal;
				msj.descripcion = "El canal ingresado no existe";
				return msj;
			}
			cdl.agregarRegistroCanal(c, r);
			msj.codigo = 0;
			msj.descripcion = "Exito";
		} catch (MultipleRegistersExists mre) {
			msj.codigo = Constantes.Push_Excepcion_multiples_registros;
			msj.descripcion = "Se encontraron multiples registros con un mismo regid";
		} catch (Exception e) {
			msj.codigo = Constantes.Push_Excepcion;
			msj.descripcion = "Ocurrio una excepcion inesperada";
		}
		return msj;
	}

	@Override
	public Mensaje desregistrar(long p_appId, String p_regId, String p_canal) {
		serv.crearPedidoPush("/Push/apid/chanel/User/id", "DELETE", p_appId);
		Mensaje msj = new Mensaje();
		try {
			Canal c = cdl.getCanal(p_canal);
			Registro r = rdl.existsDevice(p_regId);
			if (r == null) {
				msj.codigo = Constantes.Push_Error_No_existe_registro;
				msj.descripcion = "No existe ningun registro con ese regId";
				return msj;
			}
			if (c == null) {
				msj.codigo = Constantes.Push_Error_No_Existe_Canal;
				msj.descripcion = "No existe ningun canal con ese Id";
				return msj;
			}
			cdl.quitarRegistroCanal(c, r);
			msj.codigo = 0;
			msj.descripcion = "Exito";
		} catch (Exception e) {
			msj.codigo = Constantes.Push_Excepcion;
			msj.descripcion = "Ocurrio una excepcion inesperada";
		}
		return msj;
	}

	@Override
	public Mensaje enviarACanal(long p_appId, String p_canal, String p_msj) {
		Mensaje msj = new Mensaje();
		try {
			// Se consigue la lista de registros
			List<Registro> registros = cdl.getRegistroCanal(p_canal);
			Message message;
			Sender sender = new Sender(Constantes.apikey);
			if (p_msj != null) {
				message = new Message.Builder().addData("Mensaje", p_msj)
						.build();
			} else {
				message = new Message.Builder().build();
			}
			List<String> regIds = new LinkedList<String>();
			for (Registro _r : registros) {
				regIds.add(_r.getRegistrer());
			}
			MulticastResult result = sender.send(message, regIds, 5);
			if (result.getFailure() == 0) {
				msj.codigo = 0;
				msj.descripcion = "Exito";
			} else {
				msj.codigo = Constantes.Push_Error_Enviar;
				msj.descripcion = "No se pudieron enviar "
						+ result.getFailure();
			}
			serv.crearPedidoMsj(p_appId, p_canal);
			serv.crearPedidoPush("/Push/apid/chanel/Message", "POST", p_appId);
		} catch (Exception e) {
			msj.codigo = Constantes.Push_Excepcion;
			msj.descripcion = "Ocurrio una excepcion inesperada";
		}
		return msj;
	}

	@Override
	public Mensaje enviarAUsuario(long p_appId, String p_nick, String p_msj) {
		Mensaje msj = new Mensaje();
		try {
			 //Se consigue la lista de registros
			List<Registro> registros = udl.obtenerRegistrosUsuario(p_nick, p_appId);;
			if(registros.isEmpty()){
				msj.codigo = Constantes.Push_Error_Usuario_nunca_registrado;
				msj.descripcion = "Error el usuario no tiene ningun dispositivo registrado";
				return msj;
			}
			Message message;
			Sender sender = new Sender(Constantes.apikey);
			if (p_msj != null) {
				message = new Message.Builder().addData("Mensaje", p_msj)
						.build();
			} else {
				message = new Message.Builder().build();
			}
			List<String> regIds = new LinkedList<String>();
			for (Registro _r : registros) {
				regIds.add(_r.getRegistrer());
			}
			MulticastResult result = sender.send(message, regIds, 5);
			if (result.getFailure() == 0) {
				msj.codigo = 0;
				msj.descripcion = "Exito";
			} else {
				msj.codigo = Constantes.Push_Error_Enviar;
				msj.descripcion = "No se pudieron enviar "
						+ result.getFailure();
			}
			serv.crearPedidoMsj(p_appId,"owner");
			serv.crearPedidoPush("/Push/apid/chanel/Message", "POST", p_appId);
		} catch (Exception e) {
			msj.codigo = Constantes.Push_Excepcion;
			msj.descripcion = "Ocurrio una excepcion inesperada";
		}
		return msj;
	}

}
