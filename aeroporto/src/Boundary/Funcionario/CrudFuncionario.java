package Boundary.Funcionario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Boundary.Mensagem;
import Control.FuncionarioControl;
import Entity.Funcionario;
import Entity.Passageiro;
import Exception.FuncionarioException;
import Exception.PassageiroException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CrudFuncionario extends Application {

	private TableView<Funcionario> tblFuncionario;
	
	private TableColumn<Funcionario, String> tcId;
	private TableColumn<Funcionario, String> tcUsuario;
	private TableColumn<Funcionario, String> tcEmail;
	private TableColumn<Funcionario, String> tcNome;
	private TableColumn<Funcionario, String> tcCodigo;
	private TableColumn<Funcionario, String> tcContaCorrente;
	private TableColumn<Funcionario, String> tcTelefone;
	private TableColumn<Funcionario, String> tcDataNascimento;
	private TableColumn<Funcionario, String> tcSenha;
	
	TextField txtId;
	TextField txtUsuario;
	TextField txtEmail;
	TextField txtNome;
	TextField txtCodigo;
	TextField txtContaCorrente;
	TextField txtTelefone;
	DatePicker dtpDataNascimento;
	TextField txtSenha;
	
	Button btnAdicionar;
	Button btnAtualizar;
	Button btnRemover;
	
	Scene scene;
	
	FuncionarioControl control;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Funcionários");
		
		control = new FuncionarioControl();
		
		iniciarAtributos();
		adicionarListener();
		preencherTabela();
		definirLayout();
		
		stage.setResizable(false);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private void iniciarAtributos() {
		tblFuncionario = new TableView<Funcionario>();

	    tcId = new TableColumn<>("ID");
	    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    tcUsuario = new TableColumn<>("Usuário");
	    tcUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
	    
	    tcEmail = new TableColumn<>("Email");
	    tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
	    
	    tcNome = new TableColumn<>("Nome");
	    tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    
	    tcCodigo = new TableColumn<>("Código");
	    tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
	    
	    tcContaCorrente = new TableColumn<>("Conta-corrente");
	    tcContaCorrente.setCellValueFactory(new PropertyValueFactory<>("contaCorrente"));
	    
	    tcTelefone = new TableColumn<>("Telefone");
	    tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));	//mudar para telefone
	    
	    tcDataNascimento = new TableColumn<>("Data de nascimento");
	    tcDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
	    
	    tcSenha = new TableColumn<>("Senha");
	    tcSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
	    
	    txtId = new TextField();
	    txtUsuario = new TextField();
	    txtEmail = new TextField();
	    txtNome = new TextField();
	    txtCodigo = new TextField();
	    txtContaCorrente = new TextField();
	    txtTelefone = new TextField();
	    dtpDataNascimento = new DatePicker();
	    txtSenha = new TextField();
	    btnAdicionar = new Button("Adicionar");
	    btnAtualizar = new Button("Atualizar");
	    btnRemover = new Button("Remover");
	    
	    txtId.setPromptText("ID");
	    txtUsuario.setPromptText("Usuário");
	    txtEmail.setPromptText("Email");
	    txtNome.setPromptText("Nome");
	    txtCodigo.setPromptText("Código");
	    txtContaCorrente.setPromptText("Conta-Corrente");
	    txtTelefone.setPromptText("Telefone");
	    dtpDataNascimento.setPromptText("Data de nascimento");
	    txtSenha.setPromptText("Senha");

	    tblFuncionario.getColumns().add(tcId);
	    tblFuncionario.getColumns().add(tcUsuario);
	    tblFuncionario.getColumns().add(tcEmail);
	    tblFuncionario.getColumns().add(tcNome);
	    tblFuncionario.getColumns().add(tcCodigo);
	    tblFuncionario.getColumns().add(tcContaCorrente);
	    tblFuncionario.getColumns().add(tcTelefone);
	    tblFuncionario.getColumns().add(tcDataNascimento);
	    tblFuncionario.getColumns().add(tcSenha);
	    
	    btnAdicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	if (validarCampos()) {
	            	Funcionario funcionario = transformarObjeto();
	            	realizarInsert(funcionario);
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
	            	Funcionario funcionario = transformarObjeto();
	            	realizarUpdate(funcionario);
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
	            	Funcionario funcionario = transformarObjeto();
	            	realizarDelete(funcionario.getId());
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Escolha um funcionário para remover");
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
	    List<Funcionario> funcionarios = control.getLista();
	    
	    tblFuncionario.getItems().clear();
	
	    for (Funcionario funcionario: funcionarios) {
	    	tblFuncionario.getItems().add(funcionario);
	    }
	}
	
	private void adicionarListener() {
		//Listener
	    tblFuncionario.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	txtId.setText(String.valueOf(newSelection.getId()));
	    	txtUsuario.setText(newSelection.getUsuario());
	    	txtEmail.setText(newSelection.getEmail());
	    	txtNome.setText(newSelection.getNome());
	    	txtCodigo.setText(newSelection.getCodigo());
	    	txtContaCorrente.setText(newSelection.getContaCorrente());
	    	txtTelefone.setText(newSelection.getTelefone());		//mudar para telefone
	    	dtpDataNascimento.setValue(newSelection.getDataNascimento());
	    	txtSenha.setText(newSelection.getSenha());
	    });
	}
	
	private void definirLayout() {
	    GridPane grid = new GridPane();
	    
	    grid.setHgap(5);
	    grid.setVgap(5);
	    
	    btnAdicionar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    btnAtualizar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    btnRemover.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    
	    grid.add(tblFuncionario, 1, 1, 5, 1);
	    grid.add(txtId, 1, 2);
	    grid.add(txtUsuario, 2, 2);
	    grid.add(txtEmail, 3, 2);
	    grid.add(txtNome, 4, 2);
	    grid.add(txtCodigo, 5, 2);
	    grid.add(txtContaCorrente, 1, 3);
	    grid.add(txtTelefone, 2, 3);
	    grid.add(dtpDataNascimento, 3, 3);
	    grid.add(txtSenha, 4, 3);
	    grid.add(btnAdicionar, 2, 4);
	    grid.add(btnAtualizar, 3, 4);
	    grid.add(btnRemover, 4, 4);

	    scene = new Scene(grid, 725, 510);
	}
	
	private Funcionario transformarObjeto() {
		Funcionario retornoFuncionario = new Funcionario();
		retornoFuncionario.setId(Long.parseLong(txtId.getText()));
		retornoFuncionario.setUsuario(txtUsuario.getText());
		retornoFuncionario.setNome(txtNome.getText());
		retornoFuncionario.setEmail(txtEmail.getText());
		retornoFuncionario.setCodigo(txtCodigo.getText());
		retornoFuncionario.setContaCorrente(txtContaCorrente.getText());
		retornoFuncionario.setTelefone(txtTelefone.getText());
		retornoFuncionario.setSenha(txtSenha.getText());
		retornoFuncionario.setDataNascimento(dtpDataNascimento.getValue());
		
		return retornoFuncionario;
	}
	
	private boolean validarCampos() {
		if (!txtId.getText().equals("") && !txtUsuario.getText().equals("") &&
    			!txtNome.getText().equals("") && !txtCodigo.getText().equals("") &&
    			!txtContaCorrente.getText().equals("") && !txtTelefone.getText().equals("") &&
    			!txtSenha.getText().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	private void realizarInsert(Funcionario funcionario) {
		FuncionarioControl control = new FuncionarioControl();
		control.setFuncionario(funcionario);
		try {
			control.adicionar();
		} catch (FuncionarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		preencherTabela();
	}
	
	private void realizarUpdate(Funcionario funcionario) {
		FuncionarioControl control = new FuncionarioControl();
		control.setFuncionario(funcionario);
		try {
			control.atualizar();
		} catch (FuncionarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		preencherTabela();
	}

	private void realizarDelete(Long funcionarioId) {
		Funcionario f = new Funcionario();
		f.setId(funcionarioId);
		control.setFuncionario(f);
		try {
			control.remover();
		} catch(Exception ex) {
			
		}
		
		preencherTabela();
	}
	
}
