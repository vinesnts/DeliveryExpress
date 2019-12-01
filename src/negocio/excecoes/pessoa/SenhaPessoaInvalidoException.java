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
public class SenhaPessoaInvalidoException extends PessoaInvalidoException {
    public SenhaPessoaInvalidoException() {
        super("Senha da pessoa invalida");
    }
    public SenhaPessoaInvalidoException(String msg) {
        super(msg);
    }
}
