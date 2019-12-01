package negocio.entidade;

/** Classe para instaciar objetos do tipo Funcionario,
 * com métodos e atributos
 * 
 * @author Edvaldo Heliodorio
 */
public class Funcionario extends Pessoa{
    
    /** Construtor para Funcionario com 3 parâmetros para 
     * construtor da supeclasse
     * 
     * @param nome String - nome do Funcionario
     * @param cpf String - Cpf do Funcionario
     * @param senha String - senha do Funcionario
     */
    public Funcionario(String nome, String cpf, String senha){
        super(nome, cpf, senha);
    }
    
    
    /** Método para retorno dos atributos do Funcionario em 
     * uma única String
     * 
     * @return String - Atributos em texto
     */
    @Override
    public String toString() {
        return getNome() + ";" + getCpf() + ";" + getSenha();
    }
}
