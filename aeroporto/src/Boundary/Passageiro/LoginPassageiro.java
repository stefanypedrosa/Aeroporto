package Boundary.Passageiro;

import java.util.ArrayList;
import java.util.List;

import Boundary.Entrada;
import Boundary.Mensagem;
import Entity.Passageiro;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class LoginPassageiro extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Login");
		
		Label titulo = new Label("Bem vindo, passageiro!");
		Label lblLogin = new Label("Login:");
		Label lblSenha = new Label("Senha:");
		TextField txtLogin = new TextField();
		PasswordField txtSenha = new PasswordField();
        Button btnEntrar = new Button("Entrar");
        Button btnVoltar = new Button("Voltar");
        Button btnCadastrar = new Button("Cadastrar-se");
        
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        btnEntrar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnVoltar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnCadastrar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para tela de passageiro
            	if (!txtLogin.getText().equals("") && !txtSenha.getText().equals("")) {
            		if (login(txtLogin.getText(), txtSenha.getText())) {
    	            	MenuPassageiro menuPassageiro = new MenuPassageiro();
    	            	try {
    	            		Stage newStage = new Stage();
    						menuPassageiro.start(newStage);
    						stage.hide();
    					} catch (Exception e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
                	} else {
                		Stage newStage = new Stage();
                		Mensagem mensagem = new Mensagem("Login incorreto");
                		mensagem.start(newStage);
                	}
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Preencha todos os campos");
            		mensagem.start(newStage);
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
        
        grid.add(titulo, 1, 1, 2, 1);
        grid.add(lblLogin, 1, 2);
        grid.add(txtLogin, 2, 2);
        grid.add(lblSenha, 1, 3);
        grid.add(txtSenha, 2, 3);
        grid.add(btnVoltar, 1, 4);
        grid.add(btnEntrar, 2, 4);
        grid.add(btnCadastrar, 1, 5, 2, 1);
        
        GridPane.setHalignment(titulo, HPos.CENTER);
		
		stage.setResizable(false);
		stage.setScene(new Scene(grid, 225, 185));
		stage.show();		
	}
	
	private boolean login(String username, String password) {
		List<Passageiro> passageiros = new ArrayList<Passageiro>();
		for (Passageiro passageiro: passageiros) {
    		if(passageiro.getUsuario().equals(username) && passageiro.getSenha().equals(password))
    			return true;
		}
    	return false;
	}

}
