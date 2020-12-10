package Dao;

import java.util.List;

import Entity.Funcionario;
import Exception.FuncionarioException;

public interface FuncionarioDao {
	void adicionar(Funcionario p) throws FuncionarioException;
	List<Funcionario> pesquisarPorNome(String nome) throws FuncionarioException;
}
