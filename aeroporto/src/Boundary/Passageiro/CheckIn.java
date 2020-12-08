package Boundary.Passageiro;

import java.util.ArrayList;
import java.util.List;

import Entity.Bagagem;
import Entity.Bilhete;
import Entity.SituacaoBilhete;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CheckIn extends Application {

private TableView<Bilhete> tblBilhete;
	
	private TableColumn<Bilhete, String> tcId;
	private TableColumn<Bilhete, String> tcNumero;
	private TableColumn<Bilhete, String> tcAssento;
	private TableColumn<Bilhete, String> tcSituacao;
	
	private TextField txtId;
	private TextField txtPeso;
	
	private Button btnCheckIn;
		
	private Scene scene;
	
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

	    tcNumero = new TableColumn<>("Número");
	    tcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
	    
	    tcAssento = new TableColumn<>("Assento");
	    tcAssento.setCellValueFactory(new PropertyValueFactory<>("assento"));
	    
	    tcSituacao = new TableColumn<>("Situacao");
	    tcSituacao.setCellValueFactory(new PropertyValueFactory<>("situacaoBilhete"));		//trocar para situacao depois
	    
	    txtId = new TextField();
	    txtPeso = new TextField();
	    btnCheckIn = new Button("Check In");
	    
	    txtId.setPromptText("ID");
	    txtPeso.setPromptText("Peso");
	    
	    tcId.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcNumero.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcAssento.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcSituacao.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));

	    tblBilhete.getColumns().add(tcId);
	    tblBilhete.getColumns().add(tcNumero);
	    tblBilhete.getColumns().add(tcAssento);
	    tblBilhete.getColumns().add(tcSituacao);
	    
	    btnCheckIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //ir para tela de signUp
            	if (!txtId.getText().equals("") && !txtPeso.getText().equals("")) {
	            	Bagagem bagagem = new Bagagem();
	            	try {
	            		bagagem.setId(Integer.parseInt(txtId.getText()));
	            		bagagem.setPeso(Double.parseDouble(txtPeso.getText()));
	            		realizarCheckIn(bagagem);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	} else {
            		//tela de erro
            	}
            }
        });
	}
	
	private void preencherTabela() {
	    List<Bilhete> bilhetes = new ArrayList<Bilhete>();	//já puxar os valores do getAll aqui

	    //valores de teste -- começo ----------------- receber valores reais aqui
	    Bilhete teste1 = new Bilhete();
	    Bilhete teste2 = new Bilhete();
	    
	    teste1.setId(1);
	    teste1.setNumero(1);
	    teste1.setAssento("1");
	    teste1.setSituacaoBilhete(SituacaoBilhete.RESERVADO);
	    
	    teste2.setId(2);
	    teste2.setNumero(2);
	    teste2.setAssento("2");
	    teste1.setSituacaoBilhete(SituacaoBilhete.DISPONIVEL);
	    
	    bilhetes.add(teste1);
	    bilhetes.add(teste2);
	    //valores de teste -- fim
	    
	    for (Bilhete bilhete: bilhetes) {
	    	tblBilhete.getItems().add(bilhete);
	    }
	    
	    //Listener
	    tblBilhete.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	//get selected para inserir bagagem
	    });
	}
	
	private void definirLayout() {
	    GridPane grid = new GridPane();
	    
	    grid.setHgap(5);
	    grid.setVgap(5);
	    
	    btnCheckIn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    
	    grid.setPadding(new Insets(0, 5, 0, 0));
	    
	    grid.add(tblBilhete, 1, 1, 2, 1);
	    grid.add(txtId, 1, 2);
	    grid.add(txtPeso, 2, 2);
	    grid.add(btnCheckIn, 1, 3, 2, 1);

	    scene = new Scene(grid, 320, 480);
	}
	
	private void realizarCheckIn(Bagagem bagagem) {
		//colocar aqui lógica do checkIn
	}

}
