package Dao;

import java.util.List;

import Entity.Passageiro;
import Exception.PassageiroException;

public interface PassageiroDao {
	void adicionar(Passageiro p) throws PassageiroException;
	List<Passageiro> pesquisarPorNome(String nome) throws PassageiroException;
}
