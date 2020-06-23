package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import util.ConnectionPool;

public class VrcaMedDao {
	
	private String deleteByIdDrustvaQuery = "delete from vrca_med where DRUŠTVO_IdDruštva = ?";
	private String deleteByIdZaposlenogQuery = "delete from vrca_med where ZAPOSLENI_IdZaposlenog = ?";
	private String addNewVrcaMedQuery = "insert into vrca_med (`DatumVrcanja`,`VrstaMeda`,`KoličinaMeda`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`) values (?,?,?,?,?);";
	
	
public int addNewVrcaMed(String vrsta,double kolicina,int IdDrustva,int IdZaposlenog) {
		
		Date datum = new Date(new java.util.Date().getTime());
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addNewVrcaMedQuery);
			ps.setDate(1, datum);
			ps.setString(2, vrsta);
			ps.setDouble(3, kolicina);
			ps.setInt(4, IdDrustva);
			ps.setInt(5, IdZaposlenog);
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
