package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.Lijeci;
import model.dto.Pcelinjak;
import util.ConnectionPool;

public class LijeciDao {
	
	private String deleteByIdDrustvaQuery = "delete from liječi where DRUŠTVO_IdDruštva = ?";
	private String deleteByIdZaposlenogQuery = "delete from liječi where ZAPOSLENI_IdZaposlenog = ?";
	private String getByIdZaposlenogQuery = "select * from liječi where ZAPOSLENI_IdZaposlenog = ?";
	private String getAllLijeciQuery = "select * from liječi";
	private String addLijeciQuery = "insert into liječi (`DatumLiječenja`,`VrstaLijeka`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`) values (?,?,?,?);";
	
	public int addLijeci(String vrstaLijeka,int IdDrustva,int IdZaposlenog) {
		
		Date datum = new Date(new java.util.Date().getTime());
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addLijeciQuery);
			ps.setDate(1, datum);
			ps.setString(2, vrstaLijeka);
			ps.setInt(3, IdDrustva);
			ps.setInt(4, IdZaposlenog);
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
	
	public List<Lijeci> getAllLijeci() {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getAllLijeciQuery);
			result = ps.executeQuery();
			List<Lijeci> listLijeci = new LinkedList<Lijeci>();
			while (result.next()) {
				
				listLijeci.add(new Lijeci(result.getInt("IdLiječenja"),result.getDate("DatumLiječenja"),result.getString("VrstaLijeka"),result.getInt("DRUŠTVO_IdDruštva"),
						result.getInt("ZAPOSLENI_IdZaposlenog")));
				
			}
			return listLijeci;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}
	
	public List<Lijeci> getByIdZaposlenog(int IdZaposlenog) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdZaposlenogQuery);
			ps.setInt(1, IdZaposlenog);
			result = ps.executeQuery();
			List<Lijeci> listLijeci = new LinkedList<Lijeci>();
			while (result.next()) {
				
				listLijeci.add(new Lijeci(result.getInt("IdLiječenja"),result.getDate("DatumLiječenja"),result.getString("VrstaLijeka"),result.getInt("DRUŠTVO_IdDruštva"),
						result.getInt("ZAPOSLENI_IdZaposlenog")));
				
			}
			return listLijeci;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}
	
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
