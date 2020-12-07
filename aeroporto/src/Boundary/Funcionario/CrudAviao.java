package Boundary.Funcionario;

import java.util.ArrayList;
import java.util.List;

import Entity.Aviao;
import javafx.application.Application;
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

public class CrudAviao extends Application {

	private TableView<Aviao> tblAviao;
	
	private TableColumn<Aviao, String> tcId;
	private TableColumn<Aviao, String> tcCodigo;
	private TableColumn<Aviao, String> tcVagas;
	
	private TextField txtId;
	private TextField txtCodigo;
	private TextField txtVagas;
	
	private Button btnAdicionar;
	private Button btnAtualizar;
	private Button btnRemover;
		
	private Scene scene;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Avi�es");
		
		iniciarAtributos();
		preencherTabela();
		definirLayout();
		
		stage.setResizable(false);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private void iniciarAtributos() {
		tblAviao = new TableView();

	    tcId = new TableColumn<>("ID");
	    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    tcCodigo = new TableColumn<>("C�digo");
	    tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    
	    tcVagas = new TableColumn<>("Vagas");
	    tcVagas.setCellValueFactory(new PropertyValueFactory<>("vagas"));
	    
	    txtId = new TextField();
	    txtCodigo = new TextField();
	    txtVagas = new TextField();
	    btnAdicionar = new Button("Adicionar");
	    btnAtualizar = new Button("Atualizar");
	    btnRemover = new Button("Remover");
	    
	    txtId.setPromptText("ID");
	    txtCodigo.setPromptText("C�digo");
	    txtVagas.setPromptText("Vagas");
	    
	    tcId.prefWidthProperty().bind(tblAviao.widthProperty().divide(3));
	    tcCodigo.prefWidthProperty().bind(tblAviao.widthProperty().divide(3));
	    tcVagas.prefWidthProperty().bind(tblAviao.widthProperty().divide(3));

	    tblAviao.getColumns().add(tcId);
	    tblAviao.getColumns().add(tcCodigo);
	    tblAviao.getColumns().add(tcVagas);
	}
	
	private void preencherTabela() {
	    List<Aviao> avioes = new ArrayList();	//j� puxar os valores do getAll aqui

	    //valores de teste -- come�o
	    Aviao teste1 = new Aviao();
	    Aviao teste2 = new Aviao();
	    
	    teste1.setId(1);
	    teste1.setCodigo("teste1");
	    teste1.setVagas(10);
	    
	    teste2.setId(2);
	    teste2.setCodigo("teste2");
	    teste2.setVagas(25);
	    
	    avioes.add(teste1);
	    avioes.add(teste2);
	    //valores de teste -- fim
	    
	    for (Aviao aviao: avioes) {
	    	tblAviao.getItems().add(aviao);
	    }
	    
	    //Listener
	    tblAviao.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	txtId.setText(String.valueOf(newSelection.getId()));
	    	txtCodigo.setText(newSelection.getCodigo());
	    	txtVagas.setText(String.valueOf(newSelection.getVagas()));
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
	    grid.add(btnAdicionar, 2, 3);
	    grid.add(btnAtualizar, 3, 3);
	    grid.add(btnRemover, 4, 3);

	    scene = new Scene(grid, 420, 480);
	}
	
}
