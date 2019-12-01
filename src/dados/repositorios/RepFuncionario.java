
package dados.repositorios;

import java.util.ArrayList;
import negocio.entidade.Funcionario;
import dados.interfaces.IRepFuncionario;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Classe para instaciar objetos do tipo RepFuncionario
 * 
 * @author Edvaldo
 */
public class RepFuncionario implements IRepFuncionario{
    
    private ArrayList <Funcionario> arrayFuncionario;
    private FileOutputStream arquivoParaGravar;
    private ObjectOutputStream escritor;
    private FileInputStream recuperadorArquivos;
    private ObjectInputStream leitor;
    
    /** Construtor para RepFuncionario cria um ArrayList para Funcionario e 
     * adiciona o administrador
     * 
     */
    public RepFuncionario(){
        this.arrayFuncionario = new ArrayList <>();
        arrayFuncionario.add(new Funcionario("administrador", "administrad", "admin"));
    } 
    
    /** Metodo que adiciona um Funcionario ao repositório
     * 
     * @param funcionario Funcionario -  o funcionario a ser adicionado
     */
    @Override
    public void adicionarFuncionario(Funcionario funcionario){
        this.arrayFuncionario.add(funcionario);
        
    }
    
    /** Método que remove um funcionario
     * 
     * @param funcionario Funcionario -  funcionário a ser removido
     */    
    @Override
    public void removerFuncionario(Funcionario funcionario) {
        this.arrayFuncionario.remove(funcionario);
   
    }

    /** Método que retorna um Funcionario 
     * 
     * @param cpf String - Cpf do Funcionário
     * @return Retorna true se for encontrado e false caso contrario
     */    
    @Override
    public Funcionario procurarFuncionario(String cpf){
        if(arrayFuncionario.isEmpty()) return null;
        int indice;
        for (indice=0;indice < this.arrayFuncionario.size();indice++){
            if (this.arrayFuncionario.get(indice).getCpf().equals(cpf)){
                return this.arrayFuncionario.get(indice);
            }
        }
        return null;
    }   
    
    /** Método que escreve no arquivo funcionario.dat
     * as informações armazenadas em arrayFuncionario
     */
    @Override
    public void salvar() {

        try {
            this.arquivoParaGravar = new FileOutputStream("dados//funcionario.dat");
            this.escritor = new ObjectOutputStream(arquivoParaGravar);
            this.escritor.writeObject(arrayFuncionario);
            this.escritor.flush();
            this.escritor.close();
            this.arquivoParaGravar.flush();
            this.arquivoParaGravar.close();

        } catch (IOException erro) {
            erro.printStackTrace();
        }

    }
    
    /** Método que ler os dados de funcionario.dat e os 
     * coloca em arrayFuncionario
     * 
     * @throws ClassNotFoundException 
     */
    @Override
    public void carregar() {
        try {
            recuperadorArquivos = new FileInputStream("dados//funcionario.dat");
            this.leitor = new ObjectInputStream(recuperadorArquivos);
            arrayFuncionario = (ArrayList<Funcionario>) leitor.readObject();
            this.leitor.close();
            recuperadorArquivos.close();

        } catch (IOException erro) {
            System.out.println("IOExceptionFuncionario");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        }
    }
}
