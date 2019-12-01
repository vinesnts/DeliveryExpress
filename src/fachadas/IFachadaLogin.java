
package fachadas;

import negocio.excecoes.pessoa.CpfPessoaInvalidoException;
import negocio.excecoes.pessoa.EnderecoInvalidoException;
import negocio.excecoes.pessoa.PessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaJaExisteException;
import negocio.excecoes.pessoa.PessoaNaoExisteException;
import negocio.excecoes.pessoa.SenhaPessoaInvalidaException;

/** Fachada para login de Cliente e Funcionario
 * 
 * @author Vinícius
 */
public interface IFachadaLogin {
    public void logarFuncionario(String cpf, String senha) throws CpfPessoaInvalidoException, SenhaPessoaInvalidaException, PessoaNaoExisteException;
    public void logarCliente(String cpf, String senha) throws CpfPessoaInvalidoException, SenhaPessoaInvalidaException, PessoaNaoExisteException;
    public void cadastrarCliente(String nome, String cpf, String senha, String rua, String numero, String bairro, String cidade) throws PessoaJaExisteException, PessoaInvalidoException, PessoaNaoExisteException, EnderecoInvalidoException;
}
