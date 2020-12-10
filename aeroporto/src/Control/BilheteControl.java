package Control;

import java.time.LocalDate;
import java.util.List;

import Dao.BilheteDao;
import Dao.Impl.BilheteDaoImpl;
import Entity.Bilhete;
import Exception.BilheteException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BilheteControl {

private ObservableList<Bilhete> lista = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private IntegerProperty numeroProperty = new SimpleIntegerProperty(2365);
	private StringProperty assentoProperty = new SimpleStringProperty("A22");
	private DoubleProperty pesoBagagemProperty = new SimpleDoubleProperty();
	private StringProperty situacaoBilheteProperty = new SimpleStringProperty("");
	private ObjectProperty<LocalDate> partidaProperty = new SimpleObjectProperty<>(LocalDate.now());
	private ObjectProperty<LocalDate> chegadaProperty = new SimpleObjectProperty<>(LocalDate.now());
	private StringProperty codigoAeroportoProperty = new SimpleStringProperty("");
		
	private BilheteDao BilheteDAO = new BilheteDaoImpl();
	
	public Bilhete getBilhete() { 
		Bilhete b = new Bilhete();
		b.setId(idProperty.get());
		b.setNumero(numeroProperty.get());
		b.setAssento(assentoProperty.get());
		b.setPesoBagagem(pesoBagagemProperty.get());
		b.setPartida(partidaProperty.get());
		b.setChegada(chegadaProperty.get());
		b.setCodigoAeroporto(codigoAeroportoProperty.get());
		
		return b;
	}
	
	public void setBilhete(Bilhete b) { 
		if (b != null)  { 
			idProperty.set(b.getId());
			numeroProperty.set(b.getNumero());
			assentoProperty.set(b.getAssento());
			pesoBagagemProperty.set(b.getPesoBagagem());
			situacaoBilheteProperty.setValue(b.getSituacaoBilhete().getId());
			partidaProperty.set(b.getPartida());
			chegadaProperty.set(b.getChegada());
			codigoAeroportoProperty.set(b.getCodigoAeroporto());
		}
	}
	
	public void adicionar() throws BilheteException { 
		BilheteDAO.adicionar(getBilhete());
	}
	
	public void pesquisarPorNome() throws BilheteException {
		List<Bilhete> Bilhetes = BilheteDAO.pesquisarPorNumero(this.getNumeroProperty().get());
		this.lista.clear();
		this.lista.addAll(Bilhetes);
	}
	
	
	public LongProperty getIdProperty() {
		return idProperty;
	}

	public IntegerProperty getNumeroProperty() {
		return numeroProperty;
	}

	public StringProperty getAssentoProperty() {
		return assentoProperty;
	}

	public DoubleProperty getPesoBagagemProperty() {
		return pesoBagagemProperty;
	}

	public StringProperty getSituacaoBilheteProperty() {
		return situacaoBilheteProperty;
	}

	public ObjectProperty<LocalDate> getPartidaProperty() {
		return partidaProperty;
	}

	public ObjectProperty<LocalDate> getChegadaProperty() {
		return chegadaProperty;
	}

	public StringProperty getCodigoAeroportoProperty() {
		return codigoAeroportoProperty;
	}

	public BilheteDao getBilheteDAO() {
		return BilheteDAO;
	}

	public ObservableList<Bilhete> getLista() {
		return lista;
	}

}
