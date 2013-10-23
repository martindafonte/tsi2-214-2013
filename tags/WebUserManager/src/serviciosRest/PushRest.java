package serviciosRest;

public class PushRest implements IPushRest {

	@Override
	public Mensaje registrar(long p_appId, int p_regId, String p_canal) {
		Mensaje msj = new Mensaje();
		msj.codigo = 0;
		msj.descripcion="Exito";
		return msj;
	}

	@Override
	public Mensaje desregistrar(long p_appId, int p_regId, String p_canal) {
		Mensaje msj = new Mensaje();
		msj.codigo = 0;
		msj.descripcion="Exito";
		return msj;
	}

	@Override
	public Mensaje enviarACanal(long p_appId, String p_canal, String p_msj) {
		Mensaje msj = new Mensaje();
		msj.codigo = 0;
		msj.descripcion="Exito";
		return msj;
	}

}
