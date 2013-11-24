package utiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import ejb.Mongo;

public class FacebookProperties {

	
	
	public static String getProperties() {
		
		Properties fprop = new Properties();
		try {
			fprop.load(Mongo.class.getResourceAsStream("/META-INF/Facebook.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		String host;
		int port;
		host = fprop.getProperty("host");
		port = Integer.parseInt(fprop.getProperty("port"));
		
		
		return "http://"+host+":"+port;
	}
	
	
	
}
