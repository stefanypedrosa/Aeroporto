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

public class CheckIn extends Application {

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
	
	private Button btnCheckIn;
		
	private Scene scene;
	
	private Bilhete selectedBilhete;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Check-In");
		
		iniciarAtributos();
		preencherTabela();
		adicionarListener();
		definirLayout();
		
		stage.setResizable(false);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private void iniciarAtributos() {
		tblBilhete = new TableView<Bilhete>();

		tcId = new TableColumn<>("ID");
	    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    tcNumero = new TableColumn<>("N�mero");
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
	    
	    btnCheckIn = new Button("Check In");
	    
//	    tcId.prefWidthProperty().bind(tblBilhete.widthProperty().divide(8));
//	    tcNumero.prefWidthProperty().bind(tblBilhete.widthProperty().divide(8));
//	    tcAssento.prefWidthProperty().bind(tblBilhete.widthProperty().divide(8));
//	    tcPesoBagagem.prefWidthProperty().bind(tblBilhete.widthProperty().divide(8));
//	    tcSituacao.prefWidthProperty().bind(tblBilhete.widthProperty().divide(8));
//	    tcChegada.prefWidthProperty().bind(tblBilhete.widthProperty().divide(8));
//	    tcPartida.prefWidthProperty().bind(tblBilhete.widthProperty().divide(8));
//	    tcCodigoAeroporto.prefWidthProperty().bind(tblBilhete.widthProperty().divide(8));

	    tblBilhete.getColumns().add(tcId);
	    tblBilhete.getColumns().add(tcNumero);
	    tblBilhete.getColumns().add(tcAssento);
	    tblBilhete.getColumns().add(tcPesoBagagem);
	    tblBilhete.getColumns().add(tcSituacao);
	    tblBilhete.getColumns().add(tcChegada);
	    tblBilhete.getColumns().add(tcPartida);
	    tblBilhete.getColumns().add(tcCodigoAeroporto);

	    
	    btnCheckIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para tela de signUp
            	if (selectedBilhete != null) {
    	            	try {
    	            		realizarCheckIn(selectedBilhete);
    					} catch (Exception e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Escolha um bilhete v�lido");
            		mensagem.start(newStage);
            	}
            }
        });
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
	
	private void adicionarListener() {
	    //Listener
	    tblBilhete.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	selectedBilhete = newSelection;
	    });
	}
	
	private void definirLayout() {
	    GridPane grid = new GridPane();
	    
	    grid.setHgap(5);
	    grid.setVgap(5);
	    
	    btnCheckIn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    
	    grid.setPadding(new Insets(0, 5, 0, 0));
	    
	    grid.add(tblBilhete, 1, 1, 2, 1);
	    grid.add(btnCheckIn, 1, 3, 2, 1);

	    scene = new Scene(grid, 650, 450);
	}
	
	private void realizarCheckIn(Bilhete bilhete) {
		bilhete.setSituacaoBilhete("CONFIRMADO");
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
