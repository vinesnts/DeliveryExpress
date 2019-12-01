
package negocio.excecoes.pessoa;

public class PessoaNaoExisteException extends PessoaException{
    public PessoaNaoExisteException() {
        super("Pessoa não existe");
    }
    public PessoaNaoExisteException(String msg) {
        super(msg);
    }
}
