
package negocio.gerenciamento;

import negocio.entidade.Pedido;
import dados.interfaces.IRepPedido;
import dados.repositorios.RepPedido;
import java.util.ArrayList;
import negocio.entidade.AlimentoPedido;
import negocio.excecoes.pedido.AlimentoNaoFoiPedidoException;
import negocio.excecoes.pedido.PedidoEntregueException;
import negocio.excecoes.pedido.PedidoNaoExisteException;

/** Gerenciador para Pedido
 * 
 * @author Vinícius
 */
public class GerenciamentoPedido {
    private IRepPedido repositorio;
    private static GerenciamentoPedido instancia;
    
    /** Construtor para GerenciamentoPedido
     * 
     */
    private GerenciamentoPedido() {
        this.repositorio = new RepPedido();
        repositorio.carregar();
    }
    
    /** Método que retorna um controle para Pedido
     * 
     * @return GerenciamentoPedido - controle para Pedido
     */
    public static GerenciamentoPedido getInstance() {
        if(instancia == null) {
            instancia = new GerenciamentoPedido();
        }
        return instancia;
    }
    
    /** Método que adiciona um Pedido
     * 
     * @param pedido Pedido - Pedido a ser adicionado
     */
    public void adicionarPedido(Pedido pedido) {
        repositorio.adicionarPedido(pedido);
    }
    
    /** Métodod que remove um Pedido
     * 
     * @param pedido Pedido - Pedido a ser removido
     * @throws PedidoEntregueException 
     */
    public void removerPedido(Pedido pedido) throws PedidoEntregueException {
        if(pedido.getEstado().equals("Entregue")) {
            throw new PedidoEntregueException();
        } else {
            repositorio.removerPedido(pedido);
        }
    }
    
    /** Método que retorna um Pedido sendo fornecido sua id
     * 
     * @param id int - id do Pedido
     * @return Pedido - se existir, null caso contrário
     * @throws PedidoNaoExisteException 
     */
    public Pedido procurarPedido(int id) throws PedidoNaoExisteException {
        Pedido pedido = repositorio.procurarPedido(id);
        if(pedido == null) {
            throw new PedidoNaoExisteException();
        } else {
            return pedido;
        }
    }
    
    /** Método que adiciona um Alimento ao Pedido
     * 
     * @param pedido Pedido - Pedido a ser acrescentado 
     * @param alimentoPedido AlimentoPedido - o que Cliente deseja mais a quantidade
     * @throws PedidoEntregueException 
     */
    public void adicionarAlimentoPedido(Pedido pedido, AlimentoPedido alimentoPedido) throws PedidoEntregueException {
        if(pedido.getEstado().equals("Entregue")) {
            throw new PedidoEntregueException();
        } else {
            pedido.adicionarAlimentoPedido(alimentoPedido);
        }
    }
    
    /** Método que remove um alimento que o Cliente pediu
     * 
     * @param pedido Pedido a ser modificado
     * @param nomeAlimento String - o que foi pedido
     * @throws AlimentoNaoFoiPedidoException 
     */
    public void removerAlimentoPedido(Pedido pedido, String nomeAlimento) throws AlimentoNaoFoiPedidoException {
        AlimentoPedido alimentoPedido = procurarAlimentoPedido(pedido, nomeAlimento);
        pedido.removerAlimentoPedido(alimentoPedido);
    }
    
    /** Método que retorna um determinado AlimentoPedido que o Cliente pediu
     * 
     * @param pedido Pedido - Pedido onde se encontra
     * @param nomeAlimente String - nome do Alimento
     * @return AlimentoPedido - o Alimento que foi pedido
     * @throws AlimentoNaoFoiPedidoException 
     */
    private AlimentoPedido procurarAlimentoPedido(Pedido pedido, String nomeAlimente) throws AlimentoNaoFoiPedidoException {
        AlimentoPedido alimentoPedido = pedido.procurarAlimentoPedido(nomeAlimente);
        if(alimentoPedido == null) {
            throw new AlimentoNaoFoiPedidoException();
        } else {
            return alimentoPedido;
        }
    }
    
    /** Métodod que retorna o array com todos os pedidos
     * 
     * @return ArrayList<Pedido> - lista com todos os pedidos
     */
    public ArrayList<Pedido> buscarPedidos() {
        return repositorio.getPedidos();
    }
    
    /** Método que modifica a quantidade de um determinado Alimento pedido
     * 
     * @param pedido Pedido - Pedido no qual foi feito
     * @param nomeAlimento String - nome do Alimento
     * @param quantidade int - quantidade a ser colocada 
     * @throws AlimentoNaoFoiPedidoException 
     */
    public void editarQuantidade(Pedido pedido, String nomeAlimento, int quantidade) throws AlimentoNaoFoiPedidoException {
        AlimentoPedido alimentoPedido = procurarAlimentoPedido(pedido, nomeAlimento);
        if(quantidade == 0) {
            removerAlimentoPedido(pedido, nomeAlimento);
        } else {
            alimentoPedido.setQuantidade(quantidade);
        }
    }
    
    /** Método que verifica se as informações em determinado Pedido são válidas
     * 
     * @param pedido Pedido - o Pedido a ser verificado
     * @return boolean - indica se é válido
     */
    private boolean pedidoEValido(Pedido pedido) {
        if(pedido.getArrayAlimento().isEmpty() || pedido.getPrecoTotal() <= 0) {
            return false;
        } else {
            return true;
        }
    }
    
    /** Método que salva as alterações feitas
     *  
     */
    public void salvar() {
        this.repositorio.salvar();
    }
    
    /** Método que carrega todos os dados guardados
     * 
     * @throws ClassNotFoundException 
     */
    public void carregar() throws ClassNotFoundException {
        this.repositorio.carregar();
    }
}
