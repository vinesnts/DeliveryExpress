/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import fachadas.Fachada;
import fachadas.IFachadaCliente;
import gui.exception.GUIException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import negocio.entidade.Cliente;
import negocio.entidade.Pedido;
import negocio.excecoes.pedido.PedidoEntregueException;
import negocio.excecoes.pedido.PedidoNaoExisteException;
import negocio.excecoes.pessoa.CpfPessoaInvalidoException;
import negocio.excecoes.pessoa.EnderecoInvalidoException;
import negocio.excecoes.pessoa.PessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaNaoExisteException;
import negocio.excecoes.pessoa.SenhaPessoaInvalidaException;
import negocio.excecoes.pessoa.SenhasNaoConferemException;

/**
 * FXML Controller class
 *
 * @author vinicius9812
 */
public class TelaClienteController implements Initializable {

    private IFachadaCliente fachada;
    private List listaPedidos = new ArrayList<>();
    private ObservableList<Pedido> listaObservavel;

    private TextField pesquisarPedidoText;
    private Label statusPedidoLabel;
    @FXML
    private Pane clientePane;
    @FXML
    private Pane clienteAnchorPane;
    @FXML
    private Pane atualizaEnderecoPane;
    @FXML
    private TextField ruaText;
    @FXML
    private TextField numeroText;
    @FXML
    private TextField bairroText;
    @FXML
    private TextField cidadeText;
    @FXML
    private Pane alterarSenhaPane;
    @FXML
    private PasswordField senhaAtualText;
    @FXML
    private PasswordField novaSenhaText;
    @FXML
    private PasswordField repitaNovaSenhaText;
    @FXML
    private Button sairApagarContaButton;
    @FXML
    private PasswordField senhaApagarContaText;
    @FXML
    private TextField CpfApagarContaButton;
    @FXML
    private Button confirmarApagarContaButton;
    @FXML
    private Pane apagarContaPane;
    @FXML
    private Button sairAlterarSenhaButton;
    @FXML
    private Button confirmarAlterarSenhaButton;
    @FXML
    private Button sairClienteButton;
    @FXML
    private MenuItem charmAlterarEnderecoButton;
    @FXML
    private MenuItem chamarAlterarSenhaButton;
    @FXML
    private MenuItem chamarApagarContaButton;
    @FXML
    private Button novoPedidoButton;
    @FXML
    private Button cancelarPedidoButton;
    @FXML
    private Button sairAlterarEnderecoButton;
    @FXML
    private Button atualizarEnderecoButton;
    @FXML
    private TextField pessoaLogada;
    @FXML
    private Button mudarNomeButton;
    @FXML
    private ListView<Pedido> pedidosList;

    public TelaClienteController() {
        this.fachada = Fachada.getInstance();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pessoaLogada.setText(fachada.getPessoa().getNome());
        listarPedidos();
        pedidosList.setItems(listaObservavel);
    }

    private void listarPedidos() {

        ArrayList<Pedido> pedidos = fachada.buscarPedidos();
        for (int i = 0; i < pedidos.size(); i++) {
            listaPedidos.add(pedidos.get(i));
        }
        listaObservavel = FXCollections.observableArrayList(listaPedidos);

    }

    @FXML
    private void chamarAlterarEnderecoAction(ActionEvent event) {
        clientePane.setVisible(false);
        atualizaEnderecoPane.setVisible(true);
        Cliente cliente = (Cliente) fachada.getPessoa();
        ruaText.setText(cliente.getEndereco().getRua());
        numeroText.setText(cliente.getEndereco().getNumero());
        bairroText.setText(cliente.getEndereco().getBairro());
        cidadeText.setText(cliente.getEndereco().getCidade());
    }

    @FXML
    private void chamarAlterarSenhaAction(ActionEvent event) {
        clientePane.setVisible(false);
        alterarSenhaPane.setVisible(true);
    }

    @FXML
    private void chamarApagarContaAction(ActionEvent event) {
        clientePane.setVisible(false);
        apagarContaPane.setVisible(true);
    }

    @FXML
    private void sairAlterarEnderecoAction(ActionEvent event) {
        clientePane.setVisible(true);
        atualizaEnderecoPane.setVisible(false);
    }

