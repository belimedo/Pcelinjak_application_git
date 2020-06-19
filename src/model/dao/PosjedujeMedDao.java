package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.ConnectionPool;

public class PosjedujeMedDao {
	
private String deleteByIdPcelijakaQuery = "delete from posjeduje_med where `PČELINJAK_IdPčelinjaka` = ?";
	
	
	public int deleteByIdPcelinjaka(int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteByIdPcelijakaQuery);
			ps.setInt(1, IdPcelinjaka);
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
