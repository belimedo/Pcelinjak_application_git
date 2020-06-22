package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.dto.IzvrcaniMed;
import model.dto.Propolis;
import util.ConnectionPool;

public class PropolisDao {
	
	private String getByIdPropolisaQuery = "select * from propolis where IdPropolisa = ?";
	
	public Propolis getByIdPropolisa(int IdPropolisa) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdPropolisaQuery);
			ps.setInt(1, IdPropolisa);
			result = ps.executeQuery();
			if(result.next()) {		
				return new Propolis(result.getInt("IdPropolisa"),result.getString("Vrsta"),result.getInt("Količina"),result.getBigDecimal("Cijena"));
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
