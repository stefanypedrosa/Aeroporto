package Boundary.Funcionario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Boundary.Mensagem;
import Entity.Horario;
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

public class CrudHorario extends Application{

	private TableView<Horario> tblHorario;
	
	private TableColumn<Horario, String> tcId;
	private TableColumn<Horario, String> tcCodigo;
	private TableColumn<Horario, String> tcChegada;
	private TableColumn<Horario, String> tcPartida;
	private TableColumn<Horario, String> tcCodigoAeroporto;
	
	private TextField txtId;
	private TextField txtCodigo;
	private DatePicker dtpChegada;
	private DatePicker dtpPartida;
	private TextField txtCodigoAeroporto;
	
	private Button btnAdicionar;
	private Button btnAtualizar;
	private Button btnRemover;
		
	private Scene scene;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Horários");
		
		iniciarAtributos();
		preencherTabela();
		definirLayout();
		
		stage.setResizable(false);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private void iniciarAtributos() {
		tblHorario = new TableView<Horario>();

	    tcId = new TableColumn<>("ID");
	    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    tcCodigo = new TableColumn<>("Código");
	    tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    
	    tcChegada = new TableColumn<>("Chegada");
	    tcChegada.setCellValueFactory(new PropertyValueFactory<>("chegada"));
	    
	    tcPartida = new TableColumn<>("Partida");
	    tcPartida.setCellValueFactory(new PropertyValueFactory<>("partida"));
	    
	    tcCodigoAeroporto = new TableColumn<>("Codigo Aeroporto");
	    tcCodigoAeroporto.setCellValueFactory(new PropertyValueFactory<>("codigoAeroporto"));
	    
	    txtId = new TextField();
	    txtCodigo = new TextField();
	    dtpChegada = new DatePicker();
	    dtpPartida = new DatePicker();
	    txtCodigoAeroporto = new TextField();
	    btnAdicionar = new Button("Adicionar");
	    btnAtualizar = new Button("Atualizar");
	    btnRemover = new Button("Remover");
	    
	    txtId.setPromptText("ID");
	    txtCodigo.setPromptText("Código");
	    
	    tcId.prefWidthProperty().bind(tblHorario.widthProperty().divide(4));
	    tcCodigo.prefWidthProperty().bind(tblHorario.widthProperty().divide(4));
	    tcChegada.prefWidthProperty().bind(tblHorario.widthProperty().divide(4));
	    tcPartida.prefWidthProperty().bind(tblHorario.widthProperty().divide(4));
	    tcCodigoAeroporto.prefWidthProperty().bind(tblHorario.widthProperty().divide(4));

	    tblHorario.getColumns().add(tcId);
	    tblHorario.getColumns().add(tcCodigo);
	    tblHorario.getColumns().add(tcChegada);
	    tblHorario.getColumns().add(tcPartida);
	    tblHorario.getColumns().add(tcCodigoAeroporto);
	    
        btnAdicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	if (validarCampos()) {
	            	Horario horario = transformarObjeto();
	            	realizarInsert(horario);
	            } else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Preencha todos os campos");
            		mensagem.start(newStage);
	            }
            	
            }
        });
        
        btnAtualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	if (validarCampos()) {
	            	Horario horario = transformarObjeto();
	            	realizarUpdate(horario);
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Preencha todos os campos");
            		mensagem.start(newStage);
            	}
            	
            }
        });
        
        btnRemover.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	if (validarCampos()) {
	            	Horario horario = transformarObjeto();
	            	realizarDelete(horario.getId());
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Escolha um horário para remover");
            		mensagem.start(newStage);
            	}
            	
            }
        });
	}
	
	private void preencherTabela() {
	    List<Horario> horarios = new ArrayList<Horario>();	//já puxar os valores do getAll aqui

	    //valores de teste -- começo
	    Horario teste1 = new Horario();
	    Horario teste2 = new Horario();
	    
	    teste1.setId(1);
	    teste1.setCodigo("teste1");
	    teste1.setChegada(LocalDate.now());
	    teste1.setPartida(LocalDate.now());
	    teste1.setCodigoAeroporto("gru");
	    
	    teste2.setId(2);
	    teste2.setCodigo("teste2");
	    teste2.setChegada(LocalDate.now());
	    teste2.setPartida(LocalDate.now());
	    teste2.setCodigoAeroporto("mcz");
	    
	    horarios.add(teste1);
	    horarios.add(teste2);
	    //valores de teste -- fim
	    
	    for (Horario horario: horarios) {
	    	tblHorario.getItems().add(horario);
	    }
	    
	    //Listener
	    tblHorario.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	txtId.setText(String.valueOf(newSelection.getId()));
	    	txtCodigo.setText(newSelection.getCodigo());
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
	    
	    grid.add(tblHorario, 1, 1, 4, 1);
	    grid.add(txtId, 1, 2);
	    grid.add(txtCodigo, 2, 2);
	    grid.add(dtpChegada, 3, 2);
	    grid.add(dtpPartida, 4, 2);
	    grid.add(txtCodigoAeroporto, 5, 2);
	    grid.add(btnAdicionar, 1, 3);
	    grid.add(btnAtualizar, 2, 3, 2, 1);
	    grid.add(btnRemover, 4, 3);

	    scene = new Scene(grid, 420, 480);
	}
	
	private Horario transformarObjeto() {
		Horario retornoHorario = new Horario();
		retornoHorario.setId(Long.parseLong(txtId.getText()));
		retornoHorario.setCodigo(txtCodigo.getText());
		retornoHorario.setChegada(dtpChegada.getValue());
		retornoHorario.setPartida(dtpPartida.getValue());
		retornoHorario.setCodigoAeroporto(txtCodigoAeroporto.getText());
		return retornoHorario;
	}
	
	private boolean validarCampos() {
		if (!txtId.getText().equals("") && !txtCodigo.getText().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	private void realizarInsert(Horario horario) {
		//puxar insert da controller
	}
	
	private void realizarUpdate(Horario horario) {
		//puxar update
	}

	private void realizarDelete(Long horarioId) {
		//puxar delete
	}

}
