package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import model.dto.Drustvo;
import model.dto.Sanduk;
import util.ConnectionPool;

public class SandukDao {
	
	private String getTopSandukByIdDrustvaQuery	= "select * from sanduk where DRUŠTVO_IdDruštva = ?"; 
	private String addSandukQuery		= "call dodaj_sanduke (?,?,?,?,?,?)"; 
	private String deleteSandukQuery	= "call ukloni_sanduke (?,?)";
		
	
	public Sanduk getTopSandukByIdDrustva(int IdDrustva) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getTopSandukByIdDrustvaQuery);
			ps.setInt(1, IdDrustva);
			result = ps.executeQuery();
			List<Sanduk> listSanduk = new LinkedList<Sanduk>();
			while (result.next()) {
				
				listSanduk.add(new Sanduk(result.getByte("BrojSandukaUDruštvu"),result.getDate("GodinaKupovine"),result.getString("Boja"),
						result.getByte("BrojRamova"),result.getInt("DRUŠTVO_IdDruštva")));
			}
			listSanduk.sort((Sanduk s1,Sanduk s2) -> 
				Byte.valueOf(s1.getBrojSandukaUDrustvu()).compareTo(Byte.valueOf(s2.getBrojSandukaUDrustvu()))
			);
			return listSanduk.get(listSanduk.size()-1); // Dobijamo posljednji indeks ovako
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}
	
	public int addSanduk(int IdDrustva,int IdPcelinjaka,String boja,byte brojRamova) {
		
		byte brojSanduka = 1;
		int godina = Calendar.getInstance().get(Calendar.YEAR);
		

		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addSandukQuery);
			ps.setInt(1, IdDrustva);
			ps.setByte(2, brojSanduka);
			ps.setInt(3, IdPcelinjaka);
			ps.setInt(4, godina);
			ps.setString(5, boja);
			ps.setByte(6, brojRamova);
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

	public int deleteSanduk(int IdDrustva) {
		
		byte brojSanduka = 1;
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteSandukQuery);
			ps.setInt(1, IdDrustva);
			ps.setByte(2, brojSanduka);
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
