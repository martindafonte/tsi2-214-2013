package persistencia;

import java.util.List;

import javax.ejb.Local;

import modelo.Registro;

@Local
public interface RegistroDAOLocal {
	
	  public Registro register(String regId);
	  public void unregister(String regId);
	  public void updateRegistration(String oldId, String newId);
	  public List<String> getDevices();

}
