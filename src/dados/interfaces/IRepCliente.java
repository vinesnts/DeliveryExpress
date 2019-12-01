
package dados.interfaces;

import negocio.entidade.Cliente;

/** Interface para RepCliente
 * 
 * @author Vinícius
 */
public interface IRepCliente {
    void adicionarCliente(Cliente cliente);
    void removerCliente(Cliente cliente);
    Cliente procurarCliente(String cpf);
    void salvar();
    void carregar();
}
