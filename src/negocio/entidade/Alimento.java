package negocio.entidade;

import java.io.Serializable;

/** Classe para instaciar objetos do tipo Alimento,
 * com metodos e atributos 
 * 
 * @author Edvaldo
 */
public class Alimento implements Serializable {
    
    private String nome;
    private double preco;
    private String tipo;
    
    /** Construtor para Alimento com 3 parâmetros
     * 
     * @param nome String - nome do Alimento
     * @param preco double - preço do Alimento
     * @param tipo String - se é bebida ou comida
     */
    public Alimento(String nome, double preco, String tipo) {
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
    }
    
    /** Método para retorno do nome do Alimento
     * 
     * @return String - número do Alimento
     */
    public String getNome() {
        return this.nome;
    }
    
    /** Método para modificar o nome do Alimento
     * 
     * @param nome String - novo nome do Alimento
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /** Método para retorno do preço do Alimento
     * 
     * @return double - Preço do Alimento
     */
    public double getPreco() {
        return this.preco;
    }
    
    /** Método para modificar o preço  do Alimento
     * 
     * @param preco double - Novo  preço do Alimento 
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    /** Método para retornar o tipo do Alimento
     * 
     * @return String - Tipo do Alimento 
     */
    public String getTipo() {
        return this.tipo;
    }
    
    /** Método para cadastrar um novo Alimento
     * 
     * @param nome String - nome do novo Alimento
     * @param preco double - preço do novo Alimento
     * @param tipo String - tipo do novo Alimento
     */
    void cadastrarAlimentos(String nome, double preco, String tipo) {
        Alimento novoAlimento = new Alimento(nome, preco, tipo);
        
    }

    /** Método para retorno dos atributos do Alimento em 
     * uma única String
     * 
     * @return String - Atributos 
     */
    @Override
    public String toString() {
        return "Alimento: " + nome + " | Preço: " + preco + " | Tipo: " + tipo;
    }
    
    

}
