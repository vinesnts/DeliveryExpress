
package negocio.gerenciamento;

import dados.interfaces.IRepAlimento;
import dados.repositorios.RepAlimento;
import java.util.ArrayList;
import negocio.entidade.Alimento;
import negocio.excecoes.alimento.AlimentoInvalidoException;
import negocio.excecoes.alimento.AlimentoJaExisteException;
import negocio.excecoes.alimento.AlimentoNaoExisteException;

/** Classe para controle de Alimento
 * 
 * @author Edvaldo
 */
public class GerenciamentoAlimento {
    private IRepAlimento  repositorio;
    private static GerenciamentoAlimento instancia;
    
    /** Construtor para GerenciamentoAlimento
     *  
     */
    private GerenciamentoAlimento() {
        this.repositorio = new RepAlimento();
        this.repositorio.carregar();
    }
    
    /** Método que retorna uma instancia de GerenciamentoAlimento
     * 
     */
    public static GerenciamentoAlimento getInstance() {
        if(instancia == null) {
            instancia = new GerenciamentoAlimento();
        }
        return instancia;
    }
    
    /** Método que adiciona um Alimento
     * 
     * @param alimento Alimento - novo Alimento cadastrado
     * @throws AlimentoInvalidoException
     * @throws AlimentoJaExisteException 
     */
    public void adicionarAlimento(Alimento alimento) throws AlimentoInvalidoException, AlimentoJaExisteException{        
        if (!alimentoEValido(alimento)){
            throw new AlimentoInvalidoException();
        } else if (existe(alimento.getNome())){
            throw new AlimentoJaExisteException();
        }else {
            repositorio.adicionarAlimento(alimento);
        }
        
    }
    
    /** Método que remove um Alimento
     * 
     * @param nome String - nome do Alimento a ser removido
     * @throws AlimentoNaoExisteException 
     */
    public void removerAlimento(String nome) throws AlimentoNaoExisteException{
        Alimento alimento = procurarAlimento(nome);
        repositorio.removerAlimento(alimento);
    }
    
    /** Método que retorna um Alimento
     * 
     * @param nome String - nome do Alimento a ser procurado
     * @return Alimento - Alimento procurado
     * @throws AlimentoNaoExisteException 
     */
    public Alimento procurarAlimento(String nome) throws AlimentoNaoExisteException {
        if(!existe(nome)) {
            throw new AlimentoNaoExisteException();
        } else {
            return repositorio.procurarAlimento(nome);
        }
    }
    
    /** Método que retorna todos os alimento cadastrados
     * 
     * @return ArraList<Alimento> - todos os alimentos
     */
    public ArrayList<Alimento> buscarCardapio() {
        return repositorio.getAlimentos();
    }
    
    /** Método que verifica se se um Alimento com determinado
     * nome existe
     * 
     * @param nome String - nome a ser verificado
     * @return boolean - indica se existe
     */
    private boolean existe(String nome) {
        if(repositorio.procurarAlimento(nome) != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /** Método que verifica se um determinado Alimento é válido
     * 
     * @param alimento Alimento - alimento a ser verificado
     * @return boolean - indica se é válido
     */
    private boolean alimentoEValido(Alimento alimento) {
        if(alimento.getPreco() <= 0 || alimento.getNome().isEmpty() || alimento.getTipo().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    /** Método que salva as alterações feitas
     * 
     */
    public void salvar() {
        this.repositorio.salvar();
    }
    
    /** Método que carrega os dados salvos
     * 
     * @throws ClassNotFoundException 
     */
    public void carregar() throws ClassNotFoundException {
        this.repositorio.carregar();
    }
}

