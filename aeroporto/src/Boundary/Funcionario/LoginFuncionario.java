package Boundary.Funcionario;

import Boundary.Entrada;
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

public class LoginFuncionario extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Login");
		
		Label titulo = new Label("Bem vindo, funcionário!");
		Label lblLogin = new Label("Login:");
		Label lblSenha = new Label("Senha:");
		TextField txtLogin = new TextField();
		PasswordField txtSenha = new PasswordField();
        Button btnEntrar = new Button("Entrar");
        Button btnVoltar = new Button("Voltar");
        
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        btnEntrar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnVoltar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para tela de funcionário
            	MenuFuncionario menuFuncionario = new MenuFuncionario();
            	try {
            		Stage newStage = new Stage();
					menuFuncionario.start(newStage);
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
//        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(titulo, 1, 1, 2, 1);
        grid.add(lblLogin, 1, 2);
        grid.add(txtLogin, 2, 2);
        grid.add(lblSenha, 1, 3);
        grid.add(txtSenha, 2, 3);
        grid.add(btnEntrar, 1, 4, 2, 1);
        grid.add(btnVoltar, 1, 5, 2, 1);
        
        GridPane.setHalignment(titulo, HPos.CENTER);
		
		stage.setResizable(false);
		stage.setScene(new Scene(grid, 225, 180));
		stage.show();		
	}

}
