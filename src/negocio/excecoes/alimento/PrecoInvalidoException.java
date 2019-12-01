/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.excecoes.alimento;

/**
 *
 * @author vinicius9812
 */
public class PrecoInvalidoException extends Exception {
    public PrecoInvalidoException() {
        super("Preco digite nao eh valido");
    }
    public PrecoInvalidoException(String msg) {
        super(msg);
    }
}
