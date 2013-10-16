package negocio;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import modelo.Identificador;

/**
 * Session Bean implementation class AppInfo
 */
@Singleton
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class AppInfo implements AppInfoLocal {

	
	@PersistenceContext(unitName="WebUserManager")
	private EntityManager em;
    /**
     * Default constructor. 
     */
	
	public AppInfo(){}
	
	@PostConstruct
    public void AppInfo2() {
        // TODO Auto-generated constructor stub
    	
    	Query qu = em.createQuery("SELECT x FROM Identificador x");
    	@SuppressWarnings("unchecked")
		List<Identificador> ls = (List<Identificador>)qu.getResultList();
    	if( ls.isEmpty()){
    		
    		//agregar a mano, una masa... jaja
    		Identificador i = new Identificador();
    		i.setName("Usuario");
    		i.setValue(0);
    		em.persist(i);
    		
    		i = new Identificador();
    		i.setName("Desarrollador");
    		i.setValue(0);
    		em.persist(i);
    		
    		i = new Identificador();
    		i.setName("Aplicacion");
    		i.setValue(0);
    		em.persist(i);
    		
    	}
//    	em.close();
    }
    
    @Lock(LockType.WRITE)
    public long getId(String seq){
    	
    	Query qu = em.createQuery("SELECT x FROM Identificador x WHERE x.name = ?1");
    	qu.setParameter(1, seq);
    	@SuppressWarnings("unchecked")
		List<Identificador> ls = (List<Identificador>)qu.getResultList();
    	Identificador iden = ls.iterator().next();
   		long k = iden.getValue();
   		iden.setValue(k + 1);
   		return k;   		
    	
    }
    

}
