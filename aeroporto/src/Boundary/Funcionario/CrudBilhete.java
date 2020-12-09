package Boundary.Funcionario;

import java.util.ArrayList;
import java.util.List;

import Boundary.Mensagem;
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

public class CrudBilhete extends Application{

	private TableView<Bilhete> tblBilhete;
	
	private TableColumn<Bilhete, String> tcId;
	private TableColumn<Bilhete, String> tcNumero;
	private TableColumn<Bilhete, String> tcAssento;
	private TableColumn<Bilhete, String> tcSituacao;
	
	private TextField txtId;
	private TextField txtNumero;
	private TextField txtAssento;
	private TextField txtSituacao;
	
	private Button btnAdicionar;
	private Button btnAtualizar;
	private Button btnRemover;
		
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
		tblBilhete = new TableView<Bilhete>();

	    tcId = new TableColumn<>("ID");
	    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    tcNumero = new TableColumn<>("N�mero");
	    tcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
	    
	    tcAssento = new TableColumn<>("Assento");
	    tcAssento.setCellValueFactory(new PropertyValueFactory<>("assento"));
	    
	    tcSituacao = new TableColumn<>("Situacao");
	    tcSituacao.setCellValueFactory(new PropertyValueFactory<>("assento"));		//trocar para situacao depois
	    
	    txtId = new TextField();
	    txtNumero = new TextField();
	    txtAssento = new TextField();
	    txtSituacao = new TextField();
	    btnAdicionar = new Button("Adicionar");
	    btnAtualizar = new Button("Atualizar");
	    btnRemover = new Button("Remover");
	    
	    txtId.setPromptText("ID");
	    txtNumero.setPromptText("N�mero");
	    txtAssento.setPromptText("Assento");
	    txtSituacao.setPromptText("Situacao");
	    
	    tcId.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcNumero.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcAssento.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcSituacao.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));

	    tblBilhete.getColumns().add(tcId);
	    tblBilhete.getColumns().add(tcNumero);
	    tblBilhete.getColumns().add(tcAssento);
	    tblBilhete.getColumns().add(tcSituacao);
	    
	    btnAdicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	if (validarCampos()) {
	            	Bilhete bilhete = transformarObjeto();
	            	realizarInsert(bilhete);
	            } else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Preencha todos os campos corretamente");
            		mensagem.start(newStage);
	            }
            	
            }
        });
        
        btnAtualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	if (validarCampos()) {
	            	Bilhete bilhete = transformarObjeto();
	            	realizarUpdate(bilhete);
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Preencha todos os campos corretamente");
            		mensagem.start(newStage);
            	}
            	
            }
        });
        
        btnRemover.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	if (validarCampos()) {
            		Bilhete bilhete = transformarObjeto();
	            	realizarDelete(bilhete.getId());
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Escolha um bilhete para remover");
            		mensagem.start(newStage);
            	}
            	
            }
        });
	}
	
	private void preencherTabela() {
	    List<Bilhete> bilhetes = new ArrayList<Bilhete>();	//j� puxar os valores do getAll aqui

	    //valores de teste -- come�o
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
	    	txtId.setText(String.valueOf(newSelection.getId()));
	    	txtNumero.setText(String.valueOf(newSelection.getNumero()));
	    	txtAssento.setText(newSelection.getAssento());
	    	txtSituacao.setText(newSelection.getAssento());
	    });
	}
	
	private void definirLayout() {
	    GridPane grid = new GridPane();
	    
	    grid.setHgap(5);
	    grid.setVgap(5);
	    
	    btnAdicionar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    btnAtualizar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    btnRemover.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    
	    grid.setPadding(new Insets(0, 5, 0, 0));
	    
	    grid.add(tblBilhete, 1, 1, 4, 1);
	    grid.add(txtId, 1, 2);
	    grid.add(txtNumero, 2, 2);
	    grid.add(txtAssento, 3, 2);
	    grid.add(txtSituacao, 4, 2);
	    grid.add(btnAdicionar, 1, 3);
	    grid.add(btnAtualizar, 2, 3, 2, 1);
	    grid.add(btnRemover, 4, 3);

	    scene = new Scene(grid, 420, 480);
	}
	
	private Bilhete transformarObjeto() {
		Bilhete bilhete = new Bilhete();
		bilhete.setId(Long.parseLong(txtId.getText()));
		bilhete.setNumero(Integer.parseInt(txtNumero.getText()));
		bilhete.setAssento(txtAssento.getText());
		switch (txtAssento.getText()) {
			case "DISPONIVEL":
				bilhete.setSituacaoBilhete(SituacaoBilhete.DISPONIVEL);
				break;
			case "RESERVADO":
				bilhete.setSituacaoBilhete(SituacaoBilhete.RESERVADO);
				break;
			case "VENDIDO":
				bilhete.setSituacaoBilhete(SituacaoBilhete.VENDIDO);
				break;
		}
		
		return bilhete; 
	}
	
	private boolean validarCampos() {
		if (!txtId.getText().equals("") && !txtNumero.getText().equals("") && !txtAssento.getText().equals("")
				&& (txtSituacao.getText().equals("DISPONIVEL") || txtSituacao.getText().equals("RESERVADO")
				|| txtSituacao.getText().equals("VENDIDO"))) {
			return true;
		} else {
			return false;
		}
	}
	
	private void realizarInsert(Bilhete bilhete) {
		//puxar insert da controller
	}
	
	private void realizarUpdate(Bilhete bilhete) {
		//puxar update
	}

	private void realizarDelete(Long bilheteId) {
		//puxar delete
	}
	
}