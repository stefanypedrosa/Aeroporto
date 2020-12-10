package Dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Connection.ConnectionSingleton;
import Dao.BilheteDao;
import Entity.Bilhete;
import Exception.BilheteException;

public class BilheteDaoImpl implements BilheteDao {

	public BilheteDaoImpl() {
	}

	
	@Override
	public void adicionar(Bilhete b) throws BilheteException {
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "INSERT INTO Bilhete (id, numero, assento, pesoBagagem, situacaoBilhete, partida, chegada, codigoAeroporto) " + 
			"VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; 
			PreparedStatement st = con.prepareStatement(sql);
			st.setLong(1, b.getId());
			st.setInt(2, b.getNumero());
			st.setString(3, b.getAssento());
			st.setDouble(4,  b.getPesoBagagem());
			st.setString(5, b.getSituacaoBilhete().getId());
			st.setDate(6, java.sql.Date.valueOf(b.getPartida()));
			st.setDate(7, java.sql.Date.valueOf(b.getChegada()));
			st.setString(8, b.getCodigoAeroporto());			
			
			st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BilheteException(e);
		}

	}

	@Override
	public List<Bilhete> pesquisarPorNumero(int numero) throws BilheteException {
		List<Bilhete> lista = new ArrayList<>();
		try {
			Connection con = ConnectionSingleton.instancia().connection();
			String sql = "SELECT * FROM Bilhete WHERE nome like ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + numero + "%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) { 
				Bilhete b = new Bilhete();
				b.setId(rs.getLong("id"));
				b.setNumero(rs.getInt("numero"));
				b.setAssento(rs.getString("assento"));
				b.setPesoBagagem(rs.getDouble("pesoBagagem"));
				b.setPartida(rs.getDate("partida").toLocalDate());
				b.setChegada(rs.getDate("chegada").toLocalDate());
				b.setCodigoAeroporto(rs.getString("codigoAeroporto"));
				lista.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BilheteException(e);
		}
		return lista;
	}


}
