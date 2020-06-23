package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import util.ConnectionPool;

public class PregledaDao {
	
	private String deleteByIdDrustvoQuery 	= "delete from pregleda where DRUŠTVO_IdDruštva = ?";
	private String deleteByIdZaposleniQuery = "delete from pregleda where ZAPOSLENI_IdZaposlenog = ?";
	private String addPregledaQuery = "insert into pregleda (`DatumPregleda`,`VeličinaLegla`,`KoličinaMedaURezervi`,`ProizveloRoj`,`DRUŠTVO_IdDruštva`,`ZAPOSLENI_IdZaposlenog`) values (?,?,?,?,?,?);";

	public int addPregleda(byte velicinaLegla, byte medURezervi,byte proizveloRoj,int IdDrustva,int IdZaposlenog) {
		
		Date datum = new Date(new java.util.Date().getTime());
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addPregledaQuery);
			ps.setDate(1, datum);
			ps.setByte(2, velicinaLegla);
			ps.setByte(3, medURezervi);
			ps.setByte(4, proizveloRoj);
			ps.setInt(5, IdDrustva);
			ps.setInt(6, IdZaposlenog);
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
