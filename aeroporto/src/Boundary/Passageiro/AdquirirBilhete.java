package Boundary.Passageiro;

import java.util.ArrayList;
import java.util.List;

import Boundary.Mensagem;
import Entity.Bilhete;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private TableColumn<Bilhete, String> tcPesoBagagem;
	private TableColumn<Bilhete, String> tcSituacao;
	private TableColumn<Bilhete, String> tcChegada;
	private TableColumn<Bilhete, String> tcPartida;
	private TableColumn<Bilhete, String> tcCodigoAeroporto;
	
	private Button btnReservar;
	private Button btnComprar;
		
	private Scene scene;
	
	private Bilhete selectedBilhete;
	
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
		tblBilhete = new TableView<Bilhete>();

		tcId = new TableColumn<>("ID");
	    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    tcNumero = new TableColumn<>("Número");
	    tcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
	    
	    tcAssento = new TableColumn<>("Assento");
	    tcAssento.setCellValueFactory(new PropertyValueFactory<>("assento"));
	    
	    tcPesoBagagem = new TableColumn<>("Peso Bagagem");
	    tcPesoBagagem.setCellValueFactory(new PropertyValueFactory<>("pesoBagagem"));
	    
	    tcSituacao = new TableColumn<>("Situacao Bilhete");
	    tcSituacao.setCellValueFactory(new PropertyValueFactory<>("situacaoBilhete"));		
	    
	    tcChegada = new TableColumn<>("Chegada");
	    tcChegada.setCellValueFactory(new PropertyValueFactory<>("chegada"));
	    
	    tcPartida = new TableColumn<>("Partida");
	    tcPartida.setCellValueFactory(new PropertyValueFactory<>("partida"));
	    
	    tcCodigoAeroporto = new TableColumn<>("Codigo Aeroporto");
	    tcCodigoAeroporto.setCellValueFactory(new PropertyValueFactory<>("codigoAeroporto"));
	    
	    btnReservar = new Button("Reservar");
	    btnComprar = new Button("Comprar");
	    
	    tcId.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcNumero.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcAssento.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcPesoBagagem.prefWidthProperty().bind(tblBilhete.maxWidthProperty().divide(4));
	    tcSituacao.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcChegada.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcPartida.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcCodigoAeroporto.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));

	    tblBilhete.getColumns().add(tcId);
	    tblBilhete.getColumns().add(tcNumero);
	    tblBilhete.getColumns().add(tcAssento);
	    tblBilhete.getColumns().add(tcPesoBagagem);
	    tblBilhete.getColumns().add(tcSituacao);
	    tblBilhete.getColumns().add(tcChegada);
	    tblBilhete.getColumns().add(tcPartida);
	    tblBilhete.getColumns().add(tcCodigoAeroporto);
	}
	
	private void preencherTabela() {
	    List<Bilhete> bilhetes = new ArrayList<Bilhete>();	//já puxar os valores do getAll aqui
	    
	    //valores de teste -- começo
	    Bilhete teste1 = new Bilhete();
	    Bilhete teste2 = new Bilhete();
	    
	    teste1.setId(1);
	    teste1.setNumero(1);
	    teste1.setAssento("1");
	    teste1.setSituacaoBilhete("DISPONIVEL");
	    
	    teste2.setId(2);
	    teste2.setNumero(2);
	    teste2.setAssento("2");
	    teste2.setSituacaoBilhete("VENDIDO");
	    
	    bilhetes.add(teste1);
	    bilhetes.add(teste2);
	    //valores de teste -- fim
	    
	    for (Bilhete bilhete: bilhetes) {
	    	tblBilhete.getItems().add(bilhete);
	    }
	    
	    //Listener
	    tblBilhete.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	selectedBilhete = newSelection;
	    });
	    
	    
	    btnComprar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if (selectedBilhete != null && selectedBilhete.getSituacaoBilhete().equals("DISPONIVEL") || selectedBilhete.getSituacaoBilhete().equals("RESERVADO")) {
            		realizarCompra(selectedBilhete);
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Escolha um bilhete válido");
            		mensagem.start(newStage);
            	}
            }
        });
	    
	    btnReservar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if (selectedBilhete != null && selectedBilhete.getSituacaoBilhete().equals("DISPONIVEL")) {
            		realizarReserva(selectedBilhete);
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Escolha um bilhete válido");
            		mensagem.start(newStage);
            	}
            }
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
	
	private void realizarCompra(Bilhete bilhete) {
		bilhete.setSituacaoBilhete("VENDIDO");
		preencherTabela();
	}
	
	private void realizarReserva(Bilhete bilhete) {
		bilhete.setSituacaoBilhete("RESERVADO");
		preencherTabela();
	}
	
}