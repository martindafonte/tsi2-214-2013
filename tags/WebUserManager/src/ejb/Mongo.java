package ejb;


import javax.ejb.Stateless;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.ejb.LocalBean;
import org.json.JSONException;
import org.json.JSONObject;
import com.mongodb.*;
import com.mongodb.util.JSON;

/**
 * Session Bean implementation class Mongo
 */
@Stateless
@LocalBean
public class Mongo implements MongoLocal {
	

    /**
     * Default constructor. 
     */
    public Mongo() {
        // TODO Auto-generated constructor stub
    	
    }
    
    public void Crear(String nombreDB, int clienteId) throws IOException{		
		try{			
//			Properties propMongo = new Properties();
//			FileInputStream in = new FileInputStream("datosMongoDB.properties");
//			propMongo.load(in);
//			in.close();
				
			MongoClient mongoClient = new MongoClient("localhost",27017);
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
    	MongoClient mongoClient = new MongoClient("localhost",27017);
		DB base = mongoClient.getDB(nombreDB); 
		
		DBCollection collection = base.getCollection("Json");
		BasicDBObject query = new BasicDBObject(); 
        query.put("id", clienteId);
        DBCursor cursor = collection.find(query);
		return cursor.size() > 0;
    	
    }

	@Override
	public void Eliminar(String nombreDB) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient("localhost",27017);
		mongoClient.dropDatabase(nombreDB);
		
	}

	@Override
	public String IngresarJson(String nombreDB, String json, int clienteId) throws UnknownHostException {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient("localhost",27017);
		DB base = mongoClient.getDB(nombreDB); 
		
		// Ya existe
        if ( ExisteCliente(nombreDB, clienteId)){
//        	System.out.println("********************\n");
//        	System.out.println("Ya existe cliente, error no se igresa el Json");
//        	System.out.println("********************\n");
        	return "Ya existe cliente, error no se igresa el Json";
        }
        
        // no existe el cliente
        DBCollection collection = base.getCollection("Json");
    	BasicDBObject document = new BasicDBObject();	
		document.put("id",clienteId);		
		
		
		DBObject object = (DBObject) JSON.parse(json);
		document.put("json",object);
		//document.put("json",json);
		collection.insert(document);	
		return "Ok";
	}

	@Override
	public String Json(String nombreDB, int clienteId) throws UnknownHostException, JSONException {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient("localhost",27017);
		DB base = mongoClient.getDB(nombreDB); 
		
		DBCollection collection = base.getCollection("Json");
		BasicDBObject query = new BasicDBObject(); 
        query.put("id", clienteId);
        DBCursor cursor = collection.find(query);
        
        if (cursor.size() == 0){
//        	System.out.println("********************\n");
//        	System.out.println("No existe el cliente, no se puede ingresar json");
//        	System.out.println("********************\n");
        	
        	return  "No existe el cliente, no se puede ingresar json";
        }
        // Existe el cliente
      
        DBObject  objeto = (DBObject) cursor.next();
       
        DBObject json = (DBObject) objeto.get("json");
      //  DBObject j = JSON.parse(json.toString());
        JSONObject aj = new JSONObject(json.toString());
        
		return aj.toString();
	}

	@Override
	public String ActualizarJson(String nombreDB, String json, int clienteId) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		if (!ExisteCliente(nombreDB, clienteId)){	
//        	System.out.println("********************\n");
//        	System.out.println("No existe el cliente, no se puede actualizar");
//        	System.out.println("********************\n");
        	return "No existe el cliente, no se puede actualizar";
		}
		MongoClient mongoClient = new MongoClient("localhost",27017);
		DB base = mongoClient.getDB(nombreDB); 
		DBCollection collection = base.getCollection("Json");
	
    	BasicDBObject document = new BasicDBObject();			
		DBObject object = (DBObject) JSON.parse(json);
		document.put("id",clienteId);
		document.put("json",object);
		
		
		
		BasicDBObject query = new BasicDBObject().append("id", clienteId);
	 
		collection.findAndModify(query, document);
		return "Cliente actualizado";
	}

	@Override
	public String EliminarJson(String nombreDB, int clienteId) throws UnknownHostException {
		// TODO Auto-generated method stub
		if (!ExisteCliente(nombreDB, clienteId)){
//			System.out.println("********************\n");
//        	System.out.println("No existe el cliente, no se puede eliminar");
//        	System.out.println("********************\n");
        	return "No existe el cliente, no se puede eliminar";
		}
		
		
		MongoClient mongoClient = new MongoClient("localhost",27017);
		DB base = mongoClient.getDB(nombreDB); 
		DBCollection collection = base.getCollection("Json");
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", clienteId);
	 
		collection.remove(searchQuery);
		return "Cliente eliminado";
	}

}
