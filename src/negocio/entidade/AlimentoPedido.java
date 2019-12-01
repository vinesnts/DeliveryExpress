/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;

/** Classe para instaciar objetos do tipo AlimentoPedido.
 * com métodos e atributos para o mesmo
 * 
 * @author Vinícius
 */
public class AlimentoPedido implements Serializable {
    private Alimento alimento;
    private int quantidade;
    
    /** Construtor para AlimentoPedido com 3 atributos
     * 
     * @param alimento Alimento - o que o cliente pediu
     * @param quantidade int - a quantidade que o cliente pediu
     */
    public AlimentoPedido(Alimento alimento, int quantidade) {
        this.alimento = alimento;
        this.quantidade = quantidade;
    }
    
    /** Método que retorna o preço total do pedido de determinado Alimento
     * 
     * @return double - o preco dependendo da quantidade de alimentos 
     */
    public double getSubTotal() {
        double precoSubTotal;
        precoSubTotal = this.alimento.getPreco() * this.quantidade;
        return precoSubTotal;
    }
    
    /** Método que retorna o Alimento do AlimentoPedido
     * 
     * 
     * @return Alimento - alimento do AlimentoPedido
     */
    public Alimento getAlimento() {
        return this.alimento;
    }
    
    /** Método que retorna a quantidade do AlimentoPedido
     * 
     * @return int - quantidade do AlimentoPedido 
     */
    public int getQuantidade() {
        return this.quantidade;
    }

    /** Método que modifica a quantidade do AlimentoPedido
     * 
     * @param quantidade int - nova quantidade a ser colocada
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /** Método que retorna os atributos do AlimentoPedido em forma de String
     * 
     * @return String - atributos em uma String
     */
    @Override
    public String toString() {
        return alimento.getTipo() + ": " + alimento.getNome() + " | Quant.: " + quantidade;
    }
    
    
}
