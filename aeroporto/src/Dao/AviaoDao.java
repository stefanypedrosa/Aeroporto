package Dao;

import java.util.List;

import Entity.Aviao;
import Exception.AviaoException;

public interface AviaoDao {
	void adicionar(Aviao a) throws AviaoException;
	void atualizar(Aviao a) throws AviaoException;
	void remover(long id) throws AviaoException;
	List<Aviao> pesquisarPorCodigo(String nome) throws AviaoException;
	List<Aviao> pesquisarTodos() throws AviaoException;
}
