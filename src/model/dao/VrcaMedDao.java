package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.ConnectionPool;

public class VrcaMedDao {
	
	private String deleteByIdDrustvaQuery = "delete from vrca_med where DRUŠTVO_IdDruštva = ?";
	private String deleteByIdZaposlenogQuery = "delete from vrca_med where ZAPOSLENI_IdZaposlenog = ?";
	
	/**
	 * Ova metoda brise sve vrste tabele Vrca med sa odgovarajucim IdDrustva
	 * @param IdDrustva
	 * @param IdZaposlenog
	 * @return
	 */
	public int deleteVrcaMedByIdDrustva(int IdDrustva) {
		
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
	
public int deleteVrcaMedByIdZaposlenog(int IdZaposlenog) {
		
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
