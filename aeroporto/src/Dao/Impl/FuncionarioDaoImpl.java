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

public class FuncionarioDaoImpl implements FuncionarioDao{

	public FuncionarioDaoImpl() {
	}

	
	@Override
	public void adicionar(Funcionario p) throws FuncionarioException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO Funcionario (id, nome, email, telefone, usuario, senha, dataNascimento, codigo, contaCorrente) " + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, p.getId());
			st.setString(2, p.getNome());
			st.setString(3, p.getEmail());
			st.setString(4,  p.getTelefone());
			st.setString(5, p.getUsuario());
			st.setString(6, p.getSenha());
			st.setDate(7, java.sql.Date.valueOf(p.getDataNascimento()));
			st.setString(8, p.getCodigo());
			st.setString(9, p.getContaCorrente());
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
				Funcionario p = new Funcionario();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setTelefone(rs.getString("telefone"));
				p.setUsuario(rs.getString("usuario"));
				p.setSenha(rs.getString("senha"));
				p.setDataNascimento(rs.getDate("nascimento").toLocalDate());
				p.setCodigo(rs.getString("codigo"));
				p.setContaCorrente(rs.getString("contaCorrente"));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FuncionarioException(e);
		}
		return lista;
	}
}
