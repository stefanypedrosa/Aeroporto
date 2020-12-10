package Control;

import java.util.List;

import Dao.AviaoDao;
import Dao.Impl.AviaoDaoImpl;
import Entity.Aviao;
import Exception.AviaoException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AviaoControl {

private ObservableList<Aviao> lista = FXCollections.observableArrayList();
	
	private LongProperty idProperty = new SimpleLongProperty(0);
	private StringProperty codigoProperty = new SimpleStringProperty("AZ-777");
	private IntegerProperty vagasProperty = new SimpleIntegerProperty(50);
	private StringProperty ciaAereaProperty = new SimpleStringProperty("");
	
	private AviaoDao AviaoDAO = new AviaoDaoImpl();
	
	public Aviao getAviao() { 
		Aviao a = new Aviao();
		a.setId(idProperty.get());
		a.setCodigo(codigoProperty.get());
		a.setVagas(vagasProperty.get());
		a.setCiaAerea(ciaAereaProperty.get());
		
		return a;
	}
	
	public void setAviao(Aviao a) { 
		if (a != null)  { 
			idProperty.set(a.getId());
			codigoProperty.set(a.getCodigo());
			vagasProperty.set(a.getVagas());
			ciaAereaProperty.set(a.getCiaAerea());
		}
	}
	
	public void adicionar() throws AviaoException { 
		AviaoDAO.adicionar(getAviao());
	}
	
	public void pesquisarPorNome() throws AviaoException {
		List<Aviao> Aviaos = AviaoDAO.pesquisarPorNome(this.getCodigoProperty().get());
		this.lista.clear();
		this.lista.addAll(Aviaos);
	}
	
	public void remover() throws AviaoException {
		AviaoDAO.remover(getIdProperty().get());
	}
	
	public LongProperty getIdProperty() {
		return idProperty;
	}

	public StringProperty getCodigoProperty() {
		return codigoProperty;
	}

	public IntegerProperty getVagasProperty() {
		return vagasProperty;
	}

	public StringProperty getCiaAereaProperty() {
		return ciaAereaProperty;
	}

	public AviaoDao getAviaoDAO() {
		return AviaoDAO;
	}

	public ObservableList<Aviao> getLista() {
		return lista;
	}

}
