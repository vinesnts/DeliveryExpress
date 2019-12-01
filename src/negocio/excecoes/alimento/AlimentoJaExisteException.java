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
public class AlimentoJaExisteException extends Exception{
    public AlimentoJaExisteException(){
        super("Este alimento ja esta cadastrado.");
    }
    public AlimentoJaExisteException(String msg){
        super(msg);
    }
}
