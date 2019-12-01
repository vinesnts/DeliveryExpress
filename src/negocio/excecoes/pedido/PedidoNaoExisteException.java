/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.excecoes.pedido;

/**
 *
 * @author vinicius9812
 */
public class PedidoNaoExisteException extends PedidoException {
    public PedidoNaoExisteException() {
        super("Pedido nao existe");
    }
    public PedidoNaoExisteException(String msg) {
        super(msg);
    }

}
