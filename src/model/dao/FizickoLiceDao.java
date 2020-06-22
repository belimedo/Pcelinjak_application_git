package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.dto.FizickoLice;
import model.dto.Trgovina;
import util.ConnectionPool;

public class FizickoLiceDao {
	
	private String getByIdKupcaQuery = "select * from fizičko_lice where KUPAC_IdKupca = ? ";
	
	public FizickoLice getByIdKupca(int IdKupca) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdKupcaQuery);
			ps.setInt(1, IdKupca);
			result = ps.executeQuery();
			if(result.next()) {
				
				return new FizickoLice(result.getInt("KUPAC_IdKupca"),result.getString("JMBG"),result.getString("Ime"),result.getString("Prezime"));
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
