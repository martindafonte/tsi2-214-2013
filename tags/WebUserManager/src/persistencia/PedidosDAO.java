package persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.google.gson.JsonObject;

import presentacion.CanalSesBean;
import presentacion.PedSesBean;
import modelo.Aplicacion;
import modelo.Canal;
import modelo.PedidoJson;
import modelo.PedidoMsj;
import modelo.PedidoPush;
import modelo.PedidoUser;
import modelo.Usuario;

/**
 * Session Bean implementation class PedidosDAO
 */
@Stateless
@LocalBean
public class PedidosDAO implements PedidosDAOLocal {

	/**
	 * Default constructor.
	 */

	@PersistenceContext(unitName = "WebUserManager")
	EntityManager em;

	public PedidosDAO() {
	}

	@Override
	public void altaPedidoJSONAplicacion(long app, PedidoJson p) {
		try {
			em.persist(p);
			Aplicacion a = em.find(Aplicacion.class, app);
			List<PedidoJson> lp = a.getPedidosJson();
			lp.add(p);
			a.setPedidosJson(lp);
			em.flush();

		} catch (Throwable ex) {
			System.out.println("ERROR EN ALTA PEDIDO JSON!!!");

		}
	}

	@Override
	public void altaPedidoPUSHAplicacion(long app, PedidoPush p) {
		try {
			em.persist(p);
			Aplicacion a = em.find(Aplicacion.class, app);
			List<PedidoPush> lp = a.getPedidosPush();
			lp.add(p);
			a.setPedidosPush(lp);
			em.flush();

		} catch (Throwable ex) {
			System.out.println("ERROR EN ALTA PEDIDO PUSH!!!");
		}
	}

	@Override
	public void altaPedidoUMAplicacion(long app, PedidoUser p, String user) {
		try {
			Query q = em
					.createQuery("SELECT x FROM Usuario x WHERE x.nick = ?1 and aplicacion_id = ?2");
			q.setParameter(1, user).setParameter(2, app);
			Usuario u = (Usuario) q.getSingleResult();
			p.setUsuario(u);
			em.persist(p);
			Aplicacion a = em.find(Aplicacion.class, app);
			List<PedidoUser> lp = a.getPedidosUM();
			lp.add(p);
			a.setPedidosUM(lp);
			em.flush();

		} catch (Throwable ex) {
			System.out.println("ERROR EN ALTA PEDIDO USER!!!");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void altaPedidoMSJ(PedidoMsj p, String canId, long app) {

		try {
			Query q = em
					.createQuery("SELECT x FROM Canal x WHERE x.codigo = ?1 and app_id = ?2");
			q.setParameter(1, canId).setParameter(2, app);
			List<Canal> lc = q.getResultList();
			Canal c = lc.get(0);
			p.setCanal(c);
			em.persist(p);

		} catch (Throwable ex) {
			System.out.println("ERROR EN ALTA PEDIDO MSJ!!!");
		}
	}

	@Override
	public List<PedSesBean> getPedidos(long app) {
		try {
			Aplicacion a = em.find(Aplicacion.class, app);
			ArrayList<PedSesBean> lp = new ArrayList<PedSesBean>();
			HashMap<String, Integer> map = new HashMap<String, Integer>();

			Iterator<PedidoJson> it1 = a.getPedidosJson().iterator();
			while (it1.hasNext()) {
				PedidoJson p = it1.next();
				if (map.containsKey(p.getUrl())) {
					int cant = map.remove(p.getUrl());
					cant = cant + 1;
					map.put(p.getUrl(), cant);
				} else
					map.put(p.getUrl(), 1);
			}
			Iterator<PedidoPush> it2 = a.getPedidosPush().iterator();
			while (it2.hasNext()) {
				PedidoPush p = it2.next();
				if (map.containsKey(p.getUrl())) {
					int cant = map.remove(p.getUrl());
					cant = cant + 1;
					map.put(p.getUrl(), cant);
				} else
					map.put(p.getUrl(), 1);
			}
			Iterator<PedidoUser> it3 = a.getPedidosUM().iterator();
			while (it3.hasNext()) {
				PedidoUser p = it3.next();
				if (map.containsKey(p.getUrl())) {
					int cant = map.remove(p.getUrl());
					cant = cant + 1;
					map.put(p.getUrl(), cant);
				} else
					map.put(p.getUrl(), 1);
			}

			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String u = it.next();
				PedSesBean p = new PedSesBean();
				p.setServicio(u);
				p.setCantidad(map.get(u));
				lp.add(p);
			}

			return lp;

		} catch (Throwable ex) {
			System.out.println("ERROR EN ALTA PEDIDO USER!!!");
		}
		return new ArrayList<PedSesBean>();
	}

	@Override
	public JsonObject getPedidosJson(long app) {
		try {
			Aplicacion a = em.find(Aplicacion.class, app);
			JsonObject pj = new JsonObject();
			HashMap<String, Integer> map = new HashMap<String, Integer>();

			Iterator<PedidoJson> it1 = a.getPedidosJson().iterator();
			while (it1.hasNext()) {
				PedidoJson p = it1.next();
				if (map.containsKey(p.getUrl())) {
					int cant = map.remove(p.getUrl());
					map.put(p.getUrl(), cant++);
				} else
					map.put(p.getUrl(), 1);
			}
			Iterator<PedidoPush> it2 = a.getPedidosPush().iterator();
			while (it2.hasNext()) {
				PedidoPush p = it2.next();
				if (map.containsKey(p.getUrl())) {
					int cant = map.remove(p.getUrl());
					map.put(p.getUrl(), cant++);
				} else
					map.put(p.getUrl(), 1);
			}
			Iterator<PedidoUser> it3 = a.getPedidosUM().iterator();
			while (it3.hasNext()) {
				PedidoUser p = it3.next();
				if (map.containsKey(p.getUrl())) {
					int cant = map.remove(p.getUrl());
					map.put(p.getUrl(), cant++);
				} else
					map.put(p.getUrl(), 1);
			}

			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String u = it.next();
				pj.addProperty(u, map.get(u));
			}
			return pj;

		} catch (Throwable ex) {
			System.out.println("ERROR EN ALTA PEDIDO USER!!!");
		}
		return new JsonObject();
	}

