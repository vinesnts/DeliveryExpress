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
public class EnderecoInvalidoException extends Exception {
    public EnderecoInvalidoException() {
        super("Endereco do cliente invalido");
    }
    public EnderecoInvalidoException(String msg) {
        super(msg);
    }
}
