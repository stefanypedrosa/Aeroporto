package Boundary;

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
import javafx.stage.Stage;

public class Mensagem extends Application{
	
	String mensagem;
	
	public Mensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
    @Override
    public void start(Stage stage) {
        stage.setTitle("Mensagem");
        
        Label lblMensagem = new Label(mensagem);
        Button btnOk = new Button("OK");
        
        btnOk.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //fechar tela
				stage.hide();
            }
        });
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.add(lblMensagem, 1, 1);
        grid.add(btnOk, 1, 2);
        
        GridPane.setHalignment(lblMensagem, HPos.CENTER);
        GridPane.setHalignment(btnOk, HPos.CENTER);
        
        grid.setPadding(new Insets(0, 0, 15, 0));
        
        stage.setScene(new Scene(grid, 250, 80));
        stage.show();
    }
    
}
