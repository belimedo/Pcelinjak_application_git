package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.ConnectionPool;

public class PregledaDao {
	
	private String deleteByIdDrustvoQuery 	= "delete from pregleda where DRUŠTVO_IdDruštva = ?";
	private String deleteByIdZaposleniQuery = "delete from pregleda where ZAPOSLENI_IdZaposlenog = ?";
	

	public int deletePregledaByIdDrustva(int IdDrustva) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteByIdDrustvoQuery);
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
	
	public int deletePregledaByIdZaposlenog(int IdZaposlenog) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteByIdZaposleniQuery);
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
