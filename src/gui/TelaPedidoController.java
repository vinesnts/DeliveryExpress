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
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import negocio.entidade.Alimento;
import negocio.entidade.Pedido;
import negocio.excecoes.alimento.AlimentoNaoExisteException;
import negocio.excecoes.pedido.AlimentoNaoFoiPedidoException;
import negocio.excecoes.pedido.PedidoEntregueException;
import negocio.excecoes.pedido.PedidoNaoExisteException;

/**
 * FXML Controller class
 *
 * @author vinicius9812
 */
public class TelaPedidoController implements Initializable {

    private IFachadaCliente fachada;
    private Pedido pedido;
    private List listaCardapio = new ArrayList<>();
    private ObservableList<Alimento> listaObservavel;

    @FXML
    private AnchorPane pedidoAnchorPane;
    @FXML
    private Pane pedidoPane;
    @FXML
    private ListView<Alimento> cardapioList;
    @FXML
    private Button sairPedidoButton;
    @FXML
    private Button adicionarAlimentoButton;
    private TextField quantidadeText;
    @FXML
    private Button confirmarPedidoButton;
    @FXML
    private Label precoTotalLabel;
    @FXML
    private Button removerAlimentoButton;
    @FXML
    private Spinner<?> quantidadeSpinner;

    public TelaPedidoController() {
        this.fachada = Fachada.getInstance();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.pedido = fachada.adicionarPedido();
        listarCardapio();
        cardapioList.setItems(listaObservavel);
    }

    private void listarCardapio() {
        ArrayList<Alimento> cardapio = fachada.buscarCardapio();
        for (int i = 0; i < cardapio.size(); i++) {
            listaCardapio.add(cardapio.get(i));
        }
        listaObservavel = FXCollections.observableArrayList(listaCardapio);

    }

    @FXML
    private void sairPedidoAction(ActionEvent event) {
        Alert dialogoPergunta = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        dialogoPergunta.getButtonTypes().setAll(btnSim, btnNao);
        dialogoPergunta.setTitle("Você tem certeza?");
        dialogoPergunta.setHeaderText("O pedido será cancelado");
        dialogoPergunta.setContentText("Você terá que fazer outro pedido");
        dialogoPergunta.showAndWait().ifPresent(b -> {
            if (b == btnSim) {
                try {
                    fachada.removerPedido(pedido.getId());
                    Pane pane;
                    pane = FXMLLoader.load(getClass().getResource("TelaCliente.fxml"));
                    pedidoPane.setVisible(false);
                    pedidoAnchorPane.getChildren().addAll(pane);
                } catch (IOException ex) {
                    GUIException.erroInesperado();
                } catch (PedidoNaoExisteException ex) {
                    GUIException.pedidoNaoExiste();
                } catch (PedidoEntregueException ex) {
                    GUIException.pessoaJaExiste();
                } catch (Exception ex) {
                    System.out.println("excecao");
                }

            } else if (b == btnNao) {
                dialogoPergunta.close();
            }
        });
    }

    @FXML
    private void adicionarAlimentoAction(ActionEvent event) {
        try {
            String alimento = cardapioList.getSelectionModel().selectedItemProperty().getValue().getNome();
            fachada.adicionarAlimentoPedido(pedido, alimento, (Integer) quantidadeSpinner.getValue());
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Alimento adicionado com sucesso");
            dialogoErro.setContentText("Peça mais alimentos ou confirme o pedido");
            dialogoErro.showAndWait();
            atualizarPrecoTotal();
        } catch (AlimentoNaoExisteException ex) {
            GUIException.alimentoNaoExiste();
        } catch (PedidoEntregueException ex) {
            GUIException.pedidoJaFoiEntregue();
        } catch (NumberFormatException ex) {
            GUIException.quantidadeInvalida();
        } catch (Exception ex) {
            GUIException.selecioneAlgumItem();
        }
    }

    @FXML
    private void confirmarPedidoAction(ActionEvent event) {
        Pane pane;
        if (pedido.getPrecoTotal() <= 0) {
            GUIException.selecioneAlgumItem();
        } else {
            try {
                pane = FXMLLoader.load(getClass().getResource("TelaCliente.fxml"));
                pedidoPane.setVisible(false);
                pedidoAnchorPane.getChildren().addAll(pane);
                Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
                dialogoErro.setTitle("Sucesso");
                dialogoErro.setHeaderText("Pedido realizado com sucesso");
                dialogoErro.setContentText("Aguarde e receberá seu pedido em casa");
                dialogoErro.showAndWait();
            } catch (IOException ex) {
                GUIException.erroInesperado();
            } catch (Exception ex) {
                System.out.println("excecao");
            }
        }
    }

    private void atualizarPrecoTotal() {
        String precoTotal = "Preço Total: " + Double.toString(pedido.getPrecoTotal());
        precoTotalLabel.setText(precoTotal);
    }

    @FXML
    private void removerAlimentoAction(ActionEvent event) {
        try {
            String alimento = cardapioList.getSelectionModel().selectedItemProperty().getValue().getNome();
            fachada.removerAlimentoPedido(pedido, alimento);
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Alimento(s) removido(s) com sucesso");
            dialogoErro.setContentText("Peça mais alimentos ou confirme o pedido");
            dialogoErro.showAndWait();
            atualizarPrecoTotal();
            quantidadeText.setText("1");
        } catch (AlimentoNaoFoiPedidoException | AlimentoNaoExisteException ex) {
            GUIException.alimentoNaoExiste();
        } catch (Exception ex) {
            GUIException.selecioneAlgumItem();
        }
    }

    @FXML
    private void incrementarQuantidade(MouseEvent event) {
    }


}
