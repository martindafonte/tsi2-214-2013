package persistencia;

import javax.ejb.Local;

import modelo.Aplicacion;
import modelo.Pedido;

@Local
public interface PedidosDAOLocal {

	public void altaPedidoJSONAplicacion(Aplicacion a, Pedido p);
	public void altaPedidoPUSHAplicacion(Aplicacion a, Pedido p);
	public void altaPedidoUMAplicacion(Aplicacion a, Pedido p);
}
