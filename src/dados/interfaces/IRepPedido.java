
package dados.interfaces;

import java.util.ArrayList;
import negocio.entidade.Pedido;

/** Interface para RepPedido
 * 
 * @author Vinícius
 */
public interface IRepPedido {
    void adicionarPedido(Pedido pedido);
    void removerPedido(Pedido pedido);
    Pedido procurarPedido(int id);
    public ArrayList<Pedido> getPedidos();
    void salvar();
    void carregar();
}