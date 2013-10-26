package serviciosRest;


//import java.io.IOException;
//import java.net.InetSocketAddress;
import java.net.UnknownHostException;
//import java.util.concurrent.Executor;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mensajesRest.Mensaje;
import mensajesRest.MensajeJson;
import mensajesRest.MensajeJsonId;

import org.json.JSONException;

//import com.sun.net.httpserver.HttpContext;
//import com.sun.net.httpserver.HttpHandler;
//import com.sun.net.httpserver.HttpServer;
//
//import persistencia.AplicacionDAO;
//import persistencia.AplicacionDAOLocal;
//import ejb.Mongo;
import ejb.MongoLocal;


@Stateless
public class MongoRest implements IMongoPublico  {
	@EJB
	private MongoLocal m;
	

	
	public MensajeJson ObtenerJson(int app,int jsonId) throws UnknownHostException, JSONException {	
		
		return  m.Json(app, jsonId);
	}

	@Override
	public MensajeJsonId IngresarJson(int appid, String jsonString) throws UnknownHostException {
		// TODO Auto-generated method stub
	
		return m.IngresarJson(appid, jsonString);

	}

	@Override
	public Mensaje EliminarJson(int app, int jsonId) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		
		return m.EliminarJson(app, jsonId);
	}

	@Override
	public Mensaje ActualizarJson(int app,int jsonId, String json)
			throws UnknownHostException {
		// TODO Auto-generated method stub
		
		return m.ActualizarJson(app, json, jsonId);
	}

	@Override
	public Mensaje BorrarBase(int app) throws UnknownHostException {
		// TODO Auto-generated method stub
		return m.EliminarBase(app);
	}

	
	
}
