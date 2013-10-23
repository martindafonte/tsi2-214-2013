package persistencia;

import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Canal;
import modelo.Registro;

/**
 * Session Bean implementation class CanalDAO
 */
@Stateless
@LocalBean
public class CanalDAO implements CanalDAOLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext(unitName="WebUserManager")
	private EntityManager em;
	
    public CanalDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void altaCanal(Canal c) {
		// TODO Auto-generated method stub
		em.persist(c);
	}

	@Override
	public void borrarCanal(Canal c) {
		// TODO Auto-generated method stub
		List<Registro> lr = c.getRegistrados();
		Iterator<Registro> itlr = lr.iterator();
		while(itlr.hasNext()){
			Registro r = itlr.next();
			r.quitarCanal(c);
		}
		em.remove(c);
	}

	@Override
	public void agregarRegistroCanal(Canal c, Registro r) {
		// TODO Auto-generated method stub
		c.getRegistrados().add(r);
		r.getCanales().add(c);
		em.persist(c);
		em.persist(r);
	}

	@Override
	public void quitarRegistroCanal(Canal c, Registro r) {
		// TODO Auto-generated method stub
		r.quitarCanal(c);
		c.quitarRegistro(r);
		em.persist(r);
		em.persist(c);

	}
    
    

}
