package Boundary.Passageiro;

import Boundary.Entrada;
import Boundary.Mensagem;
import Entity.Endereco;
import Entity.Passageiro;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SignUp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Sign Up");
		
		Label titulo = new Label("Cadastre-se!");
		Label lblId = new Label("Id:");
		Label lblLogin = new Label("Login:");
		Label lblNome = new Label("Nome:");
		Label lblDocumento = new Label("Documento:");
		Label lblNumeroCartao = new Label("N�mero do Cart�o:");
		Label lblTelefone = new Label("Telefone:");
		Label lblNascimento = new Label("Data de nascimento:");
		Label lblIdEndereco = new Label("Id do endereco:");
		Label lblPais = new Label("Pa�s:");
		Label lblEstado = new Label("Estado:");
		Label lblCidade = new Label("Cidade:");
		Label lblBairro = new Label("Bairro:");
		Label lblRua = new Label("Rua:");
		Label lblComplemento = new Label("Complemento:");
		Label lblNumero = new Label("N�mero:");
		Label lblSenha = new Label("Senha:");
		Label lblConfirmarSenha = new Label("Confirmar Senha:");
		
		TextField txtId = new TextField();
		TextField txtLogin = new TextField();
		TextField txtNome = new TextField();
		TextField txtDocumento = new TextField();
		TextField txtNumeroCartao = new TextField();
		TextField txtTelefone = new TextField();
		DatePicker dtpNascimento = new DatePicker();
		TextField txtIdEndereco = new TextField();
		TextField txtPais = new TextField();
		TextField txtEstado = new TextField();
		TextField txtCidade = new TextField();
		TextField txtBairro = new TextField();
		TextField txtRua = new TextField();
		TextField txtComplemento = new TextField();
		TextField txtNumero = new TextField();
		PasswordField txtSenha = new PasswordField();
		PasswordField txtConfirmarSenha = new PasswordField();
		
        Button btnCadastrar = new Button("Cadastrar");
        Button btnVoltar = new Button("Voltar");
        Button btnEntrar = new Button("Entrar");
        
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        btnEntrar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnVoltar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnCadastrar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para tela de signUp
            	if (!txtId.getText().equals("") && !txtLogin.getText().equals("") &&
            			!txtNome.getText().equals("") && !txtDocumento.getText().equals("") &&
            			!txtNumeroCartao.getText().equals("") && !txtTelefone.getText().equals("") &&
            			!txtSenha.getText().equals("") && txtSenha.getText().equals(txtConfirmarSenha.getText())) {
            		Passageiro newPassageiro = new Passageiro();
            		Endereco newEndereco = new Endereco();
            		
            		newPassageiro.setId(Integer.parseInt(txtId.getText()));
            		newPassageiro.setUsuario(txtLogin.getText());
            		newPassageiro.setNome(txtNome.getText());
            		newPassageiro.setDocumento(txtDocumento.getText());
            		newPassageiro.setNumeroCartao(txtNumeroCartao.getText());
            		newPassageiro.setTeleone(txtTelefone.getText());		//mudar para telefone
            		newPassageiro.setDataNascimento(dtpNascimento.getValue());
            		newEndereco.setId(Integer.parseInt(txtIdEndereco.getText()));
            		newEndereco.setPais(txtPais.getText());
            		newEndereco.setEstado(txtEstado.getText());
            		newEndereco.setCidade(txtCidade.getText());
            		newEndereco.setBairro(txtBairro.getText());
            		newEndereco.setRua(txtRua.getText());
            		newEndereco.setComplemento(txtComplemento.getText());
            		newEndereco.setNumero(Integer.parseInt(txtNumero.getText()));
            		newPassageiro.setEndereco(newEndereco);
            		newPassageiro.setSenha(txtSenha.getText());
            		
            		try {
		        		realizarSignUp(newPassageiro);
		        		
		        		LoginPassageiro loginPassageiro = new LoginPassageiro();
		            	try {
		            		Stage newStage = new Stage();
							loginPassageiro.start(newStage);
							stage.hide();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            	
		        		Stage mensagemStage = new Stage();
		        		Mensagem mensagem = new Mensagem("Usu�rio cadastrado");
		        		mensagem.start(mensagemStage);
		            	
		            	stage.hide();
            		} catch (Exception ex) {
                		Stage newStage = new Stage();
                		Mensagem mensagem = new Mensagem("Cadastro inv�lido");
                		mensagem.start(newStage);
            		}
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Preencha todos os campos");
            		mensagem.start(newStage);
            	}
            }
        });
        
        btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para tela de passageiro
            	LoginPassageiro loginPassageiro = new LoginPassageiro();
            	try {
            		Stage newStage = new Stage();
					loginPassageiro.start(newStage);
					stage.hide();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //voltar para entrada
            	Entrada entrada = new Entrada();
            	try {
            		Stage newStage = new Stage();
					entrada.start(newStage);
					stage.hide();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(titulo, 1, 1, 4, 1);
        grid.add(lblId, 1, 2);
        grid.add(txtId, 2, 2);
        grid.add(lblLogin, 3, 2);
        grid.add(txtLogin, 4, 2);
        grid.add(lblNome, 1, 3);
        grid.add(txtNome, 2, 3);
        grid.add(lblDocumento, 3, 3);
        grid.add(txtDocumento, 4, 3);
        grid.add(lblNumeroCartao, 1, 4);
        grid.add(txtNumeroCartao, 2, 4);
        grid.add(lblTelefone, 3, 4);
        grid.add(txtTelefone, 4, 4);
        grid.add(lblNascimento, 1, 5);
        grid.add(dtpNascimento, 2, 5);
        grid.add(lblIdEndereco, 3, 5);
        grid.add(txtIdEndereco, 4, 5);
        grid.add(lblPais, 1, 6);
        grid.add(txtPais, 2, 6);
        grid.add(lblEstado, 3, 6);
        grid.add(txtEstado, 4, 6);
        grid.add(lblCidade, 1, 7);
        grid.add(txtCidade, 2, 7);
        grid.add(lblBairro, 3, 7);
        grid.add(txtBairro, 4, 7);
        grid.add(lblRua, 1, 8);
        grid.add(txtRua, 2, 8);
        grid.add(lblComplemento, 3, 8);
        grid.add(txtComplemento, 4, 8);
        grid.add(lblNumero, 1, 9);
        grid.add(txtNumero, 2, 9);
        grid.add(lblSenha, 3, 9);
        grid.add(txtSenha, 4, 9);
        grid.add(lblConfirmarSenha, 1, 10);
        grid.add(txtConfirmarSenha, 2, 10);
        grid.add(btnVoltar, 1, 11, 2, 1);
        grid.add(btnCadastrar, 3, 11, 2, 1);
        grid.add(btnEntrar, 1, 12, 4, 1);
        
        grid.setPadding(new Insets(0, 5, 0, 0));
        
        GridPane.setHalignment(titulo, HPos.CENTER);
		
		stage.setResizable(false);
		stage.setScene(new Scene(grid, 565, 430));
		stage.show();		
	}
	
	private void realizarSignUp(Passageiro passageiro) {
		//puxar m�todo de signUp aqui
	}

}
