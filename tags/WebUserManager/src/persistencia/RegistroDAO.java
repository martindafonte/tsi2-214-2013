package persistencia;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import negocio.AppInfoLocal;
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
	
	@EJB
	private AppInfoLocal appInfo;
	
    public RegistroDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Registro register(String regId) {
		// TODO Auto-generated method stub
		
		Registro reg = new Registro();
//		reg.setId(appInfo.getId("Registro"));
		reg.setRegistrer(regId);
		em.persist(reg);
		return reg;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void unregister(String regId) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDevices() {
		// TODO Auto-generated method stub
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
