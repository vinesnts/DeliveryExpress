
package negocio.gerenciamento;
import negocio.entidade.Funcionario;
import dados.interfaces.IRepFuncionario;
import dados.repositorios.RepFuncionario;
import negocio.excecoes.pessoa.CpfPessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaJaExisteException;
import negocio.excecoes.pessoa.PessoaNaoExisteException;

/** Gerenciador para Funcionario
 * 
 * @author Edvaldo
 */
public class GerenciamentoFuncionario {
    private IRepFuncionario repositorio;
    private static GerenciamentoFuncionario instancia;
    
    /** Construtor para GerenciamentoFuncionario
     * 
     */
    private GerenciamentoFuncionario() {
        this.repositorio = new RepFuncionario();
        repositorio.carregar();
    }
    
    /** Método que retorna um controle para Funcionario
     * 
     * @return GerenciamentoFuncionario - controle de Funcionario
     */
    public static GerenciamentoFuncionario getInstance() {
        if(instancia == null) {
            instancia = new GerenciamentoFuncionario();
        }
        return instancia;
    }
    
    /** Método que adiciona um Funcionario
     * 
     * @param funcionario Funcionario - Funcionario a ser adicionado
     * @throws PessoaJaExisteException
     * @throws CpfPessoaInvalidoException
     * @throws PessoaInvalidoException 
     */
    public void adicionarFuncionario(Funcionario funcionario) throws PessoaJaExisteException, CpfPessoaInvalidoException, PessoaInvalidoException {
        if (!funcionarioValido(funcionario)) {
            throw new PessoaInvalidoException("Funcionario nao valido.");
        } else if (existe(funcionario.getCpf())) {
            throw new PessoaJaExisteException();
        }else {
            repositorio.adicionarFuncionario(funcionario);
        }
    }
    
    /** Método que remove um Funcionario
     * 
     * @param cpf String - cpf do Funcionario
     * @throws PessoaNaoExisteException
     * @throws CpfPessoaInvalidoException 
     */
    public void removerFuncionario(String cpf) throws PessoaNaoExisteException, CpfPessoaInvalidoException {
        Funcionario funcionario = procurarFuncionario(cpf);
        if (!existe(funcionario.getCpf())){
            throw new PessoaNaoExisteException();
        } else {
            repositorio.removerFuncionario(funcionario);
        }
    }
    
    /** Método que retorna um Funcionario sendo fornecido seu cpf
     * 
     * @param cpf String - cpf do Funcionario a ser procurado
     * @return Funcionario - Funcionario procurado
     * @throws CpfPessoaInvalidoException
     * @throws PessoaNaoExisteException 
     */
    public Funcionario procurarFuncionario(String cpf) throws CpfPessoaInvalidoException, PessoaNaoExisteException {
        if(cpf.length() != 11) {
            throw new CpfPessoaInvalidoException();
        } else if(!existe(cpf)) {
            throw new PessoaNaoExisteException("Funcionario nao existe");
        } else {
            return repositorio.procurarFuncionario(cpf);
        }
    }
    
    /** Método que indica se um Funcionario existe 
     * 
     * @param cpf String - cpf do Funcionario
     * @return 
     */
    private boolean existe(String cpf) {
        if(repositorio.procurarFuncionario(cpf) != null){
            return true;
        } else {
            return false;
        }
    }
    
    /** Método que indica se um as informações de um Funcionario são válidas
     * 
     * @param funcionario Funcionario - Funcionario a ser verificado
     * @return boolean - indicando se é válido
     */
    private boolean funcionarioValido(Funcionario funcionario){
        if (funcionario.getNome().isEmpty() || funcionario.getCpf().length() != 11 || funcionario.getSenha().isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    /** Método que salva as alterações feitas
     * 
     */
    public void salvar() {
        this.repositorio.salvar();
    }
    
    /** Método que carrega os dados guardados
     * 
     * @throws ClassNotFoundException 
     */
    public void carregar() throws ClassNotFoundException {
        this.repositorio.carregar();
    }
}
