
package fachadas;

import java.util.ArrayList;
import negocio.entidade.Alimento;
import negocio.entidade.Pedido;
import negocio.entidade.Pessoa;
import negocio.excecoes.alimento.AlimentoNaoExisteException;
import negocio.excecoes.pedido.AlimentoNaoFoiPedidoException;
import negocio.excecoes.pedido.PedidoEntregueException;
import negocio.excecoes.pedido.PedidoNaoExisteException;
import negocio.excecoes.pessoa.CpfPessoaInvalidoException;
import negocio.excecoes.pessoa.EnderecoInvalidoException;
import negocio.excecoes.pessoa.PessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaNaoExisteException;
import negocio.excecoes.pessoa.SenhaPessoaInvalidaException;
import negocio.excecoes.pessoa.SenhaPessoaInvalidoException;
import negocio.excecoes.pessoa.SenhasNaoConferemException;

/**  Fachada para Cliente
 * 
 * @author Vinícius
 */
public interface IFachadaCliente {
    Pessoa getPessoa();
    void setPessoa();
    void alterarNome(String nome);
    void removerCliente(String cpfLogado, String cpf, String senha) throws PessoaNaoExisteException, SenhaPessoaInvalidoException, PessoaInvalidoException;
    void atualizarCliente(String rua, String numero, String bairro, String cidade) throws CpfPessoaInvalidoException, PessoaNaoExisteException, EnderecoInvalidoException;
    public void alterarSenhaCliente(String senha, String novaSenha, String repetirNovaSenha) throws CpfPessoaInvalidoException, PessoaNaoExisteException, SenhasNaoConferemException, SenhaPessoaInvalidaException;
    public Pedido adicionarPedido();
    void removerPedido(int id) throws PedidoNaoExisteException, PedidoEntregueException;
    Pedido buscarPedido(int id) throws PedidoNaoExisteException;
    ArrayList<Pedido> buscarPedidos();
    ArrayList<Alimento> buscarCardapio();
    void adicionarAlimentoPedido(Pedido pedido, String nomeAlimento, int quantidade) throws AlimentoNaoExisteException, PedidoEntregueException;
    public void removerAlimentoPedido(Pedido pedido, String nomeAlimento) throws AlimentoNaoFoiPedidoException, AlimentoNaoExisteException;
    public void editarQuantidade(Pedido pedido, String nomeAlimento, int quantidade) throws AlimentoNaoFoiPedidoException;
    
    
}
