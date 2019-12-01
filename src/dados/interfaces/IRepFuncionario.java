
package dados.interfaces;
import negocio.entidade.Funcionario;

/** Interface para RepFuncionario
 * 
 * @author Edvaldo
 */
public interface IRepFuncionario {
    void adicionarFuncionario(Funcionario funcionario);
    void removerFuncionario(Funcionario funcionario);
    Funcionario procurarFuncionario(String cpf);
    public void carregar();
    public void salvar();
}
