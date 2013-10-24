package serviciosRest;

import java.util.LinkedList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import modelo.Aplicacion;
import modelo.Canal;
import modelo.Registro;
import persistencia.*;

public class PushRest implements IPushRest {

	@Override
	public Mensaje registrar(long p_appId, String p_regId, String p_canal) {
		Mensaje msj = new Mensaje();
		try {
			RegistroDAOLocal rdl = new RegistroDAO();
			Registro r =rdl.register(p_regId);
			CanalDAOLocal cdl = new CanalDAO();
			Canal c = cdl.getCanal(p_regId);
			if(c == null){
				msj.codigo = Constantes.Push_Error_No_Existe_Canal;
				msj.descripcion = "El canal ingresado no existe";
				return msj;
			}
			cdl.agregarRegistroCanal(c,r);
			msj.codigo = 0;
			msj.descripcion = "Exito";
		} catch (Exception e) {
			msj.codigo = Constantes.Push_Excepcion;
			msj.descripcion = "Ocurrio una excepcion inesperada";
		}
		return msj;
	}

	@Override
	public Mensaje desregistrar(long p_appId, String p_regId, String p_canal) {
		Mensaje msj = new Mensaje();
		try {
			RegistroDAOLocal rdl = new RegistroDAO();
			rdl.unregister(p_regId);
			msj.codigo = 0;
			msj.descripcion = "Exito";
		} catch (Exception e) {
			msj.codigo = Constantes.Push_Excepcion;
			msj.descripcion = "Ocurrio una excepcion inesperada";
		}
		return msj;
	}

	//Estudiar la posibilidad de hacerlo de forma asincrona
	@Override
	public Mensaje enviarACanal(long p_appId, String p_canal, String p_msj) {
		Mensaje msj = new Mensaje();
		try {
			// CanalDAOLocal cdl = new CanalDAO();
			// Se consigue la lista de registros
			List<Registro> registros = new LinkedList<Registro>();
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
			MulticastResult result =sender.send(message, regIds, 5);
			if(result.getFailure()==0){
				msj.codigo = 0;
				msj.descripcion="Exito";
			}else{
				msj.codigo=Constantes.Push_Error_Enviar;
				msj.descripcion ="No se pudieron enviar " + result.getFailure();
			}
		} catch (Exception e) {
			msj.codigo = Constantes.Push_Excepcion;
			msj.descripcion = "Ocurrio una excepcion inesperada";
		}
		return msj;
	}

}
