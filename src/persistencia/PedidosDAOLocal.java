package persistencia;

import javax.ejb.Local;

import modelo.PedidoJson;
import modelo.PedidoMsj;
import modelo.PedidoPush;
import modelo.PedidoUser;

@Local
public interface PedidosDAOLocal {

	public void altaPedidoJSONAplicacion(long app, PedidoJson p);
	public void altaPedidoPUSHAplicacion(long app, PedidoPush p);
	public void altaPedidoUMAplicacion(long app, PedidoUser p, String user);
	public void altaPedidoMSJ(PedidoMsj p, String canId, long app);
}
