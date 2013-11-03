package mensajesRest;



public class MensajeJson  extends Mensaje  {
	
	public String json;
	public int cant;


	

	public MensajeJson() {
		super();
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

}
