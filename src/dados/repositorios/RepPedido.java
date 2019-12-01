
package dados.repositorios;

import dados.interfaces.IRepPedido;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.entidade.Pedido;

/** Classe para instaciar objetos do tipo RepPedido
 * 
 * @author Vinícius
 */
public class RepPedido implements IRepPedido {
    private ArrayList <Pedido> arrayPedido;
    private FileOutputStream arquivoParaGravar;
    private ObjectOutputStream escritor;
    private FileInputStream recuperadorArquivos;
    private ObjectInputStream leitor;
    
    /** Construtor de RepCliente que cria um ArrayLista para Pedido
     * 
     */
    public RepPedido() {
        arrayPedido = new ArrayList<>();
    }
    
    /** Método que adiciona um Pedido a arrayPedido
     * 
     * @param pedido Pedido - pedido a ser adiciona a arrayPedido
     */
    @Override
    public void adicionarPedido(Pedido pedido) {
        arrayPedido.add(pedido);
    }
    
    /** Método que remove um Pedido de arrayPedido
     * 
     * @param pedido Pedido - pedido a ser removido de arrayPedido
     */
    @Override
    public void removerPedido(Pedido pedido) {
        arrayPedido.remove(pedido);
    }
    
    /** Método que retorna um Pedido, 
     * sendo fornecido sua id
     * 
     * @param id int - id do Pedido procurado
     * @return Pedido - retorna o Pedido se encontrado, caso contrário null
     */
    @Override
    public Pedido procurarPedido(int id) {
        if(arrayPedido.isEmpty()) return null;
        int i;
        Pedido pedido = null;
        for(i = 0; i < arrayPedido.size(); i++) {
            if(arrayPedido.get(i).getId() == id) {
                pedido = arrayPedido.get(i);
                break;
            }
        }
        return pedido;
    }
    
    /** Método que retorna o arrayPedido
     * 
     * @return ArraList<Pedido> - o array com todos os pedidos 
     */
    public ArrayList<Pedido> getPedidos() {
        return this.arrayPedido;
    }
    
    /** Método que retorna o id do último Pedido
     * 
     * @param pedidos ArrayLista<Pedido> - o array com os pedidos
     * @return int - a id procurada
     */
    public int procurarUltimoId(ArrayList<Pedido> pedidos) {
        int maior = pedidos.get(0).getId();
        for(int i = 0; i < pedidos.size(); i++) {
            if(maior < pedidos.get(i).getId()) {
                maior = pedidos.get(i).getId();
            }
        }
        return maior;
    }
    
    
    
    /** Método que escreve no arquivo pedido.dat todos os dados
     * de arrayPedido
     * 
     */   
    @Override
     public void salvar() {

        try {
            this.arquivoParaGravar = new FileOutputStream("dados//pedido.dat");
            this.escritor = new ObjectOutputStream(arquivoParaGravar);
            this.escritor.writeObject(arrayPedido);
            this.escritor.flush();
            this.escritor.close();
            this.arquivoParaGravar.flush();
            this.arquivoParaGravar.close();

        } catch (IOException erro) {
            erro.printStackTrace();
        }

    }

    /** Método que ler os dados de pedido.dat e
     * os escreve em arrayPedido
     * 
     * @throws ClassNotFoundException 
     */
    @Override
    public void carregar() {

        try {
            recuperadorArquivos = new FileInputStream("dados//pedido.dat");
            this.leitor = new ObjectInputStream(recuperadorArquivos);
            arrayPedido = (ArrayList<Pedido>) leitor.readObject();
            this.leitor.close();
            recuperadorArquivos.close();

        } catch (IOException erro) {
            System.out.println("IOExceptionPedido");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        }
    }
}
