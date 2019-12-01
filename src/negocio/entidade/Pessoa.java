package negocio.entidade;

import java.io.Serializable;

/** Método para instaciar objetos do tipo Pessoa
 * 
 * @author Vinícius
 */
public class Pessoa implements Serializable {
	
	private String nome, cpf, senha;
	
        /** Construtor para Pessoa com 3 parâmetros
         * 
         * @param nome String - nome da Pessoa
         * @param cpf String - cpf da Pessoa
         * @param senha String - senha para acesso a conta, de Pessoa
         */
	public Pessoa(String nome, String cpf, String senha){
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}
        
        /** Metodo que retorna o CPF do cliente
         * 
         * @return String - o CPF do Cliente
         */
        public String getCpf() {
            return this.cpf;
        }
        
        /** Metodo que retorna o nome do Cliente
         * 
         * @return String - o nome do Cliente
         */
	public String getNome() {
            return this.nome;
        }
        
        /** Metodo que permite a alteracao do nome do Cliente
         * 
         * @param nome String - passada para alterar o nome do Cliente
         */
        public void setNome(String nome) {
            this.nome = nome;
        }
        
        /** Metodo que permite a visualizacao de senhas
         * 
         * @return String - a senha do Cliente
         */
        public String getSenha() {
            return this.senha;
        }
        
        /** Método que permite a alteracao de senhas
         * 
         * 
         * @param novaSenha String - a nova senha do Cliente
         */
        public void setSenha(String novaSenha) {
            this.senha = novaSenha;
        }       
        
}