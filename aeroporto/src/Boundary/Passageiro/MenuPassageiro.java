package Boundary.Passageiro;

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

public class MenuPassageiro extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.setTitle("Menu");
		
		Label titulo = new Label("Bem vindo, passageiro!");
        Button btnCheckIn = new Button("Check In");
        Button btnBilhete = new Button("Bilhetes");
        
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        btnCheckIn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnBilhete.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        btnCheckIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para checkIn
            	CheckIn checkIn = new CheckIn();
            	try {
            		Stage newStage = new Stage();
					checkIn.start(newStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnBilhete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para adquirir bilhetes
            	AdquirirBilhete adquirirBilhete = new AdquirirBilhete();
            	try {
            		Stage newStage = new Stage();
					adquirirBilhete.start(newStage);
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
        grid.add(btnCheckIn, 1, 2, 2, 1);
        grid.add(btnBilhete, 1, 3, 2, 1);
        
        GridPane.setHalignment(titulo, HPos.CENTER);
		
		stage.setResizable(false);
		stage.setScene(new Scene(grid, 220, 120));
		stage.show();
	}
	
}
