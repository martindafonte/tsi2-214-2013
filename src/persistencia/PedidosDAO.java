package persistencia;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import modelo.Aplicacion;
import modelo.Pedido;

/**
 * Session Bean implementation class PedidosDAO
 */
@Stateless
@LocalBean
public class PedidosDAO implements PedidosDAOLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext(unitName="WebUserManager")
	EntityManager em;
	
    public PedidosDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void altaPedidoJSONAplicacion(Aplicacion a, Pedido p) {
		// TODO Auto-generated method stub
		
		List<Pedido> lp = a.getPedidosJson();
		lp.add(p);
		a.setPedidosJson(lp);
		em.flush();
	}

	@Override
	public void altaPedidoPUSHAplicacion(Aplicacion a, Pedido p) {
		// TODO Auto-generated method stub
		
		List<Pedido> lp = a.getPedidosPush();
		lp.add(p);
		a.setPedidosPush(lp);
		em.flush();
		
	}

	@Override
	public void altaPedidoUMAplicacion(Aplicacion a, Pedido p) {
		// TODO Auto-generated method stub
		
		List<Pedido> lp = a.getPedidosUM();
		lp.add(p);
		a.setPedidosUM(lp);
		em.flush();
	}

}
