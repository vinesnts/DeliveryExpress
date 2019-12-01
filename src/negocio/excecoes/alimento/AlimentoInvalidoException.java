package negocio.excecoes.alimento;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edvaldo
 */
public class AlimentoInvalidoException extends Exception{
    public AlimentoInvalidoException() {
        super("Alguma informacao errada no alimento a ser cadastrado.");
    }
    
    public AlimentoInvalidoException(String msg){
        super(msg);
    }
}
