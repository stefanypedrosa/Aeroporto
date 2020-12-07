package Boundary.Passageiro;

import Boundary.Entrada;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		Label lblNumeroCartao = new Label("Login:");
		Label lblTelefone = new Label("Login:");
		Label lblNascimento = new Label("Login:");
		//colocar endereço
		Label lblSenha = new Label("Senha:");
		
		TextField txtId = new TextField();
		TextField txtLogin = new TextField();
		TextField txtNome = new TextField();
		TextField txtDocumento = new TextField();
		TextField txtNumeroCartao = new TextField();
		TextField txtTelefone = new TextField();
		TextField txtNascimento = new TextField();
		PasswordField txtSenha = new PasswordField();
		
        Button btnCadastrar = new Button("Cadastrar");
        Button btnVoltar = new Button("Voltar");
        Button btnEntrar = new Button("Entrar");
        
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        btnEntrar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnVoltar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnCadastrar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
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
        
        btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para tela de signUp
            	SignUp signUp = new SignUp();
            	try {
            		Stage newStage = new Stage();
					signUp.start(newStage);
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
        grid.add(txtNascimento, 2, 5);
        grid.add(lblSenha, 3, 5);
        grid.add(txtSenha, 4, 5);
        grid.add(btnVoltar, 1, 6, 2, 1);
        grid.add(btnCadastrar, 3, 6, 2, 1);
        grid.add(btnEntrar, 1, 7, 4, 1);
        
        grid.setPadding(new Insets(0, 5, 0, 0));
        
        GridPane.setHalignment(titulo, HPos.CENTER);
		
		stage.setResizable(false);
		stage.setScene(new Scene(grid, 450, 250));
		stage.show();		
	}

}
