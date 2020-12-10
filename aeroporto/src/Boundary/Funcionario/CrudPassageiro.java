package Boundary.Funcionario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Boundary.Mensagem;
import Control.PassageiroControl;
import Entity.Passageiro;
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

public class CrudPassageiro extends Application {
	
	private TableView<Passageiro> tblPassageiro;
	
	private TableColumn<Passageiro, String> tcId;
	private TableColumn<Passageiro, String> tcUsuario;
	private TableColumn<Passageiro, String> tcEmail;
	private TableColumn<Passageiro, String> tcNome;
	private TableColumn<Passageiro, String> tcDocumento;
	private TableColumn<Passageiro, String> tcNumeroCartao;
	private TableColumn<Passageiro, String> tcTelefone;
	private TableColumn<Passageiro, String> tcDataNascimento;
//	private TableColumn<Passageiro, String> tcEndereco;
	private TableColumn<Passageiro, String> tcSenha;
	
	private TextField txtId;
	private TextField txtUsuario;
	private TextField txtEmail;
	private TextField txtNome;
	private TextField txtDocumento;
	private TextField txtNumeroCartao;
	private TextField txtTelefone;
	private DatePicker dtpDataNascimento;
//	private TextField txtEndereco;
	private TextField txtSenha;
	
	private Button btnAdicionar;
	private Button btnAtualizar;
	private Button btnRemover;
	
	private Scene scene;
	
	private PassageiroControl control;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Passageiros");
		control = new PassageiroControl();
		
		iniciarAtributos();
		preencherTabela();
		definirLayout();
		
		stage.setResizable(false);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private void iniciarAtributos() {
		tblPassageiro = new TableView<Passageiro>();

	    tcId = new TableColumn<>("ID");
	    tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

	    tcUsuario = new TableColumn<>("Usuário");
	    tcUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
	    
	    tcEmail = new TableColumn<>("Email");
	    tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
	    
	    tcNome = new TableColumn<>("Nome");
	    tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	    
	    tcDocumento = new TableColumn<>("Documento");
	    tcDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
	    
	    tcNumeroCartao = new TableColumn<>("Número do cartão");
	    tcNumeroCartao.setCellValueFactory(new PropertyValueFactory<>("numeroCartao"));
	    
	    tcTelefone = new TableColumn<>("Telefone");
	    tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));	//mudar para telefone
	    
	    tcDataNascimento = new TableColumn<>("Data de nascimento");
	    tcDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
	    
//	    tcEndereco = new TableColumn<>("Endereço");
//	    tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
	    
	    tcSenha = new TableColumn<>("Senha");
	    tcSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
	    
	    txtId = new TextField();
	    txtUsuario = new TextField();
	    txtEmail = new TextField();
	    txtNome = new TextField();
	    txtDocumento = new TextField();
	    txtNumeroCartao = new TextField();
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
	    txtDocumento.setPromptText("Documento");
	    txtNumeroCartao.setPromptText("Número do cartão");
	    txtTelefone.setPromptText("Telefone");
	    dtpDataNascimento.setPromptText("Data de nascimento");
	    txtSenha.setPromptText("Senha");

	    tblPassageiro.getColumns().add(tcId);
	    tblPassageiro.getColumns().add(tcUsuario);
	    tblPassageiro.getColumns().add(tcEmail);
	    tblPassageiro.getColumns().add(tcNome);
	    tblPassageiro.getColumns().add(tcDocumento);
	    tblPassageiro.getColumns().add(tcNumeroCartao);
	    tblPassageiro.getColumns().add(tcTelefone);
	    tblPassageiro.getColumns().add(tcDataNascimento);
	    tblPassageiro.getColumns().add(tcSenha);
	    
	    btnAdicionar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            	if (validarCampos()) {
	            	Passageiro passageiro = transformarObjeto();
	            	realizarInsert(passageiro);
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
	            	Passageiro passageiro = transformarObjeto();
	            	realizarUpdate(passageiro);
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
	            	Passageiro passageiro = transformarObjeto();
	            	realizarDelete(passageiro.getId());
            	} else {
            		Stage newStage = new Stage();
            		Mensagem mensagem = new Mensagem("Escolha um passageiro para remover");
            		mensagem.start(newStage);
            	}
            	
            }
        });
	}
	
	private void preencherTabela() {
		control.setPassageiro(new Passageiro());
		try {
			control.pesquisarPorNome();
		} catch (PassageiroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    List<Passageiro> passageiros = control.getLista();
	    
	    for (Passageiro passageiro: passageiros) {
	    	tblPassageiro.getItems().add(passageiro);
	    }
	    
	    //Listener
	    tblPassageiro.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
	    	txtId.setText(String.valueOf(newSelection.getId()));
	    	txtUsuario.setText(newSelection.getUsuario());
	    	txtEmail.setText(newSelection.getEmail());
	    	txtNome.setText(newSelection.getNome());
	    	txtDocumento.setText(newSelection.getDocumento());
	    	txtNumeroCartao.setText(newSelection.getNumeroCartao());
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
	    
	    grid.add(tblPassageiro, 1, 1, 5, 1);
	    grid.add(txtId, 1, 2);
	    grid.add(txtUsuario, 2, 2);
	    grid.add(txtEmail, 3, 2);
	    grid.add(txtNome, 4, 2);
	    grid.add(txtDocumento, 5, 2);
	    grid.add(txtNumeroCartao, 1, 3);
	    grid.add(txtTelefone, 2, 3);
	    grid.add(dtpDataNascimento, 3, 3);
	    grid.add(txtSenha, 4, 3);
	    grid.add(btnAdicionar, 2, 4);
	    grid.add(btnAtualizar, 3, 4);
	    grid.add(btnRemover, 4, 4);

	    scene = new Scene(grid, 740, 510);
	}
	
	private Passageiro transformarObjeto() {
		Passageiro retornoPassageiro = new Passageiro();
		retornoPassageiro.setId(Long.parseLong(txtId.getText()));
		retornoPassageiro.setUsuario(txtUsuario.getText());
		retornoPassageiro.setNome(txtNome.getText());
		retornoPassageiro.setDocumento(txtDocumento.getText());
		retornoPassageiro.setNumeroCartao(txtNumeroCartao.getText());
		retornoPassageiro.setUsuario(txtTelefone.getText());
		retornoPassageiro.setUsuario(txtSenha.getText());
		
		return retornoPassageiro;
	}
	
	private boolean validarCampos() {
		if (!txtId.getText().equals("") && !txtUsuario.getText().equals("") &&
    			!txtNome.getText().equals("") && !txtDocumento.getText().equals("") &&
    			!txtNumeroCartao.getText().equals("") && !txtTelefone.getText().equals("") &&
    			!txtSenha.getText().equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	private void realizarInsert(Passageiro passageiro) {
		//puxar insert da controller
	}
	
	private void realizarUpdate(Passageiro passageiro) {
		//puxar update
	}

	private void realizarDelete(Long passageiroId) {
		//puxar delete
	}
	
}
