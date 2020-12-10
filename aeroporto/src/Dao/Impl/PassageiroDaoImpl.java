package Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionSingleton;
import Dao.PassageiroDao;
import Entity.Passageiro;
import Exception.PassageiroException;

public class PassageiroDaoImpl implements PassageiroDao{

	public PassageiroDaoImpl() {
	}

	
	@Override
	public void adicionar(Passageiro p) throws PassageiroException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO Passageiro (id, nome, email, telefone, usuario, senha, dataNascimento, documento, numeroCartao) " + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, p.getId());
			st.setString(2, p.getNome());
			st.setString(3, p.getEmail());
			st.setString(4,  p.getTelefone());
			st.setString(5, p.getUsuario());
			st.setString(6, p.getSenha());
			st.setDate(7, java.sql.Date.valueOf(p.getDataNascimento()));
			st.setString(8, p.getDocumento());
			st.setString(9, p.getNumeroCartao());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PassageiroException(e);
		}

	}
	

	@Override
	public void atualizar(Passageiro p) throws PassageiroException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "UPDATE Passageiro SET id = ?, nome = ?, email = ?, telefone = ?, usuario = ?, senha = ?, dataNascimento = ?, documento = ?, numeroCartao = ?; "; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, p.getId());
			st.setString(2, p.getNome());
			st.setString(3, p.getEmail());
			st.setString(4,  p.getTelefone());
			st.setString(5, p.getUsuario());
			st.setString(6, p.getSenha());
			st.setDate(7, java.sql.Date.valueOf(p.getDataNascimento()));
			st.setString(8, p.getDocumento());
			st.setString(9, p.getNumeroCartao());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PassageiroException(e);
		}

	}

	@Override
	public List<Passageiro> pesquisarPorNome(String nome) throws PassageiroException {
		List<Passageiro> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM Passageiro WHERE nome like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + nome + "%");
			System.out.println(nome);
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Passageiro p = new Passageiro();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setTelefone(rs.getString("telefone"));
				p.setUsuario(rs.getString("usuario"));
				p.setSenha(rs.getString("senha"));
				p.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
				p.setDocumento(rs.getString("documento"));
				p.setNumeroCartao(rs.getString("numeroCartao"));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PassageiroException(e);
		}
		return lista;
	}
	
	@Override
	public List<Passageiro> pesquisarTodos() throws PassageiroException {
		List<Passageiro> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM Passageiro";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Passageiro p = new Passageiro();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setTelefone(rs.getString("telefone"));
				p.setUsuario(rs.getString("usuario"));
				p.setSenha(rs.getString("senha"));
				p.setDataNascimento(rs.getDate("dataNascimento").toLocalDate());
				p.setDocumento(rs.getString("documento"));
				p.setNumeroCartao(rs.getString("numeroCartao"));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PassageiroException(e);
		}
		return lista;
	}
	
	
	

}
