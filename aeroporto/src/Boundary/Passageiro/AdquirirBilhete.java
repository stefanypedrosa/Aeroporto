package Boundary.Passageiro;

import java.util.ArrayList;
import java.util.List;

import Entity.Bilhete;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdquirirBilhete extends Application {

	private TableView<Bilhete> tblBilhete;
	
	private TableColumn<Bilhete, String> tcId;
	private TableColumn<Bilhete, String> tcNumero;
	private TableColumn<Bilhete, String> tcAssento;
	private TableColumn<Bilhete, String> tcSituacao;
	
	private Button btnReservar;
	private Button btnComprar;
		
	private Scene scene;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Bilhetes");
		
		iniciarAtributos();
		preencherTabela();
		definirLayout();
		
		stage.setResizable(false);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private void iniciarAtributos() {
		tblBilhete = new TableView();

	    tcId = new TableColumn<>("ID");
	    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    tcNumero = new TableColumn<>("Número");
	    tcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
	    
	    tcAssento = new TableColumn<>("Assento");
	    tcAssento.setCellValueFactory(new PropertyValueFactory<>("assento"));
	    
	    tcSituacao = new TableColumn<>("Situacao");
	    tcSituacao.setCellValueFactory(new PropertyValueFactory<>("assento"));		//trocar para situacao depois
	    
	    btnReservar = new Button("Reservar");
	    btnComprar = new Button("Comprar");
	    
	    tcId.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcNumero.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcAssento.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcSituacao.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));

	    tblBilhete.getColumns().add(tcId);
	    tblBilhete.getColumns().add(tcNumero);
	    tblBilhete.getColumns().add(tcAssento);
	    tblBilhete.getColumns().add(tcSituacao);
	}
	
	private void preencherTabela() {
	    List<Bilhete> bilhetes = new ArrayList();	//já puxar os valores do getAll aqui

	    //valores de teste -- começo
	    Bilhete teste1 = new Bilhete();
	    Bilhete teste2 = new Bilhete();
	    
	    teste1.setId(1);
	    teste1.setNumero(1);
	    teste1.setAssento("1");
	    
	    teste2.setId(2);
	    teste2.setNumero(2);
	    teste2.setAssento("2");
	    
	    bilhetes.add(teste1);
	    bilhetes.add(teste2);
	    //valores de teste -- fim
	    
	    for (Bilhete bilhete: bilhetes) {
	    	tblBilhete.getItems().add(bilhete);
	    }
	    
	    //Listener
	    tblBilhete.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	//colocar aqui para selecionar vaga na memória
	    });
	}
	
	private void definirLayout() {
	    GridPane grid = new GridPane();
	    
	    grid.setHgap(5);
	    grid.setVgap(5);
	    
	    btnComprar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    btnReservar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    
	    grid.setPadding(new Insets(0, 5, 10, 0));
	    
	    grid.add(tblBilhete, 1, 1);
	    grid.add(btnComprar, 1, 2);
	    grid.add(btnReservar, 1, 3);

	    scene = new Scene(grid, 250, 245);
	}
	
}