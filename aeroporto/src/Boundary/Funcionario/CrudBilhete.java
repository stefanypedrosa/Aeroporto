package Boundary.Funcionario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Boundary.Mensagem;
import Control.BilheteControl;
import Entity.Bilhete;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
	private TableColumn<Bilhete, String> tcPesoBagagem;
	private TableColumn<Bilhete, String> tcSituacaoBilhete;
	private TableColumn<Bilhete, String> tcChegada;
	private TableColumn<Bilhete, String> tcPartida;
	private TableColumn<Bilhete, String> tcCodigoAeroporto;
	
	private TextField txtId;
	private TextField txtNumero;
	private TextField txtAssento;
	private TextField txtPesoBagagem;
	private TextField txtSituacaoBilhete;
	private DatePicker dtpChegada;
	private DatePicker dtpPartida;
	private TextField txtCodigoAeroporto;
	
	private Button btnAdicionar;
	private Button btnAtualizar;
	private Button btnRemover;
		
	private Scene scene;
	
	private BilheteControl control;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Bilhetes");
		
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
	    
	    tcSituacaoBilhete = new TableColumn<>("Situacao Bilhete");
	    tcSituacaoBilhete.setCellValueFactory(new PropertyValueFactory<>("situacaoBilhete"));		
	    
	    tcChegada = new TableColumn<>("Chegada");
	    tcChegada.setCellValueFactory(new PropertyValueFactory<>("chegada"));
	    
	    tcPartida = new TableColumn<>("Partida");
	    tcPartida.setCellValueFactory(new PropertyValueFactory<>("partida"));
	    
	    tcCodigoAeroporto = new TableColumn<>("Codigo Aeroporto");
	    tcCodigoAeroporto.setCellValueFactory(new PropertyValueFactory<>("codigoAeroporto"));
	    
	    txtId = new TextField();
	    txtNumero = new TextField();
	    txtAssento = new TextField();
	    txtPesoBagagem = new TextField();
	    txtSituacaoBilhete = new TextField();
	    dtpChegada = new DatePicker();
	    dtpPartida = new DatePicker();
	    txtCodigoAeroporto = new TextField();
	    btnAdicionar = new Button("Adicionar");
	    btnAtualizar = new Button("Atualizar");
	    btnRemover = new Button("Remover");
	    
	    txtId.setPromptText("ID");
	    txtNumero.setPromptText("N�mero");
	    txtAssento.setPromptText("Assento");
	    txtPesoBagagem.setPromptText("Peso Bagagem");
	    txtSituacaoBilhete.setPromptText("Situacao Bilhete");
	    dtpChegada.setPromptText("Chegada");
	    dtpPartida.setPromptText("Partida");
	    txtCodigoAeroporto.setPromptText("Codigo Aeroporto");
	    
	    tcId.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcNumero.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcAssento.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcPesoBagagem.prefWidthProperty().bind(tblBilhete.maxWidthProperty().divide(4));
	    tcSituacaoBilhete.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcChegada.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcPartida.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));
	    tcCodigoAeroporto.prefWidthProperty().bind(tblBilhete.widthProperty().divide(4));

	    tblBilhete.getColumns().add(tcId);
	    tblBilhete.getColumns().add(tcNumero);
	    tblBilhete.getColumns().add(tcAssento);
	    tblBilhete.getColumns().add(tcPesoBagagem);
	    tblBilhete.getColumns().add(tcSituacaoBilhete);
	    tblBilhete.getColumns().add(tcChegada);
	    tblBilhete.getColumns().add(tcPartida);
	    tblBilhete.getColumns().add(tcCodigoAeroporto);
	    
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
		try {
	    	control.pesquisarTodos();
	    } catch (Exception ex) {
	    	
	    }
	    List<Bilhete> bilhetes = control.getLista();	//j� puxar os valores do getAll aqui

	    tblBilhete.getItems().clear();
	    
	    for (Bilhete bilhete: bilhetes) {
	    	tblBilhete.getItems().add(bilhete);
	    }
	}
	
	private void adicionarListener() {
		//Listener
	    tblBilhete.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	txtId.setText(String.valueOf(newSelection.getId()));
	    	txtNumero.setText(String.valueOf(newSelection.getNumero()));
	    	txtAssento.setText(newSelection.getAssento());
	    	txtPesoBagagem.setText(String.valueOf(newSelection.getPesoBagagem()));
	    	txtSituacaoBilhete.setText(newSelection.getAssento());
	    	dtpChegada.setValue(newSelection.getChegada());
	    	dtpPartida.setValue(newSelection.getPartida());
	    	txtCodigoAeroporto.setText(String.valueOf(newSelection.getCodigoAeroporto()));
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
	    grid.add(txtPesoBagagem, 4, 2);
	    grid.add(txtSituacaoBilhete, 5, 2);
	    grid.add(dtpChegada, 6, 2);
	    grid.add(dtpPartida, 7, 2);
	    grid.add(txtCodigoAeroporto, 8, 2);
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
		bilhete.setPesoBagagem(Double.parseDouble(txtPesoBagagem.getText()));
		bilhete.setSituacaoBilhete(txtSituacaoBilhete.getText());
		bilhete.setChegada(dtpChegada.getValue());
		bilhete.setPartida(dtpPartida.getValue());
		bilhete.setCodigoAeroporto(txtCodigoAeroporto.getText());
		
		return bilhete; 
	}
	
	private boolean validarCampos() {
		if (!txtId.getText().equals("") && !txtNumero.getText().equals("") && !txtAssento.getText().equals("")
				&& (txtSituacaoBilhete.getText().equals("DISPONIVEL") || txtSituacaoBilhete.getText().equals("RESERVADO")
				|| txtSituacaoBilhete.getText().equals("VENDIDO"))) {
			return true;
		} else {
			return false;
		}
	}
	
	private void realizarInsert(Bilhete bilhete) {
		control.setBilhete(bilhete);
		try {
			control.adicionar();
		} catch(Exception ex) {
			
		}
		
		preencherTabela();
	}
	
	private void realizarUpdate(Bilhete bilhete) {
		control.setBilhete(bilhete);
		try {
			control.atualizar();
		} catch(Exception ex) {
			
		}
		
		preencherTabela();
	}

	private void realizarDelete(Long bilheteId) {
		//puxar delete
	}
	
}
