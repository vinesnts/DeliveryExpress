/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import java.util.ArrayList;
import negocio.excecoes.pessoa.SenhaPessoaInvalidaException;
import negocio.entidade.Alimento;
import negocio.entidade.AlimentoPedido;
import negocio.entidade.Cliente;
import negocio.entidade.Endereco;
import negocio.entidade.Funcionario;
import negocio.entidade.Pedido;
import negocio.entidade.Pessoa;
import negocio.excecoes.alimento.AlimentoInvalidoException;
import negocio.excecoes.alimento.AlimentoJaExisteException;
import negocio.excecoes.alimento.AlimentoNaoExisteException;
import negocio.excecoes.alimento.PrecoInvalidoException;
import negocio.excecoes.pedido.AlimentoNaoFoiPedidoException;
import negocio.excecoes.pedido.PedidoEntregueException;
import negocio.excecoes.pedido.PedidoNaoExisteException;
import negocio.excecoes.pessoa.CpfPessoaInvalidoException;
import negocio.gerenciamento.GerenciamentoCliente;
import negocio.excecoes.pessoa.EnderecoInvalidoException;
import negocio.excecoes.pessoa.PessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaJaExisteException;
import negocio.excecoes.pessoa.PessoaNaoExisteException;
import negocio.excecoes.pessoa.SenhaPessoaInvalidoException;
import negocio.excecoes.pessoa.SenhasNaoConferemException;
import negocio.gerenciamento.GerenciamentoAlimento;
import negocio.gerenciamento.GerenciamentoFuncionario;
import negocio.gerenciamento.GerenciamentoPedido;

/** Fachada do Projeto
 *
 * @author vinicius
 */
public class Fachada implements IFachadaCliente, IFachadaFuncionario, IFachadaLogin {
    
    private GerenciamentoCliente gerCliente;
    private GerenciamentoFuncionario gerFuncionario;
    private GerenciamentoAlimento gerAlimento;
    private GerenciamentoPedido gerPedido;
    Pessoa pessoa;
    private static Fachada instancia;
    
    /** Construtor para Fachada
     * 
     */
    private Fachada() {
        this.gerCliente = GerenciamentoCliente.getInstance();
        this.gerFuncionario = GerenciamentoFuncionario.getInstance();
        this.gerAlimento = GerenciamentoAlimento.getInstance();
        this.gerPedido = GerenciamentoPedido.getInstance();
    }
    
