package gui;

import fachadas.Fachada;
import gui.exception.GUIException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import negocio.excecoes.pessoa.CpfPessoaInvalidoException;
import negocio.excecoes.pessoa.EnderecoInvalidoException;
import negocio.excecoes.pessoa.PessoaInvalidoException;
import negocio.excecoes.pessoa.PessoaJaExisteException;
import negocio.excecoes.pessoa.PessoaNaoExisteException;
import negocio.excecoes.pessoa.SenhaPessoaInvalidaException;

public class TelaLoginController implements Initializable {

    private Fachada fachada;
    private String perfil;

    private Label label;
    @FXML
    private Button logarButton;
    @FXML
    private TextField cpfLoginText;
    @FXML
    private PasswordField senhaLoginText;
    @FXML
    private MenuItem clienteSelected;
    @FXML
    private MenuItem funcionarioSelected;
    @FXML
    private AnchorPane anchorPaneLogin;
    @FXML
    private Pane cadastroPane;
    @FXML
    private SplitMenuButton seletorPerfil;
    @FXML
    private ToolBar toolbarTelaCadastro;
    @FXML
    private TextField nomeCadastroText;
    @FXML
    private TextField cpfCadastroText;
    @FXML
    private PasswordField senhaCadastroText;
    @FXML
    private TextField ruaText;
    @FXML
    private TextField numeroText;
    @FXML
    private TextField bairroText;
    @FXML
    private TextField cidadeText;
    @FXML
    private Pane loginPane;
    @FXML
    private Button cadastrarLoginButton;
    @FXML
    private Button cadastrarCadastroButton;
    @FXML
    private Button ajudaButton;
    @FXML
    private Button sobreButton;

    public TelaLoginController() {
        this.fachada = Fachada.getInstance();
    }

    @FXML
    private void setCliente(ActionEvent event) {
        perfil = "Cliente";
        seletorPerfil.setText(perfil);

    }

    @FXML
    private void setFuncionario(ActionEvent event) {
        perfil = "Funcionario";
        seletorPerfil.setText(perfil);
    }

    private void verificarPerfil() {
        if (perfil == null) {
            Alert dialogoPergunta = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType btnCliente = new ButtonType("Cliente");
            ButtonType btnFuncionario = new ButtonType("Funcionario");
            dialogoPergunta.getButtonTypes().setAll(btnCliente, btnFuncionario);
            dialogoPergunta.setTitle("Selecione o perfil");
            dialogoPergunta.setHeaderText("Qual o seu perfil de usuário?");
            dialogoPergunta.setContentText("Dica: Você é cliente? Então clique em cliente");
            dialogoPergunta.showAndWait().ifPresent(b -> {
                if (b == btnCliente) {
                    perfil = "Cliente";
                    seletorPerfil.setText(perfil);
                } else if (b == btnFuncionario) {
                    perfil = "Funcionario";
                    seletorPerfil.setText(perfil);
                }
            });
        }
    }

    @FXML
    private void entrar(ActionEvent event) {
        verificarPerfil();
        if (perfil.equals("Cliente")) {
            try {
                fachada.logarCliente(cpfLoginText.getText(), senhaLoginText.getText());
                System.out.println("Logado");
                Pane pane;
                pane = FXMLLoader.load(getClass().getResource("TelaCliente.fxml"));
                loginPane.setVisible(false);
                anchorPaneLogin.getChildren().addAll(pane);
            } catch (CpfPessoaInvalidoException ex) {
                GUIException.cpfInvalido();
                cpfLoginText.setText("");
            } catch (SenhaPessoaInvalidaException ex) {
                GUIException.senhaInvalida();
                senhaLoginText.setText("");
            } catch (PessoaNaoExisteException ex) {
                GUIException.pessoaNaoExiste();
            } catch (IOException ex) {
                GUIException.erroInesperado();
            }

        } else if (perfil.equals("Funcionario")) {
            try {
                fachada.logarFuncionario(cpfLoginText.getText(), senhaLoginText.getText());
                System.out.println("Logado");
                Pane pane;
                pane = FXMLLoader.load(getClass().getResource("TelaFuncionario.fxml"));
                loginPane.setVisible(false);
                anchorPaneLogin.getChildren().addAll(pane);
            } catch (CpfPessoaInvalidoException ex) {
                GUIException.cpfInvalido();
            } catch (SenhaPessoaInvalidaException ex) {
                GUIException.senhaInvalida();
            } catch (PessoaNaoExisteException ex) {
                GUIException.pessoaNaoExiste();
            } catch (IOException ex) {
                GUIException.erroInesperado();
            }

        }
    }

    @FXML
    private void chamarTelaCadastro(ActionEvent event) {
        loginPane.setVisible(false);
        cadastroPane.setVisible(true);

    }

    @FXML
    private void cadastrarAction(ActionEvent event) {
        try {
            fachada.cadastrarCliente(nomeCadastroText.getText(), cpfCadastroText.getText(), senhaCadastroText.getText(), ruaText.getText(), numeroText.getText(), bairroText.getText(), cidadeText.getText());
            Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
            dialogoErro.setTitle("Sucesso");
            dialogoErro.setHeaderText("Você foi cadastrado com sucesso");
            dialogoErro.setContentText("Entre para pedir");
            dialogoErro.showAndWait();
            loginPane.setVisible(true);
            cadastroPane.setVisible(false);
        } catch (PessoaJaExisteException ex) {
            GUIException.pessoaJaExiste();
        } catch (EnderecoInvalidoException ex) {
            GUIException.enderecoInvalido();
        } catch (PessoaInvalidoException ex) {
            GUIException.pessoaInvalida();
        } catch (PessoaNaoExisteException ex) {
            GUIException.erroInesperado();
        }

    }

    @FXML
    private void voltarTelaLoginAction(ActionEvent event) {
        loginPane.setVisible(true);
        cadastroPane.setVisible(false);
        nomeCadastroText.clear();
        cpfCadastroText.clear();
        senhaCadastroText.clear();
        ruaText.clear();
        numeroText.clear();
        bairroText.clear();
        cidadeText.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sobreAction(ActionEvent event) {
        Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
        dialogoErro.setTitle("Sobre");
        dialogoErro.setHeaderText("Delivery Express");
        dialogoErro.setContentText("Desenvolvido por:\nVinícius Santos & Edvaldo Heliodorio");
        dialogoErro.showAndWait();
        loginPane.setVisible(true);
        cadastroPane.setVisible(false);
    }

    @FXML
    private void ajudaAction(ActionEvent event) {
        Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
        dialogoErro.setTitle("Ajuda");
        dialogoErro.setHeaderText("Delivery Express");
        dialogoErro.setContentText("    O Delivery Express permite que os clientes\n"
                + "se cadastrem no sistema a peçam pratos e bebidas\n"
                + "do restaurante, é possível acompanhar a entrega\n"
                + "e cancelar pedidos*.                           \n"
                + "-Os pedidos entregues já não aparecerão na tela\n"
                + "de pedidos ativos.                             \n"
                + "-É possível alterar o endereço mesmo após o ca-\n"
                + "dastro, também é possível alterar a senha, e apa-\n"
                + "a conta caso não queira usar mais o serviço, tudo\n"
                + "pelo menu Editar.                                \n"
                + "-É possível alterar o nome após o cadastro na tela\n"
                + "inicial após o login do cliente.                  \n"
                + "*Sujeito a multa");
        dialogoErro.showAndWait();
        loginPane.setVisible(true);
        cadastroPane.setVisible(false);
    }

}
