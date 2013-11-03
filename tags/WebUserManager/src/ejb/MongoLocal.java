package ejb;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.ejb.Local;

import mensajesRest.Mensaje;
import mensajesRest.MensajeJson;
import mensajesRest.MensajeJsonId;

import org.json.JSONException;

@Local
public interface MongoLocal {
	
	public void Crear(String nombreDB, int clienteId) throws IOException;
	public Mensaje EliminarBase(int appid) throws UnknownHostException;
	public MensajeJsonId IngresarJson(int appid, String json) throws UnknownHostException;
	public MensajeJson Json(int appid, int clienteId) throws UnknownHostException, JSONException;
	public boolean ExisteCliente(String nombreDB, int clienteId) throws UnknownHostException;
	public Mensaje ActualizarJson(int appid, String json, int jsonId) throws UnknownHostException;
	public Mensaje EliminarJson(int appid, int jsonId) throws UnknownHostException;
	public MensajeJson ObtenerListaJson(int appid,String Json, int desde, int cant) throws JSONException, UnknownHostException;
}
