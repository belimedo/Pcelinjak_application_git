package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.ConnectionPool;

public class LijeciDao {
	
	private String deleteByIdDrustvaQuery = "delete from liječi where DRUŠTVO_IdDruštva = ?";
	private String deleteByIdZaposlenogQuery = "delete from liječi where ZAPOSLENI_IdZaposlenog = ?";
	
	public int deleteLijeciByIdDrustva(int IdDrustva) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteByIdDrustvaQuery);
			ps.setInt(1, IdDrustva);
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
	
	public int deleteLijeciByIdZaposlenog(int IdZaposlenog) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteByIdZaposlenogQuery);
			ps.setInt(1, IdZaposlenog);
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
