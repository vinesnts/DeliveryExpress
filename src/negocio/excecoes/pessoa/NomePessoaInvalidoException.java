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
public class NomePessoaInvalidoException extends PessoaInvalidoException {
    public NomePessoaInvalidoException() {
        super("Nome da invalido");
    }
    public NomePessoaInvalidoException(String msg) {
        super(msg);
    }
}
