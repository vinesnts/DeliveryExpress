
package dados.interfaces;

import java.util.ArrayList;
import negocio.entidade.Alimento;

/** Interface para RepAlimento
 * 
 * @author Edvaldo
 */
public interface IRepAlimento {
    void adicionarAlimento(Alimento alimento);
    void removerAlimento(Alimento alimento);
    Alimento procurarAlimento(String nome);
    ArrayList<Alimento> getAlimentos();
    void carregar();
    void salvar();
}
