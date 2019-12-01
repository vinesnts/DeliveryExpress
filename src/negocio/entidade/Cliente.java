package negocio.entidade;

import java.io.Serializable;
import java.util.Objects;

/** Classe para instaciar objetos do tipo Cliente
 * com métodos e atributos
 * 
 * @author Vinicius
 */
public class Cliente extends Pessoa implements Serializable {
	
        private Endereco endereco;
	
        /** Construtor para Cliente com 4 parâmetros,
         * sendo 3 para o construtor da superclasse Pessoa
         * 
         * @param nome String - nome do cliente
         * @param cpf String - Cpf do cliente
         * @param senha String - senha do cliente
         * @param endereco Endereço - endereço do cliente não é atributo de Pessoa
         */
	public Cliente(String nome, String cpf, String senha, Endereco endereco){
            super(nome, cpf, senha);
            this.endereco = endereco;
	}
        
        
    /** Método para retorno do Endereço do Cliente
     * 
     * @return String - Endereço do Cliente
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /** Método para modificar o Endereço do Cliente 
     * 
     * @param endereco Endereço - endereço do cliente
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }  
    
    /** Método que retorna os atributos de Cliente em forma de String 
     * 
     * @return String - atributos em uma String
     */
    @Override
    public String toString() {
        return getNome() + ";" + getCpf() + ";" + getSenha() + ";" + getEndereco();
    }
        
}