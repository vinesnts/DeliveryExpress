/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.excecoes.pessoa;

/**
 *
 * @author vinicius9812
 */
public class SenhaPessoaInvalidaException extends Exception {

    public SenhaPessoaInvalidaException() {
        super("Senha invalida");
    }
    public SenhaPessoaInvalidaException(String msg) {
        super(msg);
    }
    
}
