package negocio;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import modelo.Identificador;

/**
 * Session Bean implementation class AppInfo
 */
@Singleton
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class AppInfo implements AppInfoLocal {

	
	@PersistenceUnit(unitName = "WebUserManager")
	private EntityManagerFactory emf;
	
	private TreeMap<String, Identificador> ids;
    /**
     * Default constructor. 
     */
	
	public AppInfo(){}
	
	@PostConstruct
    public void AppInfo2() {
        // TODO Auto-generated constructor stub
    	
    	ids = new TreeMap<String, Identificador>();
    	EntityManager em = emf.createEntityManager();
    	Query qu = em.createQuery("SELECT x FROM Identificador x");
    	@SuppressWarnings("unchecked")
		List<Identificador> ls = (List<Identificador>)qu.getResultList();
    	if( ls.isEmpty()){
    		
    		//agregar a mano, una masa... jaja
    		Identificador i = new Identificador();
    		i.setName("Usuario");
    		i.setValue(0);
    		em.persist(i);
    		ids.put(i.getName(),i);
    		
    		i = new Identificador();
    		i.setName("Desarrollador");
    		i.setValue(0);
    		em.persist(i);
    		ids.put(i.getName(),i);
    		
    		i = new Identificador();
    		i.setName("Aplicacion");
    		i.setValue(0);
    		em.persist(i);
    		
    		ids.put(i.getName(),i);

    	}
    	else{
    	
    		Iterator<Identificador> it = ls.iterator();
	    	Identificador seq;
	    	while (it.hasNext()){
	    		seq = it.next();
	    		ids.put(seq.getName(),seq);
	    	}
    	}
    	em.close();
    }
    
    @Lock(LockType.WRITE)
    public long getId(String seq){
    	
    	Identificador iden = ids.get(seq);
    	long k = iden.getValue() + 1;
    	iden.setValue(k);
//    	EntityManager em = emf.createEntityManager();
//    	em.persist(iden);
//    	em.close();
    	return k;
    	
    }
    

}
