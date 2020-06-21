package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.InformacijeLijeci;
import model.dto.InformacijePregleda;
import util.ConnectionPool;

public class InformacijeLijeciDao {
	

	private String getAllByIdDrustva = "select * from informacije_o_liječenju where IdDruštva = ?";
	
	
	public List<InformacijeLijeci> getByIdDrustva(int IdDrustva) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getAllByIdDrustva);
			ps.setInt(1, IdDrustva);
			result = ps.executeQuery();
			List<InformacijeLijeci> infoLijeci = new LinkedList<InformacijeLijeci>();
			while (result.next()) {
				infoLijeci.add(new InformacijeLijeci(result.getInt("IdLiječenja"),result.getInt("IdDruštva"),result.getDate("DatumLiječenja"),result.getString("VrstaLijeka"),result.getString("Zaposleni")));
			}
			return infoLijeci;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}

}
