
package negocio.excecoes.pessoa;

public class PessoaException extends Exception {
    public PessoaException() {
        super("Erro em Pessoa");
    }
    public PessoaException(String msg) {
        super(msg);
    }
    
    
}
