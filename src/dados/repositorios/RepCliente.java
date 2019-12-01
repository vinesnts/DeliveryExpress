package dados.repositorios;

import dados.interfaces.IRepCliente;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.entidade.Cliente;

/** Classe para instaciar objetos do tipo RepCliente
 * 
 * @author Vinícius
 */
public class RepCliente implements IRepCliente {
    private ArrayList <Cliente> arrayCliente;
    private FileOutputStream arquivoParaGravar;
    private ObjectOutputStream escritor;
    private FileInputStream recuperadorArquivos;
    private ObjectInputStream leitor;
    
    /** Construtor de RepCliente
     * 
     */
    public RepCliente() {
        arrayCliente = new ArrayList();
    }
    
    /**
     * Recebe um cliente como parametro
     * Adiciona clientes ao repositorio
     * @param cliente Cliente - recebe o cliente a ser adicionado
     */
    @Override
    public void adicionarCliente(Cliente cliente) {
        arrayCliente.add(cliente);
    }
    
    /**
     * Remove o cliente passando o CPF como parametro
     * @param cliente Cliente - objeto cliente para ser removido
     */
    @Override
    public void removerCliente(Cliente cliente) {
            arrayCliente.remove(cliente);
    }
    
    /** Método que retorna um Cliente do repositório,
     * sendo fornecido o cpf
     * 
     * @param cpf String - CPF do Cliente
     * @return Cliente - o Cliente do cpf procurado ou null caso não exista
     */
    @Override
    public Cliente procurarCliente(String cpf) {
        int i;
        if(arrayCliente.isEmpty()) return null;
        Cliente cliente = null;
        for(i = 0; i < arrayCliente.size(); i++) {
            if(arrayCliente.get(i).getCpf().equals(cpf)) {
                cliente = arrayCliente.get(i);
                break;
            }
        }
        return cliente;
    }
    
    /** Método que escreve no arquivo cliente.dat todas as informações
     * de RepCliente
     */
    @Override
    public void salvar() {
        try {
            this.arquivoParaGravar = new FileOutputStream("dados//cliente.dat");
            this.escritor = new ObjectOutputStream(arquivoParaGravar);
            this.escritor.writeObject(arrayCliente);
            this.escritor.flush();
            this.escritor.close();
            this.arquivoParaGravar.flush();
            this.arquivoParaGravar.close();

        } catch (IOException erro) {
            erro.printStackTrace();
        }

    }
    
    /** Método que ler os dados do arquivo cliente.dat e os
     * coloca em arrayCliente
     * 
     * @throws ClassNotFoundException 
     */
    @Override
    public void carregar() {
        try {
            recuperadorArquivos = new FileInputStream("dados//cliente.dat");
            this.leitor = new ObjectInputStream(recuperadorArquivos);
            arrayCliente = (ArrayList<Cliente>) leitor.readObject();
            this.leitor.close();
            recuperadorArquivos.close();

        } catch (IOException erro) {
            System.out.println("IOExceptionCliente");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException");
        }
    }
}