    /** Método que retorna uma instancia da Fachada
     * 
     * @return Fachada - instancia que sera usada acima
     */
    public static Fachada getInstance() {
        if(instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }
    
    /** Método que retorna uma Pessoa
     * 
     * @return 
     */
    @Override
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    /** Método que colocada o usuário como null
     * 
     */
    @Override
    public void setPessoa() {
        this.pessoa = null;
    }
    
    /** Método para cadastro de um Cliente
     * 
     * @param nome String - nome 
     * @param cpf String - cpf 
     * @param senha String - senha 
     * @param rua String - rua 
     * @param numero String - numero da casa
     * @param bairro String - bairro do usuário
     * @param cidade String - cidade do usuário
     * @throws PessoaJaExisteException
     * @throws PessoaInvalidoException
     * @throws PessoaNaoExisteException
     * @throws EnderecoInvalidoException 
     */
    @Override
    public void cadastrarCliente(String nome, String cpf, String senha, String rua, String numero, String bairro, String cidade) throws PessoaJaExisteException, PessoaInvalidoException, PessoaNaoExisteException, EnderecoInvalidoException {
        Endereco endereco = new Endereco(rua, numero, bairro, cidade);
        Cliente cliente = new Cliente(nome, cpf, senha, endereco);
        this.gerCliente.adicionarCliente(cliente);
        this.gerCliente.salvar();
    }
    
    /** Método para remover um Cliente
     * 
     * @param cpfLogado String - cpf do logado
     * @param cpf String - cpf do Cliente a ser removido
     * @param senha String - senha do Cliente a ser removido
     * @throws PessoaNaoExisteException
     * @throws SenhaPessoaInvalidoException
     * @throws PessoaInvalidoException 
     */
    @Override
    public void removerCliente(String cpfLogado, String cpf, String senha) throws PessoaNaoExisteException, SenhaPessoaInvalidoException, PessoaInvalidoException {
        gerCliente.removerCliente(cpfLogado, cpf, senha);
        this.gerCliente.salvar();
    }
    
    /** Método que retorna um Cliente
     * 
     * @param cpf String - cpf do Cliente a ser procurado
     * @return Cliente - Cliente ou null
     * @throws CpfPessoaInvalidoException
     * @throws PessoaNaoExisteException 
     */
    @Override
    public Cliente buscarCliente(String cpf) throws CpfPessoaInvalidoException, PessoaNaoExisteException {
        Cliente temp = this.gerCliente.procurarCliente(cpf);
        return temp;
    }
    
    /** Método que atualiza os dados de Cliente
     * 
     * @param rua String - rua do Cliente
     * @param numero String - numero da casa do Cliente
     * @param bairro String - bairro
     * @param cidade String - cidade
     * @throws CpfPessoaInvalidoException
     * @throws PessoaNaoExisteException
     * @throws EnderecoInvalidoException 
     */
    @Override
    public void atualizarCliente(String rua, String numero, String bairro, String cidade) throws CpfPessoaInvalidoException, PessoaNaoExisteException, EnderecoInvalidoException {
        Cliente cliente = (Cliente) pessoa;
        Endereco enderecotemp = new Endereco(rua, numero, bairro, cidade);
        if(!gerCliente.enderecoEValido(enderecotemp)) {
            throw new EnderecoInvalidoException();
        }
        cliente.setEndereco(enderecotemp);
        this.gerCliente.salvar();
    }
    
    /** Alteração do nome
     * 
     * @param nome String - o nome a ser colocado como atual
     */
    public void alterarNome(String nome) {
        pessoa.setNome(nome);
        gerCliente.salvar();
        gerFuncionario.salvar();
    }
    
    /** Metodo para alteracao da senha do Cliente
     * 
     * @param senha String - senha atual
     * @param novaSenha String - nova senha
     * @param repetirNovaSenha String - nova senha 
     * @throws CpfPessoaInvalidoException
     * @throws PessoaNaoExisteException
     * @throws SenhasNaoConferemException
     * @throws SenhaPessoaInvalidaException 
     */
    @Override
    public void alterarSenhaCliente(String senha, String novaSenha, String repetirNovaSenha) throws CpfPessoaInvalidoException, PessoaNaoExisteException, SenhasNaoConferemException, SenhaPessoaInvalidaException {
        Cliente cliente = (Cliente) pessoa;
        if(!novaSenha.equals(repetirNovaSenha)) {
            throw new SenhasNaoConferemException();
        } else if(!senha.equals(cliente.getSenha())) {
            throw new SenhaPessoaInvalidaException();
        }
        cliente.setSenha(novaSenha);
        this.gerCliente.salvar();
        
    }
    
    /** Metodo para cadastrar Funcionario
     * 
     * @param nome String - nome do novo Funcionario
     * @param cpf String - cpf do novo Funcionario
     * @param senha String - senha do novo Funcionario
     * @throws PessoaJaExisteException
     * @throws PessoaInvalidoException
     * @throws PessoaNaoExisteException 
     */
    @Override
    public void cadastrarFuncionario(String nome, String cpf, String senha) throws PessoaJaExisteException, PessoaInvalidoException, PessoaNaoExisteException {
        Funcionario funcionario = new Funcionario(nome, cpf, senha);
        gerFuncionario.adicionarFuncionario(funcionario);
        this.gerFuncionario.salvar();
    }
    
    /** Metodo para remover um Funcionario
     * 
     * @param cpf String - cpf do Funcionario
     * @throws PessoaNaoExisteException
     * @throws CpfPessoaInvalidoException 
     */
    @Override
    public void removerFuncionario(String cpf) throws PessoaNaoExisteException, CpfPessoaInvalidoException {
        gerFuncionario.removerFuncionario(cpf);
        this.gerFuncionario.salvar();
    }
    
    /** Metodo que retoran um Funcionario
     * 
     * @param cpf String - cpf do Funcionario
     * @return Funcionario - ou null
     * @throws CpfPessoaInvalidoException
     * @throws PessoaNaoExisteException 
     */
    @Override
    public Funcionario buscarFuncionario(String cpf) throws CpfPessoaInvalidoException, PessoaNaoExisteException {
        Funcionario temp = this.gerFuncionario.procurarFuncionario(cpf);
        return temp;
    }
    
    /** Metodo para atualizar de um Funcionario
     * 
     * @param cpf String - cpf do Funcionario
     * @param nome String - nome do Funcionario
     * @throws CpfPessoaInvalidoException
     * @throws PessoaNaoExisteException 
     */
    @Override
    public void atualizarFuncionario(String cpf, String nome) throws CpfPessoaInvalidoException, PessoaNaoExisteException {
        Funcionario temp = this.gerFuncionario.procurarFuncionario(cpf);
        temp.setNome(nome);
        this.gerFuncionario.salvar();
    }
    
    /** Metodo para cadastra um Alimento
     * 
     * @param nome String - nome do Alimento
     * @param preco String - preço do Alimento
     * @param tipo String - se é bebida ou não
     * @throws AlimentoInvalidoException
     * @throws AlimentoJaExisteException
     * @throws PrecoInvalidoException 
     */
    @Override
    public void cadastrarAlimento(String nome, String preco, String tipo) throws AlimentoInvalidoException, AlimentoJaExisteException, PrecoInvalidoException {
        double dPreco = Double.parseDouble(preco);
        Alimento alimento = new Alimento(nome, dPreco, tipo);
        gerAlimento.adicionarAlimento(alimento);
        gerAlimento.salvar();
    }
    /** Metodo para remover um Alimento 
     * 
     * @param nome String - nome do Alimento
     * @throws AlimentoNaoExisteException 
     */
    @Override
    public void removerAlimento(String nome) throws AlimentoNaoExisteException {
        gerAlimento.removerAlimento(nome);
        gerAlimento.salvar();
    }
    
    /** Metodo de busca para Alimento
     * 
     * @param nome String - nome do Alimento
     * @return Alimento - Alimento procurado
     * @throws AlimentoNaoExisteException 
     */
    @Override
    public Alimento buscarAlimento(String nome) throws AlimentoNaoExisteException {
        Alimento temp = this.gerAlimento.procurarAlimento(nome);
        return temp;
    }
    
    /** Metodo que retorna a lista de Alimento pedidos por um Cliente
     * 
     * @param idPedido int - retorna um pedido
     * @return ArrayList<AlimentoPedido> - lista de AlimentoPedido
     * @throws PedidoNaoExisteException 
     */
    @Override
    public ArrayList<AlimentoPedido> buscarAlimentoPedido(int idPedido) throws PedidoNaoExisteException {
        return gerPedido.procurarPedido(idPedido).getArrayAlimento();
    }
    
    /** Metodo que atualiza um Alimento
     * 
     * @param nome String - nome atual
     * @param novoNome String - novo nome
     * @param preco double - preço novo preço
     * @throws AlimentoNaoExisteException
     * @throws PrecoInvalidoException
     * @throws AlimentoInvalidoException 
     */
    @Override
    public void atualizarAlimento(String nome, String novoNome, double preco) throws AlimentoNaoExisteException, PrecoInvalidoException , AlimentoInvalidoException {
        if(preco <= 0) {
            throw new PrecoInvalidoException();
        }
        if(novoNome.isEmpty()) {
            throw new AlimentoInvalidoException();
        }
        Alimento temp = this.gerAlimento.procurarAlimento(nome);
        temp.setNome(novoNome);
        temp.setPreco(preco);
        gerAlimento.salvar();
    }
    
    /** Metodo para adicionar um Pedido de um Cliente
     * 
     * @return Pedido - o pedido feito
     */
    @Override
    public Pedido adicionarPedido() {
        Cliente cliente = (Cliente) pessoa;
        Pedido pedido =  new Pedido(cliente);
        gerPedido.adicionarPedido(pedido);
        gerPedido.salvar();
        return pedido;
    }
    
    /** Metodo que remove um Pedido
     * 
     * @param id int - a id do Pedido
     * @throws PedidoNaoExisteException
     * @throws PedidoEntregueException 
     */
    @Override
    public void removerPedido(int id) throws PedidoNaoExisteException, PedidoEntregueException {
        gerPedido.removerPedido(buscarPedido(id));
        gerPedido.salvar();
    }
    
    /** Metodo que retorna um Pedido
     * 
     * @param id int - id do Pedido a ser procurado
     * @return Pedido
     * @throws PedidoNaoExisteException 
     */
    @Override
    public Pedido buscarPedido(int id) throws PedidoNaoExisteException {
        return gerPedido.procurarPedido(id);
    }
    
    /** Metodo todos os pedidos feitos por um Cliente
     * 
     * @return ArrayList<Pedido> - os pedidos de um Cliente
     */
    @Override
    public ArrayList<Pedido> buscarPedidos() {
        ArrayList<Pedido> arrayPedidoCliente = new ArrayList<>();
        ArrayList<Pedido> arrayPedidos = gerPedido.buscarPedidos();
        for(int i = 0; i < arrayPedidos.size(); i++) {
            if(arrayPedidos.get(i).getCliente().getCpf().equals(pessoa.getCpf()) && arrayPedidos.get(i).getEstado().equals("Não entregue")) {
                arrayPedidoCliente.add(arrayPedidos.get(i));
            } 
        }
        return arrayPedidoCliente;
        
    }
    
    /** Metodo que retorna os pedidos de um Cliente por meio de Funcionario
     * 
     * @return ArrayList<Pedido> - os pedidos de um Cliente
     */
    @Override
    public ArrayList<Pedido> buscarPedidosFuncionario() {
        ArrayList<Pedido> arrayPedidoCliente = new ArrayList<>();
        ArrayList<Pedido> arrayPedidos = gerPedido.buscarPedidos();
        for(int i = 0; i < arrayPedidos.size(); i++) {
            if(arrayPedidos.get(i).getEstado().equals("Não entregue")) {
                arrayPedidoCliente.add(arrayPedidos.get(i));
            } 
        }
        return arrayPedidoCliente;
        
    }
    
    /** Metodo que adicionar um Alimento pedido por um Cliente
     * 
     * @param pedido Pedido - Pedido onde sera adicionado
     * @param nomeAlimento String - nome do Alimento 
     * @param quantidade int - quantidade do Alimento
     * @throws AlimentoNaoExisteException
     * @throws PedidoEntregueException 
     */
    @Override
    public void adicionarAlimentoPedido(Pedido pedido, String nomeAlimento, int quantidade) throws AlimentoNaoExisteException, PedidoEntregueException {
        Alimento alimento = buscarAlimento(nomeAlimento);
        
        AlimentoPedido alimentoPedido = new AlimentoPedido(alimento, quantidade);
        gerPedido.adicionarAlimentoPedido(pedido, alimentoPedido);
        gerPedido.salvar();
    }
    
    /** Metodo que remover um Alimento do Pedido de um Cliente
     * 
     * @param pedido Pedido - de onde sera removido
     * @param nomeAlimento String - nome do Alimento que sera removido
     * @throws AlimentoNaoFoiPedidoException
     * @throws AlimentoNaoExisteException 
     */
    @Override
    public void removerAlimentoPedido(Pedido pedido, String nomeAlimento) throws AlimentoNaoFoiPedidoException, AlimentoNaoExisteException {
        Alimento alimento = buscarAlimento(nomeAlimento);
        gerPedido.removerAlimentoPedido(pedido, nomeAlimento);
        gerPedido.salvar();
    }
    
    /** Metodo que muda a quantidade de um Alimento que foi pedido
     * 
     * @param pedido Pedido - no qual sera mudado o Alimento
     * @param nomeAlimento String - nome do Alimento
     * @param quantidade int - quantidade do Alimento
     * @throws AlimentoNaoFoiPedidoException 
     */
    @Override
    public void editarQuantidade(Pedido pedido, String nomeAlimento, int quantidade) throws AlimentoNaoFoiPedidoException {
        gerPedido.editarQuantidade(pedido, nomeAlimento, quantidade);
         gerPedido.salvar();
    }
    
    /** Metodo para logar um Cliente
     * 
     * @param cpf String - cpf do Cliente
     * @param senha String - senha do Cliente
     * @throws CpfPessoaInvalidoException
     * @throws SenhaPessoaInvalidaException
     * @throws PessoaNaoExisteException 
     */
    @Override
    public void logarCliente(String cpf, String senha) throws CpfPessoaInvalidoException, SenhaPessoaInvalidaException, PessoaNaoExisteException {
        Cliente cliente = gerCliente.procurarCliente(cpf);
        if (!cliente.getSenha().equals(senha)){
            throw new SenhaPessoaInvalidaException();
        }
        pessoa = cliente;
    }
    
    /** Metodo para logar um Funcionario
     * 
     * @param cpf String - cpf do Funcionario
     * @param senha String - senha do Funcionario
     * @throws CpfPessoaInvalidoException
     * @throws SenhaPessoaInvalidaException
     * @throws PessoaNaoExisteException 
     */
    @Override
    public void logarFuncionario(String cpf, String senha) throws CpfPessoaInvalidoException, SenhaPessoaInvalidaException, PessoaNaoExisteException {
        Funcionario funcionario = gerFuncionario.procurarFuncionario(cpf);
        if (!funcionario.getSenha().equals(senha)){
            throw new SenhaPessoaInvalidaException();
        }
        pessoa = funcionario;
    }

    /** Metodo que retorna todos os alimentos cadastrados
     * 
     * @return ArrayList<Alimento> - array de Alimento
     */
    @Override
    public ArrayList<Alimento> buscarCardapio() {
        return gerAlimento.buscarCardapio();
    }
}
