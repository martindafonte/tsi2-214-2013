package ejb;


import javax.ejb.EJB;
import javax.ejb.Stateless;

//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.ejb.LocalBean;

import mensajesRest.Mensaje;
import mensajesRest.MensajeJson;
import mensajesRest.MensajeJsonId;
import modelo.Aplicacion;

import org.json.JSONException;
//import org.json.JSONObject;
//
//import persistencia.AplicacionDAO;
import persistencia.AplicacionDAOLocal;
import serviciosRest.Constantes;

import com.mongodb.*;
import com.mongodb.util.JSON;

/**
 * Session Bean implementation class Mongo
 */
@Stateless
@LocalBean
public class Mongo implements MongoLocal {
	
	@EJB
	private AplicacionDAOLocal aplicacion;
    /**
     * Default constructor. 
     */
    public Mongo() {
        // TODO Auto-generated constructor stub
    	
    }
    
	public Mensaje MensajeErrorApp(){
		MensajeJson json = new MensajeJson();
		json.codigo = Constantes.Cte_Error_No_Existe_App;
		json.descripcion = "No existe la aplicacion";
		return json;
	}
	public MensajeJsonId MensajeErrorAppJsonId(){
		MensajeJsonId json = new MensajeJsonId();
		json.codigo = Constantes.Cte_Error_No_Existe_App;
		json.descripcion = "No existe la aplicacion";
		return json;
	} 
	public MensajeJson MensajeErrorAppJson(){
		MensajeJson json = new MensajeJson();
		json.codigo = Constantes.Cte_Error_No_Existe_App;
		json.descripcion = "No existe la aplicacion";
		return json;
	}
	
