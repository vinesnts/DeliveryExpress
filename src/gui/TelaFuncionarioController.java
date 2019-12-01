/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import fachadas.Fachada;
import fachadas.IFachadaFuncionario;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import negocio.entidade.Alimento;
import negocio.entidade.AlimentoPedido;
import negocio.entidade.Pedido;
import negocio.excecoes.alimento.AlimentoInvalidoException;
import negocio.excecoes.alimento.AlimentoJaExisteException;
import negocio.excecoes.alimento.AlimentoNaoExisteException;
import negocio.excecoes.alimento.PrecoInvalidoException;
import negocio.excecoes.pedido.PedidoNaoExisteException;
import negocio.excecoes.pessoa.PessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaJaExisteException;
import negocio.excecoes.pessoa.PessoaNaoExisteException;

/**
 * FXML Controller class
 *
 * @author vinicius9812
 */
public class TelaFuncionarioController implements Initializable {

    private boolean eGerente;
    private IFachadaFuncionario fachada;
    private List listaPedidos = new ArrayList<>();
    private ObservableList<Pedido> listaObservavelPedido;
    private List listaAlimentosPedido = new ArrayList<>();
    private ObservableList<AlimentoPedido> listaObservavelAlimentoPedido;
    private List listaCardapio = new ArrayList<>();
    private ObservableList<Alimento> listaObservavelCardapio;
    private Alimento alimento;

    @FXML
    private Button sairFuncionarioButton;
    @FXML
    private MenuButton gerenteMenu;
    @FXML
    private MenuItem demitirFuncionarioButton;
    @FXML
    private Button novoAlimentoButton;
    @FXML
    private ListView<Pedido> pedidoArea;
    @FXML
    private Button confirmarEntregaButton;
    @FXML
    private Pane funcionarioPane;
    @FXML
    private AnchorPane funcionarioAnchorPane;
    @FXML
    private Button voltarFuncionarioButton;
    @FXML
    private TextField nomeAdmitirText;
    @FXML
    private TextField cpfAdmitirText;
    @FXML
    private PasswordField senhaAdmitirText;
    @FXML
    private Button admitirButton;
    @FXML
    private Pane admitirPane;
    @FXML
    private MenuItem admitirFuncionarioButton;
    @FXML
    private Pane demitirPane;
    @FXML
    private Button voltarDemitirButton;
    @FXML
    private TextField cpfDemitirText;
    @FXML
    private Button demitirButton;
    @FXML
    private Pane novoAlimentoPane;
    @FXML
    private Button voltarAlimentoButton;
    @FXML
    private TextField nomeAlimentoText;
    @FXML
    private TextField precoText;
    private TextField tipoText;
    @FXML
    private Button cadastrarAlimentoButton;
    @FXML
    private SplitMenuButton tipoSelect;
    @FXML
    private MenuItem comidaButton;
    @FXML
    private MenuItem bebidaButton;
    @FXML
    private Button verPedidoButton;
    @FXML
    private Pane detalhePedidoPane;
    @FXML
    private ListView<AlimentoPedido> detalhePedidoArea;
    @FXML
    private Button voltarPedidoDetalheButton;
    @FXML
    private TextArea enderecoArea;
    @FXML
    private Button atualizarPrecosButton;
    @FXML
    private Button voltarAtualizarMenuButton;
    @FXML
    private Button editarAlimentoButton;
    @FXML
    private Button concluirEdicaoButton;
    @FXML
    private Pane atualizarMenuPane;
    @FXML
    private TextField atualizarNomeAlimentoText;
    @FXML
    private TextField atualizarPrecoAlimentoText;
    @FXML
    private ListView<Alimento> menuArea;
    @FXML
    private Button ajudaButton;

    public TelaFuncionarioController() {
        this.fachada = Fachada.getInstance();
        this.eGerente = fachada.getPessoa().getCpf().equals("administrad");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gerenteMenu.setVisible(eGerente);
        tipoSelect.setText("Tipo");
        listarPedidos();
        pedidoArea.setItems(listaObservavelPedido);
        listarMenu();
        menuArea.setItems(listaObservavelCardapio);
    }

    private void listarMenu() {
        ArrayList<Alimento> cardapio = fachada.buscarCardapio();
        for (int i = 0; i < cardapio.size(); i++) {
            listaCardapio.add(cardapio.get(i));
        }
        listaObservavelCardapio = FXCollections.observableArrayList(listaCardapio);

    }

