package Dao;

import java.util.List;

import Entity.Aviao;
import Exception.AviaoException;

public interface AviaoDao {

	void adicionar(Aviao a) throws AviaoException;
	List<Aviao> pesquisarPorNome(String nome) throws AviaoException;
	void remover(long id) throws AviaoException;
}
