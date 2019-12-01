
package negocio.gerenciamento;

import negocio.entidade.Cliente;
import dados.interfaces.IRepCliente;
import dados.repositorios.RepCliente;
import negocio.entidade.Endereco;
import negocio.excecoes.pessoa.PessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaJaExisteException;
import negocio.excecoes.pessoa.PessoaNaoExisteException;
import negocio.excecoes.pessoa.CpfPessoaInvalidoException;
import negocio.excecoes.pessoa.EnderecoInvalidoException;
import negocio.excecoes.pessoa.SenhaPessoaInvalidoException;

/** Gerenciador para Cliente
 * 
 * @author Vinícius
 */
public class GerenciamentoCliente {
       
    private IRepCliente repositorio;
    private static GerenciamentoCliente instancia;
    
    /** Construtor para GerenciamentoCliente,
     *  
     */
    private GerenciamentoCliente() {
        repositorio = new RepCliente();
        this.repositorio.carregar();
    }
    
    /** Método que instancia um objeto GerenciamentoCliente
     * 
     * @return GerenciamentoCliente - retorna um controle para Cliente
     */
    public static GerenciamentoCliente getInstance() {
        if(instancia == null){
            instancia = new GerenciamentoCliente();
        }
        return instancia;
    }
    /** Método que adiciona um Cliente
     * 
     * @param cliente Cliente - o cliente a ser adicionado
     * @throws PessoaJaExisteException
     * @throws PessoaInvalidoException
     * @throws PessoaNaoExisteException
     * @throws EnderecoInvalidoException 
     */
    public void adicionarCliente(Cliente cliente) throws PessoaJaExisteException, PessoaInvalidoException, PessoaNaoExisteException, EnderecoInvalidoException{
        if(!clienteEValido(cliente)) {
            throw new PessoaInvalidoException("Cliente invalido");
        } if(existe(cliente.getCpf())) {
            throw new PessoaJaExisteException();
        }if(!enderecoEValido(cliente.getEndereco())) {
            throw new EnderecoInvalidoException();
        } else {
            repositorio.adicionarCliente(cliente);
        }
    }
    
    /** Método que remove um Cliente
     * 
     * @param cpfLogado String - cpf do cliente logado que deseja remover sua conta
     * @param cpf String - cpf a ser procurado no banco de dados
     * @param senha String - senha do Cliente
     * @throws PessoaNaoExisteException
     * @throws SenhaPessoaInvalidoException
     * @throws PessoaInvalidoException 
     */
    public void removerCliente(String cpfLogado, String cpf, String senha) throws PessoaNaoExisteException, SenhaPessoaInvalidoException, PessoaInvalidoException {
        Cliente cliente = procurarCliente(cpf);
        if(cliente.getSenha().equals(senha) && cpfLogado.equals(cpf)) {
            repositorio.removerCliente(cliente);
        } else {
            throw new SenhaPessoaInvalidoException();
        }
    }
    
    /** Método que retorna um Cliente
     * 
     * @param cpf String - cpf do Cliente procurado
     * @return Cliente - se existir ou null caso não exista
     * @throws CpfPessoaInvalidoException
     * @throws PessoaNaoExisteException 
     */
    public Cliente procurarCliente(String cpf) throws CpfPessoaInvalidoException, PessoaNaoExisteException {
        if(cpf.length() != 11) {
            throw new CpfPessoaInvalidoException();
        } else if(!existe(cpf)) {
            throw new PessoaNaoExisteException("Cliente nao existe");
        } else {
            return repositorio.procurarCliente(cpf);
        }
    }
    
    /** Método que indica se um Cliente com determinado cpf existe
     * 
     * @param cpf String - cpf do Cliente
     * @return boolean - retorna true se exister e false caso contrário
     */
    private boolean existe(String cpf) {
        if(repositorio.procurarCliente(cpf) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /** Método que indica se as informações de determinado Cliente são válidas
     * 
     * @param cliente Cliente - Cliente a ser verificado
     * @return boolean - indica se é válido
     */
    private boolean clienteEValido(Cliente cliente) {
        if(cliente.getCpf().length() != 11 || cliente.getNome().isEmpty() || cliente.getSenha().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    /** Método que indica se um endereço é válido
     * 
     * @param endereco Endereço - Endereço a ser verificado
     * @return boolean - indicando se é válido
     */
    public boolean enderecoEValido(Endereco endereco) {
        if(endereco.getRua().isEmpty() || endereco.getNumero().isEmpty() || endereco.getBairro().isEmpty() || endereco.getCidade().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    /** Método que salva as alterações feitas nos dados de Cliente
     * 
     */
    public void salvar() {
        this.repositorio.salvar();
    }
    
    /** Método que carrega os dados de Cliente
     * 
     * @throws ClassNotFoundException 
     */
    public void carregar()  throws ClassNotFoundException {
        this.repositorio.carregar();
    }
}