    public void Crear(String nombreDB, int clienteId) throws IOException{		
		try{			
			MongoProperties mprop = new MongoProperties();
			MongoClient mongoClient = new MongoClient(mprop.hostname,mprop.port);
			DB base = mongoClient.getDB(nombreDB);  
			DBCollection collection = base.getCollection("Json");
			// Ingreso algo en la base porque si no no me la ingresa.
			if (!ExisteCliente(nombreDB, clienteId )){
				BasicDBObject document = new BasicDBObject();
				document.put("id", clienteId); 
				collection.insert(document);
			}
			
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}
    
    public boolean ExisteCliente(String nombreDB, int clienteId) throws UnknownHostException{
    	
    	MongoProperties mprop = new MongoProperties();
		MongoClient mongoClient = new MongoClient(mprop.hostname,mprop.port);
		
		DB base = mongoClient.getDB(nombreDB); 
		
		DBCollection collection = base.getCollection("Json");
		BasicDBObject query = new BasicDBObject(); 
        query.put("_id", clienteId);
        DBCursor cursor = collection.find(query);
		return cursor.size() > 0;
    	
    }

	@Override
	public Mensaje EliminarBase(int appid) throws UnknownHostException {
		// TODO Auto-generated method stub
		Aplicacion app = aplicacion.getAplicacion(appid);
		if (app == null){
			return  MensajeErrorApp();
		}	
		String nombreDB = Integer.toString(appid).concat("_").concat(app.getNombre().replace(" ", ""));
		MongoProperties mprop = new MongoProperties();
		MongoClient mongoClient = new MongoClient(mprop.hostname,mprop.port);
		mongoClient.dropDatabase(nombreDB);
		app.setJsonid(0);
		Mensaje m = new Mensaje();
		m.codigo = Constantes.Cte_Exito;
		m.descripcion = "Base de datos eliminada";
		return m;
		
	}

	@Override
	public MensajeJsonId IngresarJson(int appid, String json) throws UnknownHostException {
		// TODO Auto-generated method stub
		Aplicacion app = aplicacion.getAplicacion(appid);
		if (app == null){
			return  MensajeErrorAppJsonId();
		}
		String nombreDB = Integer.toString(appid).concat("_").concat(app.getNombre().replace(" ", ""));
		int jsonId =(int) app.getJSONID();
		
		// conectar con BAse
		MongoProperties mprop = new MongoProperties();
		MongoClient mongoClient = new MongoClient(mprop.hostname,mprop.port);
		DB base = mongoClient.getDB(nombreDB); 
		
        DBCollection collection = base.getCollection("Json");
        
        DBObject dbObject = (DBObject)JSON.parse(json.toString());
        
        dbObject.put("_id",jsonId);
        collection.insert(dbObject);
		MensajeJsonId m = new MensajeJsonId();
		m.codigo = Constantes.Cte_Exito;
		m.descripcion = "Json ingresado";
		m.jsonId = jsonId;
		return m;
	}

	@Override
	public MensajeJson Json(int appid, int jsonId) throws UnknownHostException, JSONException {
		// TODO Auto-generated method stub
		Aplicacion app = aplicacion.getAplicacion(appid);
		if (app == null){
			return  MensajeErrorAppJson();
		}
		String nombreDB = Integer.toString(appid).concat("_").concat(app.getNombre().replace(" ", ""));	
		
		MongoProperties mprop = new MongoProperties();
		MongoClient mongoClient = new MongoClient(mprop.hostname,mprop.port);
		DB base = mongoClient.getDB(nombreDB); 
		
		MensajeJson m = new MensajeJson();
		DBCollection collection = base.getCollection("Json");
		BasicDBObject query = new BasicDBObject(); 
       
        query.put("_id",jsonId);
        DBCursor cursor = collection.find(query);
        
        if (cursor.size() == 0){
        	m.codigo = Constantes.Cte_Error_Buscar_Id ;
        	m.descripcion = "El id no existe";
        	return  m;
        }
      
        DBObject  objeto = (DBObject) cursor.next();
        
        objeto.removeField("_id");
        
        m.codigo = Constantes.Cte_Exito;
        m.json =  objeto.toString();
        System.out.println(m.json);
        
        return m ;
	}

	@Override
	public Mensaje ActualizarJson(int appid, String json, int jsonId) throws UnknownHostException {
		// TODO Auto-generated method stub
		Aplicacion app = aplicacion.getAplicacion(appid);
		if (app == null){
			return  MensajeErrorApp();
		}
		String nombreDB = Integer.toString(appid).concat("_").concat(app.getNombre().replace(" ", ""));	
		Mensaje m = new Mensaje();
		if (!ExisteCliente(nombreDB, jsonId)){	
			m.codigo = Constantes.Cte_Error_Buscar_Id ;
        	m.descripcion = "El id no existe";
        	return  m;
        	
		}
	
		EliminarJson(appid, jsonId);
		
		// ingresar json
		MongoProperties mprop = new MongoProperties();
		MongoClient mongoClient = new MongoClient(mprop.hostname,mprop.port);
		DB base = mongoClient.getDB(nombreDB); 
		
        DBCollection collection = base.getCollection("Json");
        
        DBObject dbObject = (DBObject)JSON.parse(json.toString());
        
        dbObject.put("_id",jsonId);
        collection.insert(dbObject);
		//***********************************************************+
        
		m.codigo = Constantes.Cte_Exito;
		m.descripcion = "Json Actualizado";
		return m;
	}

	@Override
	public Mensaje EliminarJson(int appid, int jsonId) throws UnknownHostException {
		// TODO Auto-generated method stub
	// no existe la app
		Aplicacion app = aplicacion.getAplicacion(appid);
		if (app == null){
			return  MensajeErrorApp();
		}
		String nombreDB = Integer.toString(appid).concat("_").concat(app.getNombre().replace(" ", ""));	
		
	//no existe el id		
		Mensaje m = new Mensaje();
		if (!ExisteCliente(nombreDB, jsonId)){
        	m.codigo = Constantes.Cte_Error_Buscar_Id ;
        	m.descripcion = "El id no existe";
        	return m;
		}
		
		MongoProperties mprop = new MongoProperties();
		MongoClient mongoClient = new MongoClient(mprop.hostname,mprop.port);
		
		DB base = mongoClient.getDB(nombreDB); 
		DBCollection collection = base.getCollection("Json");
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", jsonId);
	 
		collection.remove(searchQuery);
		
    	m.codigo = Constantes.Cte_Exito;
    	m.descripcion = "Json eliminado.";
		
		return m;
	}

}

class MongoProperties{
	String hostname;
	int port;
	public MongoProperties() {
		Properties propMongo = new Properties();
		try {
			propMongo.load(Mongo.class.getResourceAsStream("/META-INF/MongoDB.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		hostname = propMongo.getProperty("hostname");
		port = Integer.parseInt(propMongo.getProperty("port"));
	}	
}