    @FXML
    private void chamarNovoPedidoAction(ActionEvent event) {
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("TelaPedido.fxml"));
            clientePane.setVisible(false);
            clienteAnchorPane.getChildren().addAll(pane);
        } catch (IOException ex) {
            GUIException.erroInesperado();
        }

    }

    @FXML
    private void logoutAction(ActionEvent event) {
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
            clientePane.setVisible(false);
            clienteAnchorPane.getChildren().addAll(pane);
            fachada.setPessoa();
        } catch (IOException ex) {
            GUIException.erroInesperado();
        }

    }

    @FXML
    private void sairAlterarSenhaAction(ActionEvent event) {
        clientePane.setVisible(true);
        alterarSenhaPane.setVisible(false);
        senhaAtualText.clear();
        novaSenhaText.clear();
        repitaNovaSenhaText.clear();
    }

    @FXML
    private void salvarNovaSenhaAction(ActionEvent event) {
        try {
            fachada.alterarSenhaCliente(senhaAtualText.getText(), novaSenhaText.getText(), repitaNovaSenhaText.getText());
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Senha alterada com sucesso");
            dialogoErro.setContentText("Volte para pedir");
            dialogoErro.showAndWait();
            clientePane.setVisible(true);
            alterarSenhaPane.setVisible(false);
            senhaAtualText.clear();
            novaSenhaText.clear();
            repitaNovaSenhaText.clear();
        } catch (CpfPessoaInvalidoException ex) {
            GUIException.cpfInvalido();
        } catch (PessoaNaoExisteException ex) {
            GUIException.pessoaNaoExiste();
        } catch (SenhasNaoConferemException ex) {
            GUIException.senhasNaoConferem();
        } catch (SenhaPessoaInvalidaException ex) {
            GUIException.senhaInvalida();
        }
    }

    @FXML
    private void atualizarEnderecoAction(ActionEvent event) {
        try {
            fachada.atualizarCliente(ruaText.getText(), numeroText.getText(), bairroText.getText(), cidadeText.getText());
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Endereço atualizado com sucesso");
            dialogoErro.setContentText("Volte para pedir");
            dialogoErro.showAndWait();
            clientePane.setVisible(true);
            atualizaEnderecoPane.setVisible(false);
        } catch (CpfPessoaInvalidoException ex) {
            GUIException.cpfInvalido();
        } catch (PessoaNaoExisteException ex) {
            GUIException.pessoaNaoExiste();
        } catch (EnderecoInvalidoException ex) {
            GUIException.enderecoInvalido();
        }
    }

    @FXML
    private void sairApagarContaAction(ActionEvent event) {
        clientePane.setVisible(true);
        apagarContaPane.setVisible(false);
    }

    @FXML
    private void apagarContaAction(ActionEvent event) {
        Cliente cliente = (Cliente) fachada.getPessoa();
        Alert dialogoPergunta = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        dialogoPergunta.getButtonTypes().setAll(btnSim, btnNao);
        dialogoPergunta.setTitle("Você tem certeza?");
        dialogoPergunta.setHeaderText("Você tem certeza que deseja apagar sua conta?");
        dialogoPergunta.setContentText("Você perderá acesso ao nosso sistema de delivery!");
        dialogoPergunta.showAndWait().ifPresent(b -> {
            if (b == btnSim) {
                try {
                    fachada.removerCliente(cliente.getCpf(), CpfApagarContaButton.getText(), senhaApagarContaText.getText());
                    logoutAction(event);
                    apagarContaPane.setVisible(false);
                    fachada.setPessoa();
                } catch (PessoaNaoExisteException ex) {
                    GUIException.pessoaNaoExiste();
                } catch (PessoaInvalidoException ex) {
                    GUIException.pessoaInvalida();
                }
            } else if (b == btnNao) {
                clientePane.setVisible(true);
                apagarContaPane.setVisible(false);
            }
        });
    }

    @FXML
    private void mudarNomeAction(ActionEvent event) {
        if (pessoaLogada.getText() != null) {
            fachada.alterarNome(pessoaLogada.getText());
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Nome alterado com sucesso");
            dialogoErro.setContentText("Volte para pedir");
            dialogoErro.showAndWait();
        } else {
            GUIException.nomeInvalido();
        }
    }

    @FXML
    private void cancelarPedidoAction(ActionEvent event) {
        Alert dialogoPergunta = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        dialogoPergunta.getButtonTypes().setAll(btnSim, btnNao);
        dialogoPergunta.setTitle("Você tem certeza?");
        dialogoPergunta.setHeaderText("O pedido será cancelado, e você será multado pelo entregador!");
        dialogoPergunta.setContentText("Multa de metade desse pedido na próxima entrega");
        dialogoPergunta.showAndWait().ifPresent(b -> {
            if (b == btnSim) {
                try {
                    Pedido pedidoSelecionado = pedidosList.getSelectionModel().selectedItemProperty().getValue();
                    pedidoSelecionado.setEstado("Cancelado");
                    pedidosList.setItems(null);
                    pedidosList.setItems(listaObservavel);
                } catch (Exception ex) {
                    GUIException.selecioneAlgumItem();
                }

            } else if (b == btnNao) {
                dialogoPergunta.close();
            }
        });
    }

}
