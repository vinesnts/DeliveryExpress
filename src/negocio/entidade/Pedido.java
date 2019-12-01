package negocio.entidade;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Vinicius Santos
 */
public class Pedido implements Serializable {
    
    private static int contador = 1;
    private int id;
    private Cliente cliente;
    private String estadoDeEntrega;
    private ArrayList <AlimentoPedido> arrayAlimentoPedido;
    private double precoTotal;


    
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.arrayAlimentoPedido = new ArrayList<>();
        this.estadoDeEntrega = "Não entregue";
        this.id = contador;
        this.contador++;
    }
    
    public static void setContador(int contador) {
        Pedido.contador = contador;
    }
    

    public String getEstado() {
        return this.estadoDeEntrega;
    }
        
    public void setEstado(String estado) {
        this.estadoDeEntrega = estado;
    }
    
    
    public ArrayList<AlimentoPedido> getArrayAlimento() {
        return arrayAlimentoPedido;
    }
    
    public void adicionarAlimentoPedido(AlimentoPedido alimentoPedido) {
        this.arrayAlimentoPedido.add(alimentoPedido);
        setPrecoTotal();
    }
    
    public void removerAlimentoPedido(AlimentoPedido alimentoPedido) { //Remover alimento de AlimentoPedido
        this.arrayAlimentoPedido.remove(alimentoPedido);
        setPrecoTotal();
    }
    
    public AlimentoPedido procurarAlimentoPedido(String nomeAlimento) {
        AlimentoPedido alimentoPedido = null;
        for(int i = 0; i < arrayAlimentoPedido.size(); i++) {
            if(nomeAlimento == arrayAlimentoPedido.get(i).getAlimento().getNome()) {
                alimentoPedido = arrayAlimentoPedido.get(i);
            }
        }
        return alimentoPedido;
    }
    
    public double getPrecoTotal() {
        return precoTotal;
    }
    
    public void setPrecoTotal() {
        precoTotal = 0.0;
        
        for(int i = 0; i < arrayAlimentoPedido.size(); i++) {
            precoTotal += arrayAlimentoPedido.get(i).getSubTotal();
        }
    }  

    public Cliente getCliente() {
        return cliente;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | Cliente: " + cliente.getNome() + " | Status: " + estadoDeEntrega + " | Preço: " + precoTotal;
    }
    
    

    public int getId() {
        return id;
    }
    
}
