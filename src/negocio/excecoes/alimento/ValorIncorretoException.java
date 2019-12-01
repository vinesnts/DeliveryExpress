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
public class ValorIncorretoException  extends Exception {
    public ValorIncorretoException() {
        super("Valor digitado incorreto");
    }
    public ValorIncorretoException(String msg) {
        super(msg);
    }
}
