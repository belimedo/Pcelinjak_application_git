package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.ConnectionPool;

public class StavkaMedDao {
	
	private String deleteByIdKupovineQuery = "delete from stavka_med where `KUPOVINA_IdKupovine` = ?";
	
	
	public int deleteByIdKupovine(int IdKupovine) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteByIdKupovineQuery);
			ps.setInt(1, IdKupovine);
			return ps.executeUpdate();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return 0;
	}

}
