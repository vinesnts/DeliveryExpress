/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.excecoes.pessoa;

/**
 *
 * @author USUARIO
 */
public class PessoaInvalidoException extends PessoaException{
    public PessoaInvalidoException() {
        super("Alguma informação da pessoa está inválida");
    }
    public PessoaInvalidoException(String msg) {
        super(msg);
    }
}
