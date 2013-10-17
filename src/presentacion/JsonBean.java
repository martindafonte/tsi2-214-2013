/**
 * 
 */
package presentacion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author bruno
 *
 */
public class JsonBean {
	private java.lang.String valor;
	private java.lang.String js;

	public JsonBean() {
	}

	public java.lang.String getValor() {
		return valor;
	}

	public void setValor(java.lang.String valor) {
		this.valor = valor;
	}

	public java.lang.String getJs() {
		return js;
	}

	public void setJs(java.lang.String js) {
		this.js = js;
	}
	
	
	public String doGet(){
		
		String direc = "http://localhost:8080/WebUserManager/MongoServicios/obtener/" + valor;
		
		try {
			URL url = new URL(direc);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			 
			// optional default is GET
			con.setRequestMethod("GET");
			
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			
	 
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			//print result
			js = response.toString();
			
			
		}catch(Exception ex){
			System.out.println("ERRORRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
			System.out.println(ex.getStackTrace());
			
		}
		
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String ret = req.getRequestURL().toString();
		return ret;


	}
	
	
}
