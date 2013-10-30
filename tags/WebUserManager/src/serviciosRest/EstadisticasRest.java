package serviciosRest;

import javax.ejb.EJB;

import negocio.ServiciosLocal;


public class EstadisticasRest implements IEstadisticasRest {

	@EJB
	private ServiciosLocal serv;
	
	@Override
	public void registrarPedidoJson(String http, String metodo, long app, int jsonId) {
		
		try {
			serv.crearPedidoJson(http, metodo, app, jsonId);
		} catch (Exception e) {
		}
		
	}

	@Override
	public void registrarPedidoPush(String http, String metodo, long app) {
		try {
			serv.crearPedidoPush(http, metodo, app);
		} catch (Exception e) {
		}
		
	}

	@Override
	public void registrarPedidoUser(String http, String metodo, long app, String userId) {
		try {
			serv.crearPedidoUser(http, metodo, app, userId);
		} catch (Exception e) {
		}
		
	}

	@Override
	public void registrarPedidoMsj(long app,	String canId) {
		try {
			serv.crearPedidoMsj(app, canId);
		} catch (Exception e) {
		}
		
	}
	
}
