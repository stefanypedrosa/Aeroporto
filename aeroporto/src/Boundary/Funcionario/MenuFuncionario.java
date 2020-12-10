package Boundary.Funcionario;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuFuncionario extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Menu");
		
		Label titulo = new Label("Bem vindo, funcionário!");		//pegar nome do current
		Button btnPassageiro = new Button("Cadastrar Passageiros");
        Button btnAviao = new Button("Cadastrar Aviões");
        Button btnFuncionario = new Button("Cadastrar Funcionários");
        Button btnBilhete = new Button("Cadastrar Bilhete");
        
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        btnPassageiro.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnAviao.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnFuncionario.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnBilhete.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        btnPassageiro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para crud de funcionário
            	CrudPassageiro crudPassageiro = new CrudPassageiro();
            	try {
            		Stage newStage = new Stage();
					crudPassageiro.start(newStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnAviao.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para crud de aviao
            	CrudAviao crudAviao = new CrudAviao();
            	try {
            		Stage newStage = new Stage();
					crudAviao.start(newStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnFuncionario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para crud de funcionario
            	CrudFuncionario crudFuncionario = new CrudFuncionario();
            	try {
            		Stage newStage = new Stage();
					crudFuncionario.start(newStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        
        btnBilhete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para crud de bilhete
            	CrudBilhete crudBilhete = new CrudBilhete();
            	try {
            		Stage newStage = new Stage();
            		crudBilhete.start(newStage);
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
        grid.add(btnPassageiro, 1, 2);
        grid.add(btnAviao, 2, 2);
        grid.add(btnFuncionario, 1, 3);
        grid.add(btnBilhete, 2, 3);
        
        GridPane.setHalignment(titulo, HPos.CENTER);
		
		stage.setResizable(false);
		stage.setScene(new Scene(grid, 280, 150));
		stage.show();
	}

}
