package serviciosRest;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import java.util.concurrent.Executor;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import mensajesRest.Mensaje;
import mensajesRest.MensajeJson;
import mensajesRest.MensajeJsonId;
import negocio.ServiciosLocal;

import org.json.JSONException;


import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import persistencia.AplicacionDAO;
import persistencia.AplicacionDAOLocal;
import ejb.Mongo;
import ejb.MongoLocal;


@Stateless
public class MongoRest implements IMongoPublico  {
	@EJB
	private MongoLocal m;
	
	@EJB
	private ServiciosLocal serv;

	
	public MensajeJson ObtenerJson(int app,int jsonId) throws UnknownHostException, JSONException {	
		
		serv.crearPedidoJson("/MongoServicios/apid/jsonid", "GET", app, jsonId);
		return  m.Json(app, jsonId);
	}

	@Override
	public MensajeJsonId IngresarJson(int appid, String jsonString) throws UnknownHostException {
		
		MensajeJsonId msj = m.IngresarJson(appid, jsonString);
		int jsonId = (int) msj.getJsonId();
		serv.crearPedidoJson("/MongoServicios/apid", "POST", appid, jsonId);
		return msj;
	}

	@Override
	public Mensaje EliminarJson(int app, int jsonId) throws UnknownHostException {
		
		serv.crearPedidoJson("/MongoServicios/apid/jsonid", "DELETE", app, jsonId);
		return m.EliminarJson(app, jsonId);
	}

	@Override
	public Mensaje ActualizarJson(int app,int jsonId, String json)
			throws UnknownHostException {
		
		serv.crearPedidoJson("/MongoServicios/apid/jsonid", "PUT", app, jsonId);
		return m.ActualizarJson(app, json, jsonId);
	}

	@Override
	public Mensaje BorrarBase(int app) throws UnknownHostException {

		serv.crearPedidoJson("/MongoServicios/deleteDB/apid", "DELETE", app, -1);
		return m.EliminarBase(app);
	}

	@Override
	public MensajeJson ObtenerListaJson(int appid, String Json) throws UnknownHostException, JSONException {

		serv.crearPedidoJson("/MongoServicios/listaJson/apid", "GET", appid, -1);
		return m.ObtenerListaJson(appid, Json);
	}

	
	
}
