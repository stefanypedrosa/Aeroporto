package Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionSingleton;
import Dao.AviaoDao;
import Entity.Aviao;
import Exception.AviaoException;

public class AviaoDaoImpl implements AviaoDao{

	public AviaoDaoImpl() {
	}

	
	@Override
	public void adicionar(Aviao a) throws AviaoException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO Aviao (id, codigo, vagas, ciaAerea) " + 
			"VALUES (?, ?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, a.getId());
			st.setString(2, a.getCodigo());
			st.setInt(3, a.getVagas());
			st.setString(4,  a.getCiaAerea());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AviaoException(e);
		}

	}
	
	@Override
	public void atualizar(Aviao a) throws AviaoException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "UPDATE Aviao SET id = ?, codigo = ?, vagas = ?, ciaAerea = ? WHERE id = ?; "; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, a.getId());
			st.setString(2, a.getCodigo());
			st.setInt(3, a.getVagas());
			st.setString(4, a.getCiaAerea());
			st.setLong(5, a.getId());
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AviaoException(e);
		}

	}

	@Override
	public List<Aviao> pesquisarPorCodigo(String codigo) throws AviaoException {
		List<Aviao> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM Aviao WHERE codigo like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + codigo + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Aviao a = new Aviao();
				a.setId(rs.getLong("id"));
				a.setCodigo(rs.getString("codigo"));
				a.setVagas(rs.getInt("vagas"));
				a.setCiaAerea(rs.getString("ciaAerea"));
				lista.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AviaoException(e);
		}
		return lista;
	}
	
	
	@Override
	public List<Aviao> pesquisarTodos() throws AviaoException {
		List<Aviao> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM Aviao";
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Aviao a = new Aviao();
				a.setId(rs.getLong("id"));
				a.setCodigo(rs.getString("codigo"));
				a.setVagas(rs.getInt("vagas"));
				a.setCiaAerea(rs.getString("ciaAerea"));
				lista.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AviaoException(e);
		}
		return lista;
	}
	
	
	@Override
	public void remover(long id) throws AviaoException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "DELETE FROM Aviao WHERE id = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, id);
			st.executeQuery();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AviaoException(e);
		}
	}

}
