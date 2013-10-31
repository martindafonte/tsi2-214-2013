package mensajesRest;

import java.util.LinkedList;


public class MensajeJson  extends Mensaje  {
	
	public String json;


	public MensajeJson() {
		super();
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
