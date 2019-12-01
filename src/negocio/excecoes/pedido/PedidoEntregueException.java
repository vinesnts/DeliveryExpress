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
public class PedidoEntregueException extends PedidoException {
    public PedidoEntregueException() {
        super("Pedido já entregue");
    }
    public PedidoEntregueException(String msg) {
        super(msg);
    }
}
