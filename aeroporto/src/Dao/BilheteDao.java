package Dao;

import java.util.List;

import Entity.Bilhete;
import Exception.BilheteException;

public interface BilheteDao {

	void adicionar(Bilhete b) throws BilheteException;
	List<Bilhete> pesquisarPorNumero(int b) throws BilheteException;
	List<Bilhete> pesquisarTodos() throws BilheteException;
	void atualizar(Bilhete b) throws BilheteException;
	void remover(long id) throws BilheteException;
}
