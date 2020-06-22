package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.dto.Drustvo;
import model.dto.Trgovina;
import util.ConnectionPool;

public class TrgovinaDao {
	
	private String getByIdKupcaQuery = "select * from trgovina where KUPAC_IdKupca = ? ";
	
	public Trgovina getByIdKupca(int IdKupca) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdKupcaQuery);
			ps.setInt(1, IdKupca);
			result = ps.executeQuery();
			if(result.next()) {
				
				return new Trgovina(result.getInt("KUPAC_IdKupca"),result.getString("Naziv"),result.getString("Adresa"));
			}
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
