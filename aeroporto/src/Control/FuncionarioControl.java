package Control;

import java.time.LocalDate;
import java.util.List;

import Dao.FuncionarioDao;
import Dao.Impl.FuncionarioDaoImpl;
import Entity.Funcionario;
import Exception.FuncionarioException;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuncionarioControl {

private ObservableList<Funcionario> lista = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private StringProperty nomeProperty = new SimpleStringProperty("Jo�o");
	private StringProperty telefoneProperty = new SimpleStringProperty("(11)    -    ");
	private StringProperty emailProperty = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataNascimentoProperty = new SimpleObjectProperty<>(LocalDate.now());
	private StringProperty usuarioProperty = new SimpleStringProperty("");
	private StringProperty senhaProperty = new SimpleStringProperty("");
	private StringProperty codigoProperty = new SimpleStringProperty("");
	private StringProperty contaCorrenteProperty = new SimpleStringProperty("");
	private FuncionarioDao FuncionarioDAO = new FuncionarioDaoImpl();
	
	public Funcionario getFuncionario() { 
		Funcionario f = new Funcionario();
		f.setId(idProperty.get());
		f.setNome(nomeProperty.get());
		f.setTelefone(telefoneProperty.get());
		f.setEmail(emailProperty.get());
		f.setDataNascimento(dataNascimentoProperty.get());
		f.setUsuario(usuarioProperty.get());
		f.setSenha(senhaProperty.get());
		f.setCodigo(codigoProperty.get());
		f.setContaCorrente(contaCorrenteProperty.get());
		return f;
	}
	
	public void setFuncionario(Funcionario f) { 
		if (f != null)  { 
			idProperty.set(f.getId());
			nomeProperty.set(f.getNome());
			telefoneProperty.set(f.getTelefone());
			emailProperty.set(f.getEmail());
			dataNascimentoProperty.set(f.getDataNascimento());
			usuarioProperty.set(f.getUsuario());
			senhaProperty.set(f.getSenha());
			codigoProperty.set(f.getCodigo());
			contaCorrenteProperty.set(f.getContaCorrente());
		}
	}
	
	public void adicionar() throws FuncionarioException { 
		FuncionarioDAO.adicionar(getFuncionario());
	}
	
	public void atualizar() throws FuncionarioException { 
		FuncionarioDAO.atualizar(getFuncionario());
	}
	
	public void pesquisarPorNome() throws FuncionarioException {
		List<Funcionario> Funcionarios = FuncionarioDAO.pesquisarPorNome(this.getNomeProperty().get());
		this.lista.clear();
		this.lista.addAll(Funcionarios);
	}
	
	public void pesquisarTodos() throws FuncionarioException {
		List<Funcionario> Funcionarios = FuncionarioDAO.pesquisarTodos();
		this.lista.clear();
		this.lista.addAll(Funcionarios);
	}
	
	public void remover() throws FuncionarioException {
		FuncionarioDAO.remover(getIdProperty().get());
	}
	
	public LongProperty getIdProperty() {
		return idProperty;
	}
	public StringProperty getNomeProperty() {
		return nomeProperty;
	}
	public StringProperty getTelefoneProperty() {
		return telefoneProperty;
	}
	public StringProperty getEmailProperty() {
		return emailProperty;
	}
	public ObjectProperty<LocalDate> getDataNascimentoProperty() {
		return dataNascimentoProperty;
	}
	
	public StringProperty getUsuarioProperty() {
		return usuarioProperty;
	}
	
	public StringProperty getSenhaProperty() {
		return senhaProperty;
	}
	
	public StringProperty getCodigoProperty() {
		return codigoProperty;
	}
	
	public StringProperty getContaCorrenteProperty() {
		return contaCorrenteProperty;
	}
	
	public ObservableList<Funcionario> getLista() {
		return lista;
	}
}
