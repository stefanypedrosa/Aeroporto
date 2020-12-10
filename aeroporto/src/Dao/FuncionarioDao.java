package Dao;

import java.util.List;

import Entity.Funcionario;
import Exception.FuncionarioException;

public interface FuncionarioDao {
	void adicionar(Funcionario f) throws FuncionarioException;
	List<Funcionario> pesquisarPorNome(String nome) throws FuncionarioException;
	List<Funcionario> pesquisarTodos() throws FuncionarioException;
	void atualizar(Funcionario f) throws FuncionarioException;
}
