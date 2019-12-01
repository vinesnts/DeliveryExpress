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
public class PessoaJaExisteException extends PessoaException {
    public PessoaJaExisteException() {
        super("Pessoa já cadastrada");
    }
    public PessoaJaExisteException(String msg) {
        super(msg);
    }
}
