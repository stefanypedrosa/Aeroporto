package Boundary.Funcionario;

import java.util.List;

import Boundary.Mensagem;
import Control.AviaoControl;
import Entity.Aviao;
import Exception.AviaoException;
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

public class CrudAviao extends Application {

	private TableView<Aviao> tblAviao;
	
	private TableColumn<Aviao, String> tcId;
	private TableColumn<Aviao, String> tcCodigo;
	private TableColumn<Aviao, String> tcVagas;
	private TableColumn<Aviao, String> tcCiaAerea;
	
	private TextField txtId;
	private TextField txtCodigo;
	private TextField txtVagas;
	private TextField txtCiaAerea;
	
	private Button btnAdicionar;
	private Button btnAtualizar;
	private Button btnRemover;
		
	private Scene scene;
	
	private AviaoControl control;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Avi�es");
		
		control = new AviaoControl();
		
		iniciarAtributos();
		preencherTabela();
		adicionarListener();
		definirLayout();
		
		stage.setResizable(false);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private void iniciarAtributos() {
		tblAviao = new TableView<Aviao>();

	    tcId = new TableColumn<>("ID");
	    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    tcCodigo = new TableColumn<>("C�digo");
	    tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    
	    tcVagas = new TableColumn<>("Vagas");
	    tcVagas.setCellValueFactory(new PropertyValueFactory<>("vagas"));
	    
	    tcCiaAerea = new TableColumn<>("Cia Aerea");
	    tcCiaAerea.setCellValueFactory(new PropertyValueFactory<>("ciaAerea"));
	    
	    txtId = new TextField();
	    txtCodigo = new TextField();
	    txtVagas = new TextField();
	    txtCiaAerea = new TextField();
	    btnAdicionar = new Button("Adicionar");
	    btnAtualizar = new Button("Atualizar");
	    btnRemover = new Button("Remover");
	    
	    txtId.setPromptText("ID");
	    txtCodigo.setPromptText("C�digo");
	    txtVagas.setPromptText("Vagas");
	    txtCiaAerea.setPromptText("Cia Aerea");
	    
	    tcId.prefWidthProperty().bind(tblAviao.widthProperty().divide(3));
	    tcCodigo.prefWidthProperty().bind(tblAviao.widthProperty().divide(3));
	    tcVagas.prefWidthProperty().bind(tblAviao.widthProperty().divide(3));
	    tcCiaAerea.prefWidthProperty().bind(tblAviao.maxWidthProperty().divide(3));

	    tblAviao.getColumns().add(tcId);
	    tblAviao.getColumns().add(tcCodigo);
	    tblAviao.getColumns().add(tcVagas);
	    tblAviao.getColumns().add(tcCiaAerea);
	    
        btnAdicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	if (validarCampos()) {
	            	Aviao aviao = transformarObjeto();
	            	realizarInsert(aviao);
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
	            	Aviao aviao = transformarObjeto();
	            	realizarUpdate(aviao);
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
	            	Aviao aviao = transformarObjeto();
	            	realizarDelete(aviao.getId());
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Escolha um avi�o para remover");
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
		List<Aviao> avioes = control.getLista();
		
		tblAviao.getItems().clear();
	    
	    for (Aviao aviao: avioes) {
	    	tblAviao.getItems().add(aviao);
	    }
	    
	}
	
	private void adicionarListener() {
		//Listener
	    tblAviao.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	txtId.setText(String.valueOf(newSelection.getId()));
	    	txtCodigo.setText(newSelection.getCodigo());
	    	txtVagas.setText(String.valueOf(newSelection.getVagas()));
	    	txtCiaAerea.setText(String.valueOf(newSelection.getCiaAerea()));
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
	    
	    grid.add(tblAviao, 1, 1, 5, 1);
	    grid.add(txtId, 2, 2);
	    grid.add(txtCodigo, 3, 2);
	    grid.add(txtVagas, 4, 2);
	    grid.add(txtCiaAerea, 5, 2);
	    grid.add(btnAdicionar, 2, 3);
	    grid.add(btnAtualizar, 3, 3);
	    grid.add(btnRemover, 4, 3);

	    scene = new Scene(grid, 420, 480);
	}
	
	private Aviao transformarObjeto() {
		Aviao retornoAviao = new Aviao();
		retornoAviao.setId(Long.parseLong(txtId.getText()));
		retornoAviao.setCodigo(txtCodigo.getText());
		retornoAviao.setVagas(Integer.parseInt(txtVagas.getText()));
		retornoAviao.setCiaAerea(txtCiaAerea.getText());
		
		return retornoAviao;
	}
	
	private boolean validarCampos() {
		if (!txtId.getText().equals("") && !txtCodigo.getText().equals("") && !txtVagas.getText().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	private void realizarInsert(Aviao aviao) {
		control.setAviao(aviao);
		try {
			control.adicionar();
		} catch (AviaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		preencherTabela();
	}
	
	private void realizarUpdate(Aviao aviao) {
		control.setAviao(aviao);
		try {
			control.atualizar();
		} catch (AviaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		preencherTabela();
	}

	private void realizarDelete(Long aviaoId) {
		Aviao a = new Aviao();
		a.setId(aviaoId);
		control.setAviao(a);
		try {
			control.remover();
		} catch(Exception ex) {
			
		}
		
		preencherTabela();
	}
	
}
