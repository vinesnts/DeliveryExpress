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
public class SenhasNaoConferemException extends Exception {
    public SenhasNaoConferemException() {
        super("As senhas digitadas não conferem");
    }
    public SenhasNaoConferemException(String msg) {
        super(msg);
    }
    
}
