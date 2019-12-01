/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.excecoes.alimento;

/**
 *
 * @author Edvaldo
 */
public class AlimentoNaoExisteException extends Exception{
    public AlimentoNaoExisteException(){
        super("Alimento nao encontrado.");
    }
    
    public AlimentoNaoExisteException(String msg){
        super(msg);
    }
}
