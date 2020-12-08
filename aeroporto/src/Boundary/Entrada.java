package Boundary;

import Boundary.Funcionario.LoginFuncionario;
import Boundary.Passageiro.LoginPassageiro;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Entrada extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Entrar");
        
        Label titulo = new Label("Entrar como");
        Button btnFuncionario = new Button("Funcionário");
        Button btnPassageiro = new Button("Passageiro");
        
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        btnPassageiro.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnFuncionario.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        btnFuncionario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para login de funcionário
            	LoginFuncionario loginFuncionario = new LoginFuncionario();
            	try {
            		Stage newStage = new Stage();
					loginFuncionario.start(newStage);
					stage.hide();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnPassageiro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para login de passageiro
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
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(titulo, 1, 1);
        grid.add(btnFuncionario, 1, 2);
        grid.add(btnPassageiro, 1, 3);
        
        GridPane.setHalignment(titulo, HPos.CENTER);
        GridPane.setHalignment(btnFuncionario, HPos.CENTER);
        GridPane.setHalignment(btnPassageiro, HPos.CENTER);
        
        grid.setPadding(new Insets(0, 0, 15, 0));
        
        stage.setScene(new Scene(grid, 200, 150));
        stage.show();
    }

}