    private void listarAlimentoPedido(int idPedido) {

        ArrayList<AlimentoPedido> alimentoPedido;
        try {
            alimentoPedido = fachada.buscarAlimentoPedido(idPedido);
            for (int i = 0; i < alimentoPedido.size(); i++) {
                listaAlimentosPedido.add(alimentoPedido.get(i));
            }
            listaObservavelAlimentoPedido = FXCollections.observableArrayList(listaAlimentosPedido);
        } catch (PedidoNaoExisteException ex) {
            Logger.getLogger(TelaFuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarPedidos() {

        ArrayList<Pedido> pedidos = fachada.buscarPedidosFuncionario();
        for (int i = 0; i < pedidos.size(); i++) {
            listaPedidos.add(pedidos.get(i));
        }
        listaObservavelPedido = FXCollections.observableArrayList(listaPedidos);

    }

    @FXML
    private void logoutFuncionarioAction(ActionEvent event) {
        Pane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("TelaLogin.fxml"));
            funcionarioPane.setVisible(false);
            funcionarioAnchorPane.getChildren().addAll(pane);
            fachada.setPessoa();
        } catch (IOException ex) {
            GUIException.erroInesperado();
        }

    }

    @FXML
    private void admitirFuncionarioAction(ActionEvent event) {
        funcionarioPane.setVisible(false);
        admitirPane.setVisible(true);
    }

    @FXML
    private void demitirFuncionarioAction(ActionEvent event) {
        demitirPane.setVisible(true);
        funcionarioPane.setVisible(false);
    }

    @FXML
    private void chamarNovoAlimentoAction(ActionEvent event) {
        novoAlimentoPane.setVisible(true);
        funcionarioPane.setVisible(false);
    }

    @FXML
    private void confirmarEntregaAction(ActionEvent event) {
        Pedido pedidoEntregue = pedidoArea.getSelectionModel().selectedItemProperty().getValue();
        if (pedidoEntregue == null) {
            GUIException.selecioneAlgumItem();
        } else {
            pedidoEntregue.setEstado("Entregue");
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Pedido entregue com sucesso");
            dialogoErro.setContentText("Os clientes estão esperando, entregue mais pedidos");
            dialogoErro.showAndWait();
            pedidoArea.setItems(null);
            pedidoArea.setItems(listaObservavelPedido);
        }

    }

    @FXML
    private void voltarFuncionarioAction(ActionEvent event) {
        funcionarioPane.setVisible(true);
        admitirPane.setVisible(false);
        nomeAdmitirText.clear();
        cpfAdmitirText.clear();
        senhaAdmitirText.clear();
    }

    @FXML
    private void admitirAction(ActionEvent event) {
        try {
            fachada.cadastrarFuncionario(nomeAdmitirText.getText(), cpfAdmitirText.getText(), senhaAdmitirText.getText());
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Funcionário admitido com sucesso");
            dialogoErro.setContentText("Funcionário admitido");
            dialogoErro.showAndWait();
            voltarFuncionarioAction(event);
        } catch (PessoaJaExisteException ex) {
            GUIException.pessoaJaExiste();
        } catch (PessoaInvalidoException ex) {
            GUIException.pessoaInvalida();
        } catch (PessoaNaoExisteException ex) {
            GUIException.pessoaNaoExiste();
        }
    }

    @FXML
    private void voltarDemitirAction(ActionEvent event) {
        funcionarioPane.setVisible(true);
        demitirPane.setVisible(false);
        cpfDemitirText.clear();
    }

    @FXML
    private void demitirAction(ActionEvent event) {

        Alert dialogoPergunta = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");
        dialogoPergunta.getButtonTypes().setAll(btnSim, btnNao);
        dialogoPergunta.setTitle("Você tem certeza?");
        dialogoPergunta.setHeaderText("Você tem certeza que deseja demitir o funcionário?");
        dialogoPergunta.setContentText("O funcionário deixará de trabalhar para você!");
        dialogoPergunta.showAndWait().ifPresent(b -> {
            if (b == btnSim) {
                try {
                    fachada.removerFuncionario(cpfDemitirText.getText());
                    Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
                    dialogoErro.setTitle("Sucesso");
                    dialogoErro.setHeaderText("Funcionário demitido com sucesso");
                    dialogoErro.setContentText("Funcionário demitido");
                    dialogoErro.showAndWait();
                    voltarDemitirAction(event);
                    demitirPane.setVisible(false);
                } catch (PessoaNaoExisteException ex) {
                    GUIException.pessoaNaoExiste();
                } catch (PessoaInvalidoException ex) {
                    GUIException.pessoaInvalida();
                }
            } else if (b == btnNao) {
                funcionarioPane.setVisible(true);
                demitirPane.setVisible(false);
            }
        });
    }

    @FXML
    private void voltarAlimentoAction(ActionEvent event) {
        novoAlimentoPane.setVisible(false);
        funcionarioPane.setVisible(true);
        menuArea.setItems(null);
        menuArea.setItems(listaObservavelCardapio);
        nomeAlimentoText.clear();
        precoText.clear();
        tipoText.clear();
    }

    private void setTipo() {
        if (tipoSelect.getText().equals("Tipo")) {
            Alert dialogoPergunta = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnComida = new ButtonType("Comida");
            ButtonType btnBebida = new ButtonType("Bebida");
            dialogoPergunta.getButtonTypes().setAll(btnComida, btnBebida);
            dialogoPergunta.setTitle("Seleciona o tipo");
            dialogoPergunta.setHeaderText("Qual o tipo de alimento?");
            dialogoPergunta.setContentText("Escolha o tipo de alimento que deseja cadastrar");
            dialogoPergunta.showAndWait().ifPresent(b -> {
                if (b == btnComida) {
                    tipoSelect.setText("Comida");
                } else if (b == btnBebida) {
                    tipoSelect.setText("Bebida");
                }
            });
        }
    }

    @FXML
    private void cadastrarAlimentoAction(ActionEvent event) {
        try {
            setTipo();
            fachada.cadastrarAlimento(nomeAlimentoText.getText(), precoText.getText(), tipoSelect.getText());
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Alimento cadastrado com sucesso");
            dialogoErro.setContentText("Novo alimento no cardápio");
            dialogoErro.showAndWait();
            tipoSelect.setText("Tipo");
            voltarAlimentoAction(event);
        } catch (AlimentoInvalidoException ex) {
            GUIException.alimentoInvalido();
        } catch (AlimentoJaExisteException ex) {
            GUIException.alimentoJaExiste();
        } catch (PrecoInvalidoException | NumberFormatException ex) {
            GUIException.precoInvalido();
        }
    }

    @FXML
    private void comidaAction(ActionEvent event) {
        tipoSelect.setText("Comida");
    }

    @FXML
    private void bebidaAction(ActionEvent event) {
        tipoSelect.setText("Bebida");
    }

    @FXML
    private void verPedidoAction(ActionEvent event) {
        Pedido pedidoSelecionado = pedidoArea.getSelectionModel().selectedItemProperty().getValue();
        if (pedidoSelecionado == null) {
            GUIException.selecioneAlgumItem();
        } else {
            listarAlimentoPedido(pedidoSelecionado.getId());
            detalhePedidoArea.setItems(listaObservavelAlimentoPedido);
            enderecoArea.setText(pedidoSelecionado.getCliente().getEndereco().toString());
            detalhePedidoPane.setVisible(true);
            funcionarioPane.setVisible(false);
        }
    }

    @FXML
    private void voltarPedidoDetalheButton(ActionEvent event) {
        detalhePedidoPane.setVisible(false);
        funcionarioPane.setVisible(true);
    }

    @FXML
    private void chamarAtualizarPrecosButton(ActionEvent event) {
        funcionarioPane.setVisible(false);
        atualizarMenuPane.setVisible(true);
    }

    @FXML
    private void voltarAtualizarMenuAction(ActionEvent event) {
        funcionarioPane.setVisible(true);
        atualizarMenuPane.setVisible(false);
        atualizarNomeAlimentoText.clear();
        atualizarPrecoAlimentoText.clear();
    }

    @FXML
    private void editarAlimentoAction(ActionEvent event) {
        try {
        alimento = menuArea.getSelectionModel().selectedItemProperty().getValue();

        atualizarNomeAlimentoText.setText(alimento.getNome());
        atualizarPrecoAlimentoText.setText(Double.toString(alimento.getPreco()));
        } catch (NullPointerException ex) {
            GUIException.selecioneAlgumItem();
        }
    }

    @FXML
    private void concluirEdicaoAction(ActionEvent event) {
        try {
            fachada.atualizarAlimento(alimento.getNome(), atualizarNomeAlimentoText.getText(), Double.parseDouble(atualizarPrecoAlimentoText.getText()));
            menuArea.setItems(null);
            menuArea.setItems(listaObservavelCardapio);
            atualizarNomeAlimentoText.clear();
            atualizarPrecoAlimentoText.clear();
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Menu atualizado com sucesso");
            dialogoErro.setContentText("Alimento atualizada no menu");
            dialogoErro.showAndWait();
            alimento = null;
        } catch (AlimentoNaoExisteException ex) {
            GUIException.alimentoNaoExiste();
        } catch (NumberFormatException ex) {
            GUIException.precoInvalido();
        } catch (PrecoInvalidoException ex) {
            GUIException.precoInvalido();
        } catch (NullPointerException ex) {
            GUIException.selecioneAlgumItem();
        } catch (AlimentoInvalidoException ex) {
            GUIException.alimentoInvalido();
        }
    }

    @FXML
    private void ajudaAction(ActionEvent event) {
        Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
        dialogoErro.setTitle("Ajuda Funcionário");
        dialogoErro.setHeaderText("Delivery Express");
        dialogoErro.setContentText("    No Delivery Express...     \n"
                + "-É possível ver detalhes dos pedidos a partir do\n"
                + "botão Ver Pedido na tela inicial do funcionário.\n"
                + "-Há um botão que o levará ao menu, onde permitirá\n"
                + "editar o menu, preços e nomes.                  \n"
                + "-O gerente tem um menu exclusivo que permite que\n"
                + "ele admita e demita funcionários, menu aparecerá\n"
                + "no canto esquedo superior quando o gerente entrar.\n");
        dialogoErro.showAndWait();
    }

}
