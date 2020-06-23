package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.dto.Drustvo;
import model.dto.Trgovina;
import util.ConnectionPool;

public class TrgovinaDao {
	
	private String getByIdKupcaQuery = "select * from trgovina where KUPAC_IdKupca = ? ";
	private String addTrgovinaQuery = "insert into trgovina (`KUPAC_IdKupca`,`Naziv`,`Adresa`) values (?,?,?);";
	
	public int addTrgovina(int IdKupca,String Naziv,String Adresa) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addTrgovinaQuery);
			ps.setInt(1, IdKupca);
			ps.setString(2, Naziv);
			ps.setString(3, Adresa);
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
	
	public Trgovina getByIdKupca(int IdKupca) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdKupcaQuery);
			ps.setInt(1, IdKupca);
			result = ps.executeQuery();
			if(result.next()) {
				
				return new Trgovina(result.getInt("KUPAC_IdKupca"),result.getString("Naziv"),result.getString("Adresa"));
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
