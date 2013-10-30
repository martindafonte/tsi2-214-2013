package persistencia;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import modelo.Registro;

/**
 * Session Bean implementation class RegistroDAO
 */
@Stateless
@LocalBean
public class RegistroDAO implements RegistroDAOLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(unitName="WebUserManager")
	private EntityManager em;
	
	
    public RegistroDAO() {
    }

	@Override
	public Registro register(String regId) {
		Registro reg = new Registro();
//		reg.setId(appInfo.getId("Registro"));
		reg.setRegistrer(regId);
		em.persist(reg);
		return reg;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void unregister(String regId) {
		Query q = em.createQuery("SELECT x FROM Registro x WHERE x.registrer = ?1");
		q.setParameter(1, regId);
		List<Registro> ls = (List<Registro>)q.getResultList();
		Iterator<Registro> itls = ls.iterator();
		while (itls.hasNext()){
			
			em.remove(itls.next());
			
		}
		
		
	}

	@Override
	public void updateRegistration(String oldId, String newId) {
		throw new NotImplementedException();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDevices() {
		Query q = em.createQuery("SELECT x FROM Registro x");
		List<Registro> ls = (List<Registro>)q.getResultList();
		List<String> lres = new LinkedList<String>(); 
		Iterator<Registro> itls = ls.iterator();
		while (itls.hasNext()){
			lres.add(itls.next().getRegistrer());
		}
		return lres;
	}

}
