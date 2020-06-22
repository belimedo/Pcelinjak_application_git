package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.IzvrcaniMed;
import model.dto.PosjedujeMed;
import util.ConnectionPool;

public class IzvrcaniMedDao {
	
	private String getByIdMedaQuery = "select * from izvrcani_med where IdIzvrcanogMeda = ?";
	
	public IzvrcaniMed getByIdMeda(int IdMeda) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdMedaQuery);
			ps.setInt(1, IdMeda);
			result = ps.executeQuery();
			if(result.next()) {		
				return new IzvrcaniMed(result.getInt("IdIzvrcanogMeda"),result.getString("Vrsta"),result.getDouble("Količina"),result.getBigDecimal("Cijena"));
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
