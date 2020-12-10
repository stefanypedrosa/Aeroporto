package Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionSingleton;
import Dao.FuncionarioDao;
import Entity.Funcionario;
import Exception.FuncionarioException;
import Exception.PassageiroException;

public class FuncionarioDaoImpl implements FuncionarioDao{

	public FuncionarioDaoImpl() {
	}

	
	@Override
	public void adicionar(Funcionario f) throws FuncionarioException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO Funcionario (id, nome, email, telefone, usuario, senha, dataNascimento, codigo, contaCorrente) " + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, f.getId());
			st.setString(2, f.getNome());
			st.setString(3, f.getEmail());
			st.setString(4,  f.getTelefone());
			st.setString(5, f.getUsuario());
			st.setString(6, f.getSenha());
			st.setDate(7, java.sql.Date.valueOf(f.getDataNascimento()));
			st.setString(8, f.getCodigo());
			st.setString(9, f.getContaCorrente());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}

	}
	
	@Override
	public void atualizar(Funcionario f) throws FuncionarioException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "UPDATE Passageiro SET id = ?, nome = ?, email = ?, telefone = ?, usuario = ?, senha = ?, dataNascimento = ?, documento = ?, numeroCartao = ?; "; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, f.getId());
			st.setString(2, f.getNome());
			st.setString(3, f.getEmail());
			st.setString(4,  f.getTelefone());
			st.setString(5, f.getUsuario());
			st.setString(6, f.getSenha());
			st.setDate(7, java.sql.Date.valueOf(f.getDataNascimento()));
			st.setString(8, f.getCodigo());
			st.setString(9, f.getContaCorrente());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}

	}
	

	@Override
	public List<Funcionario> pesquisarPorNome(String nome) throws FuncionarioException {
		List<Funcionario> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM Funcionario WHERE nome like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Funcionario f = new Funcionario();
				f.setId(rs.getLong("id"));
				f.setNome(rs.getString("nome"));
				f.setEmail(rs.getString("email"));
				f.setTelefone(rs.getString("telefone"));
				f.setUsuario(rs.getString("usuario"));
				f.setSenha(rs.getString("senha"));
				f.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
				f.setCodigo(rs.getString("codigo"));
				f.setContaCorrente(rs.getString("contaCorrente"));
				lista.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}
		return lista;
	}
	
	@Override
	public List<Funcionario> pesquisarTodos() throws FuncionarioException {
		List<Funcionario> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM Funcionario";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Funcionario f = new Funcionario();
				f.setId(rs.getLong("id"));
				f.setNome(rs.getString("nome"));
				f.setEmail(rs.getString("email"));
				f.setTelefone(rs.getString("telefone"));
				f.setUsuario(rs.getString("usuario"));
				f.setSenha(rs.getString("senha"));
				f.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
				f.setCodigo(rs.getString("codigo"));
				f.setContaCorrente(rs.getString("contaCorrente"));
				lista.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}
		return lista;
	}
	
	@Override
	public void remover(long id) throws FuncionarioException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "DELETE FROM Funcionario WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeQuery();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}
	}
	
}
