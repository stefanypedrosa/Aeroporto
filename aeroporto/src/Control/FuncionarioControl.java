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
	private StringProperty nomeProperty = new SimpleStringProperty("João");
	private StringProperty telefoneProperty = new SimpleStringProperty("(11)    -    ");
	private StringProperty emailProperty = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataNascimentoProperty = new SimpleObjectProperty<>(LocalDate.now());
	private StringProperty usuarioProperty = new SimpleStringProperty("");
	private StringProperty senhaProperty = new SimpleStringProperty("");
	private StringProperty codigoProperty = new SimpleStringProperty("");
	private StringProperty contaCorrenteProperty = new SimpleStringProperty("");
	private FuncionarioDao FuncionarioDAO = new FuncionarioDaoImpl();
	
	public Funcionario getFuncionario() { 
		Funcionario p = new Funcionario();
		p.setId(idProperty.get());
		p.setNome(nomeProperty.get());
		p.setTelefone(telefoneProperty.get());
		p.setEmail(emailProperty.get());
		p.setDataNascimento(dataNascimentoProperty.get());
		p.setUsuario(usuarioProperty.get());
		p.setSenha(senhaProperty.get());
		p.setCodigo(codigoProperty.get());
		p.setContaCorrente(contaCorrenteProperty.get());
		return p;
	}
	
	public void setFuncionario(Funcionario p) { 
		if (p != null)  { 
			idProperty.set(p.getId());
			nomeProperty.set(p.getNome());
			telefoneProperty.set(p.getTelefone());
			emailProperty.set(p.getEmail());
			dataNascimentoProperty.set(p.getDataNascimento());
			usuarioProperty.set(p.getUsuario());
			senhaProperty.set(p.getSenha());
			codigoProperty.set(p.getCodigo());
			contaCorrenteProperty.set(p.getContaCorrente());
		}
	}
	
	public void adicionar() throws FuncionarioException { 
		FuncionarioDAO.adicionar(getFuncionario());
	}
	
	public void pesquisarPorNome() throws FuncionarioException {
		List<Funcionario> Funcionarios = FuncionarioDAO.pesquisarPorNome(this.getNomeProperty().get());
		this.lista.clear();
		this.lista.addAll(Funcionarios);
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
