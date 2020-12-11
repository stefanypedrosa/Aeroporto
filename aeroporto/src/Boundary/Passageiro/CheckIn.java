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

public class CheckIn extends Application {

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

	    
	    btnCheckIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para tela de signUp
            	if (selectedBilhete != null && selectedBilhete.getSituacaoBilhete().equals("COMPRADO")) {
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
	    List<Bilhete> bilhetes = new ArrayList<Bilhete>();	//j� puxar os valores do getAll aqui

	    //valores de teste -- come�o ----------------- receber valores reais aqui
	    Bilhete teste1 = new Bilhete();
	    Bilhete teste2 = new Bilhete();
	    
	    teste1.setId(1);
	    teste1.setNumero(1);
	    teste1.setAssento("1");
	    teste1.setSituacaoBilhete("RESERVADO");
	    
	    teste2.setId(2);
	    teste2.setNumero(2);
	    teste2.setAssento("2");
	    teste2.setSituacaoBilhete("COMPRADO");
	    
	    bilhetes.add(teste1);
	    bilhetes.add(teste2);
	    //valores de teste -- fim
	    
	    for (Bilhete bilhete: bilhetes) {
	    	if(bilhete.getSituacaoBilhete().equals("COMPRADO"))
	    		tblBilhete.getItems().add(bilhete);
	    }
	    
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

	    scene = new Scene(grid, 320, 480);
	}
	
	private void realizarCheckIn(Bilhete bilhete) {
		bilhete.setSituacaoBilhete("CONFIRMADO");
		preencherTabela();
	}

}