	@Override
	public int getMensajes(long app, String canId) {
		try {
			Aplicacion a = em.find(Aplicacion.class, app);
			int cant = 0;
			Iterator<PedidoMsj> it = a.getPedidosMsj().iterator();
			while (it.hasNext()) {
				PedidoMsj p = it.next();
				if (p.getCanal().getCodigo().equals(canId)) {
					cant++;
				}
			}
			return cant;
		} catch (Throwable ex) {
			System.out.println("ERROR EN ALTA PEDIDO USER!!!");
		}
		return 0;
	}

	@Override
	public List<PedSesBean> cantPedidosAPI(long aplicacionid) {

		try {
			Aplicacion a = em.find(Aplicacion.class, aplicacionid);
			ArrayList<PedSesBean> lp = new ArrayList<PedSesBean>();
			HashMap<String, Integer> map = new HashMap<String, Integer>();

			Iterator<PedidoJson> it1 = a.getPedidosJson().iterator();
			while (it1.hasNext()) {
				PedidoJson p = it1.next();
				if (map.containsKey(p.getUrl())) {
					int cant = map.remove(p.getUrl());
					cant = cant + 1;
					map.put(p.getUrl(), cant);
				} else
					map.put(p.getUrl(), 1);
			}

			Iterator<PedidoUser> it3 = a.getPedidosUM().iterator();
			while (it3.hasNext()) {
				PedidoUser p = it3.next();
				if (map.containsKey(p.getUrl())) {
					int cant = map.remove(p.getUrl());
					cant = cant + 1;
					map.put(p.getUrl(), cant);
				} else
					map.put(p.getUrl(), 1);
			}

			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String u = it.next();
				PedSesBean p = new PedSesBean();
				p.setServicio(u);
				p.setCantidad(map.get(u));
				lp.add(p);
			}

			return lp;

		} catch (Throwable ex) {
			
		}
		return new ArrayList<PedSesBean>();
	}

	@Override
	public List<PedSesBean> cantDespRegCanales(long appId) {

		List<PedSesBean> lp = new ArrayList<PedSesBean>();
		
		try{
						
			Aplicacion a = em.find(Aplicacion.class, appId);
			if (a != null){
			
				List<CanalSesBean> lc = a.getCanalesAppSesAll();
				CanalSesBean c = null;
				Iterator<CanalSesBean> itc = lc.iterator();
				PedSesBean p = null;
				while(itc.hasNext()){
					
					p = new PedSesBean();
					c = itc.next();
					p.setServicio(c.getCodigo());
					p.setCantidad(c.getRegistrados());
					lp.add(p);
				}
					
			}			
			return lp;
						
		}catch(Exception e){
			
			return lp;
			
		}
	}

	@Override
	public List<PedSesBean> cantMsjEnvPush(long appId) {

		
		List<PedSesBean> lp = new ArrayList<PedSesBean>();
		
		try{
						
			Aplicacion a = em.find(Aplicacion.class, appId);
			if (a != null){
			
				lp = a.getCanalesAppSesAllMsj();
					
			}			
			return lp;
						
		}catch(Exception e){
			
			return lp;
			
		}
		
	}
	
	
	
}
