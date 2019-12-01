/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.entidade;

import java.io.Serializable;

/** Classe para instaciar objetos do tipo Endereco
 *  com métodos e atributos para o mesmo
 *
 * @author Vinicius
 */
public class Endereco implements Serializable {

    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    
    /** Construtor da Endereco com 4 parâmetros
     * 
     * @param rua String - rua do Cliente
     * @param numero String - número da casa do Cliente
     * @param bairro String - bairro do endereçp do Cliente
     * @param cidade String - cidade do Cliente
     */
    public Endereco(String rua, String numero, String bairro, String cidade) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade  = cidade;
    }
    
    /** Método que retorna a rua do Cliente
     * 
     * @return String - rua do Cliente
     */
    public String getRua() {
        return this.rua;
    }
    
    /** Método para modificar a rua do Cliente
     * 
     * @param rua String - rua atual do Cliente
     */
    public void setRua(String rua) {
        this.rua = rua;
    }
    
    /** Método que retorna o número da casa do Cliente
     * 
     * @return String - numero do endereço do Cliente
     */
    public String getNumero() {
        return this.numero;
    }
    
    /** Método que modifica o numero do Endereço
     * 
     * @param numero 
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    /** Método que retorna o bairro do Endereço
     * 
     * @return String - bairro do Endereço
     */
    public String getBairro() {
        return bairro;
    }
    
    /** Método que modifica o bairro do Endereço
     * 
     * @param bairro String - bairro a ser atualizado no Endereço 
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    /** Método que retorna a cidade
     * 
     * @return String - cidade
     */
    public String getCidade() {
        return this.cidade;
    }
    
    /** Método que modifica a cidade do Endereço
     * 
     * @param cidade String - cidade a ser colocada como atual
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /** Método que retorna os atributos de Endereço em forma de String
     * 
     * @return String - atributos
     */
    @Override
    public String toString() {
        return "Rua: " + getRua() + "| Num.: " + getNumero() + " | Bairro" + getBairro() + " | Cidade: " + getCidade();
    }
    
}
