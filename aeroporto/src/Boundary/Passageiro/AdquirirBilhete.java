package Boundary.Passageiro;

import java.util.List;

import Boundary.Mensagem;
import Control.BilheteControl;
import Entity.Bilhete;
import Exception.BilheteException;
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

	BilheteControl control = new BilheteControl();
	
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
		adicionarEventos();
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
		try {
	    	control.pesquisarTodos();
	    } catch (Exception ex) {
	    	
	    }
	    List<Bilhete> bilhetes = control.getLista();

	    tblBilhete.getItems().clear();
	    
	    for (Bilhete bilhete: bilhetes) {
	    	tblBilhete.getItems().add(bilhete);
	    }
	}
	
	private void adicionarEventos() {
		//Listener
	    tblBilhete.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	selectedBilhete = newSelection;
	    });
	    
	    
	    btnComprar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if (selectedBilhete != null) {
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
            	if (selectedBilhete != null) {
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

	    scene = new Scene(grid, 650, 245);
	}
	
	private void realizarCompra(Bilhete bilhete) {
		bilhete.setSituacaoBilhete("VENDIDO");
		control.setBilhete(bilhete);
		try {
			control.atualizar();
		} catch (BilheteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		preencherTabela();
	}
	
	private void realizarReserva(Bilhete bilhete) {
		bilhete.setSituacaoBilhete("RESERVADO");
		control.setBilhete(bilhete);
		try {
			control.atualizar();
		} catch (BilheteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		preencherTabela();
	}
	
}