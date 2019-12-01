
package gui.exception;

import javafx.scene.control.Alert;


public class GUIException {

    public GUIException() {
        
    }
    
    public static void erroInesperado() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Erro Inesperado!!");
        dialogoErro.setContentText("Clique OK e tente novamente");
        dialogoErro.showAndWait();
    }
    
    public static void pessoaJaExiste() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu um Erro");
        dialogoErro.setHeaderText("Já existe uma conta com esse CPF");
        dialogoErro.setContentText("Fale com o gerente");
        dialogoErro.showAndWait();
    }
    
    public static void pessoaNaoExiste() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("CPF ou senha inválida");
        dialogoErro.setContentText("Verifique os dados digitados ou cadastrar-se");
        dialogoErro.showAndWait();
    }
    
    public static void enderecoInvalido() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu um Erro");
        dialogoErro.setHeaderText("O Endereço digitado é inválido");
        dialogoErro.setContentText("Revise os campos");
        dialogoErro.showAndWait();
    }
    
    public static void pessoaInvalida() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu um Erro");
        dialogoErro.setHeaderText("Nome, Cpf ou Senha é invalido");
        dialogoErro.setContentText("Preencha novamente");
        dialogoErro.showAndWait();
    }
    
    public static void nomeInvalido() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu um Erro");
        dialogoErro.setHeaderText("Nome invalido");
        dialogoErro.setContentText("Preencha novamente");
        dialogoErro.showAndWait();
    }
    
    public static void cpfInvalido() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("CPF inválido");
        dialogoErro.setContentText("Clique OK e digite novamente");
        dialogoErro.showAndWait();
    }
    
    public static void senhaInvalida() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Senha inválida");
        dialogoErro.setContentText("Clique OK e digite novamente");
        dialogoErro.showAndWait();
    }
    
    public static void senhasNaoConferem() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Senhas não conferem");
        dialogoErro.setContentText("Clique OK e digite novamente");
        dialogoErro.showAndWait();
    }
    
    public static void alimentoInvalido() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Alimento digitado inválido");
        dialogoErro.setContentText("Clique OK e digite novamente");
        dialogoErro.showAndWait();
    }
    
    public static void alimentoJaExiste() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Alimento já está no cardápio");
        dialogoErro.setContentText("Clique OK e digite novamente");
        dialogoErro.showAndWait();
    }
    
    public static void precoInvalido() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Campo de preço invalido");
        dialogoErro.setContentText("Digite apenas números e pontos, não deixe vazio");
        dialogoErro.showAndWait();
    }
    
    public static void alimentoNaoExiste() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("O alimento não existe");
        dialogoErro.setContentText("");
        dialogoErro.showAndWait();
    }
    
    public static void pedidoJaFoiEntregue() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Pedido já foi entregue");
        dialogoErro.setContentText("Pedidos entregues não podem ser alterados");
        dialogoErro.showAndWait();
    }
    
    public static void quantidadeInvalida() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Quantidade de itens inválida");
        dialogoErro.setContentText("Digite apenas números");
        dialogoErro.showAndWait();
    }
    
    public static void pedidoNaoExiste() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Pedido não existe");
        dialogoErro.setContentText("O pedido a ser removido não existe");
        dialogoErro.showAndWait();
    }
    
    public static void selecioneAlgumItem() {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setTitle("Aconteceu Um Erro");
        dialogoErro.setHeaderText("Nenhum item selecionado");
        dialogoErro.setContentText("Selecione algum item e tente novamente");
        dialogoErro.showAndWait();
    }
}
