
package dados.repositorios;
import dados.interfaces.IRepAlimento;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import negocio.entidade.Alimento;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Classe para instaciar um objeto do tipo RepAlimento,
 * um repositótio para Alimento
 * 
 * @author Edvaldo
 */
public class RepAlimento implements IRepAlimento{
    private ArrayList <Alimento> arrayAlimento;
    private FileOutputStream arquivoParaGravar;
    private ObjectOutputStream escritor;
    private FileInputStream recuperadorArquivos;
    private ObjectInputStream leitor;
    
    /** Construtor para RepAlimento,
     * que cria um ArrayLista para objetos de Alimento
     */
    public RepAlimento(){
        this.arrayAlimento = new ArrayList <>();
    }
    
    /** Método que adciona um funcionario ao repositorio
     * 
     * @param alimento Alimento - o funcionario a ser adicionado
     */
    
    @Override
    public void adicionarAlimento(Alimento alimento){
        this.arrayAlimento.add(alimento);        
    }
    
    /** Método que remove um alimento do repositório de Alimento
     * 
     * @param alimento Alimento - Alimento a ser removido
     */
    @Override
    public void removerAlimento(Alimento alimento){                              
        this.arrayAlimento.remove(alimento); 
    }
    
    /** Metodo que retorna um Alimento
     * 
     * @param nome String - nome do Alimento a ser procurado
     * @return Alimento - o alimento procurado, null caso contrario
     */    
    @Override
    public Alimento procurarAlimento(String nome){
        if(arrayAlimento.isEmpty()) return null;
        int indice;
        for (indice=0;indice < this.arrayAlimento.size();indice++){
            if (this.arrayAlimento.get(indice).getNome().equals(nome)){
                return this.arrayAlimento.get(indice);
            }
        }
        return null;
    }
    
    /** Método para escrever no arquivo alimento.dat todas as informações 
     * de RepAlimento
     * 
     */
    @Override
    public void salvar() {
        try {
            this.arquivoParaGravar = new FileOutputStream("dados//alimento.dat");
            this.escritor = new ObjectOutputStream(arquivoParaGravar);
            this.escritor.writeObject(arrayAlimento);
            this.escritor.flush();
            this.escritor.close();
            this.arquivoParaGravar.flush();
            this.arquivoParaGravar.close();

        } catch (IOException erro) {
            erro.printStackTrace();
        }

    }
    
    /** Método que ler todas as informações e alimento.dat e
     * as coloca em RepAlimento
     * 
     * @throws ClassNotFoundException 
     */
    @Override
    public void carregar() {
        try {
            recuperadorArquivos = new FileInputStream("dados//alimento.dat");
            this.leitor = new ObjectInputStream(recuperadorArquivos);
            arrayAlimento = (ArrayList<Alimento>) leitor.readObject();
            this.leitor.close();
            recuperadorArquivos.close();

        } catch (IOException erro) {
            System.out.println("IOExceptionAlimento");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        }
    }

    /** Método que retorna o arrayAlimento
     * 
     * @return ArrayList<Alimento> - todos os Alimentos armazenados
     */
    @Override
    public ArrayList<Alimento> getAlimentos() {
        return this.arrayAlimento;
    }
    
    
    
}
