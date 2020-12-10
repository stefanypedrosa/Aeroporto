package Control;

import java.time.LocalDate;
import java.util.List;

import Dao.PassageiroDao;
import Dao.Impl.PassageiroDaoImpl;
import Entity.Passageiro;
import Exception.PassageiroException;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PassageiroControl {

	private ObservableList<Passageiro> lista = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private StringProperty nomeProperty = new SimpleStringProperty("João");
	private StringProperty telefoneProperty = new SimpleStringProperty("(11)    -    ");
	private StringProperty emailProperty = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> dataNascimentoProperty = new SimpleObjectProperty<>(LocalDate.now());
	private StringProperty usuarioProperty = new SimpleStringProperty("");
	private StringProperty senhaProperty = new SimpleStringProperty("");
	private StringProperty documentoProperty = new SimpleStringProperty("");
	private StringProperty numeroCartaoProperty = new SimpleStringProperty("");
	private PassageiroDao PassageiroDAO = new PassageiroDaoImpl();
	
	public Passageiro getPassageiro() { 
		Passageiro p = new Passageiro();
		p.setId(idProperty.get());
		p.setNome(nomeProperty.get());
		p.setTelefone(telefoneProperty.get());
		p.setEmail(emailProperty.get());
		p.setDataNascimento(dataNascimentoProperty.get());
		p.setUsuario(usuarioProperty.get());
		p.setSenha(senhaProperty.get());
		p.setDocumento(documentoProperty.get());
		p.setNumeroCartao(numeroCartaoProperty.get());
		return p;
	}
	
	public void setPassageiro(Passageiro p) { 
		if (p != null)  { 
			idProperty.set(p.getId());
			nomeProperty.set(p.getNome());
			telefoneProperty.set(p.getTelefone());
			emailProperty.set(p.getEmail());
			dataNascimentoProperty.set(p.getDataNascimento());
			usuarioProperty.set(p.getUsuario());
			senhaProperty.set(p.getSenha());
			documentoProperty.set(p.getDocumento());
			numeroCartaoProperty.set(p.getNumeroCartao());
		}
	}
	
	public void adicionar() throws PassageiroException { 
		PassageiroDAO.adicionar(getPassageiro());
	}
	
	public void atualizar() throws PassageiroException { 
		PassageiroDAO.atualizar(getPassageiro());
	}
	
	public void pesquisarPorNome() throws PassageiroException {
		List<Passageiro> Passageiros = PassageiroDAO.pesquisarPorNome(this.getNomeProperty().get());
		this.lista.clear();
		this.lista.addAll(Passageiros);
	}
	
	public void pesquisarTodos() throws PassageiroException {
		List<Passageiro> Passageiros = PassageiroDAO.pesquisarTodos();
		this.lista.clear();
		this.lista.addAll(Passageiros);
	}
	
	public void remover() throws PassageiroException {
		PassageiroDAO.remover(getIdProperty().get());
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
	
	public StringProperty getDocumentoProperty() {
		return documentoProperty;
	}
	
	public StringProperty getNumeroCartaoProperty() {
		return numeroCartaoProperty;
	}
	
	public ObservableList<Passageiro> getLista() {
		return lista;
	}

}
