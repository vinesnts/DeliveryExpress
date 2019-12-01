
package fachadas;

import java.util.ArrayList;
import negocio.entidade.Alimento;
import negocio.entidade.AlimentoPedido;
import negocio.entidade.Cliente;
import negocio.entidade.Funcionario;
import negocio.entidade.Pedido;
import negocio.entidade.Pessoa;
import negocio.excecoes.alimento.AlimentoInvalidoException;
import negocio.excecoes.alimento.AlimentoJaExisteException;
import negocio.excecoes.alimento.AlimentoNaoExisteException;
import negocio.excecoes.alimento.PrecoInvalidoException;
import negocio.excecoes.pedido.PedidoNaoExisteException;
import negocio.excecoes.pessoa.CpfPessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaJaExisteException;
import negocio.excecoes.pessoa.PessoaNaoExisteException;

/** Fachada para Funcionario
 * 
 * @author Vinícius
 */
public interface IFachadaFuncionario {
    Pessoa getPessoa();
    void setPessoa();
    Cliente buscarCliente(String cpf) throws CpfPessoaInvalidoException, PessoaNaoExisteException;
    void cadastrarFuncionario(String nome, String cpf, String senha) throws PessoaJaExisteException, PessoaInvalidoException, PessoaNaoExisteException;
    void removerFuncionario(String cpf) throws PessoaNaoExisteException, CpfPessoaInvalidoException;
    Funcionario buscarFuncionario(String cpf) throws CpfPessoaInvalidoException, PessoaNaoExisteException;
    void atualizarFuncionario(String cpf, String nome) throws CpfPessoaInvalidoException, PessoaNaoExisteException;
    void cadastrarAlimento(String nome, String preco, String tipo) throws AlimentoInvalidoException, AlimentoJaExisteException, PrecoInvalidoException, NumberFormatException;
    void removerAlimento(String nome) throws AlimentoNaoExisteException;
    Alimento buscarAlimento(String nome) throws AlimentoNaoExisteException;
    void atualizarAlimento(String nome, String novoNome, double preco) throws AlimentoNaoExisteException, PrecoInvalidoException, AlimentoInvalidoException;
    Pedido buscarPedido(int id) throws PedidoNaoExisteException;
    ArrayList<Pedido> buscarPedidosFuncionario();
    ArrayList<AlimentoPedido> buscarAlimentoPedido(int idPedido) throws PedidoNaoExisteException;

    public ArrayList<Alimento> buscarCardapio();
}
