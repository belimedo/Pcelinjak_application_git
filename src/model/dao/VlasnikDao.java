package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import model.dto.Vlasnik;
import util.ConnectionPool;

public class VlasnikDao {
	
	private String getByUsernameQuery 	= "select * from Vlasnik where KorisničkoIme = ? and Lozinka = ?";
	private String getByIdVlasnikaQuery = "select * from Vlasnik where IdVlasnika = ?";
	
	public Vlasnik getByUsername(String username, String pass) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByUsernameQuery);
			ps.setString(1, username);
			ps.setString(2, pass);
			result = ps.executeQuery();
			if (result.next()) {
				Vlasnik v = new Vlasnik( result.getInt("IdVlasnika"),result.getString("KorisničkoIme"),result.getString("Lozinka"),result.getString("JMBG"),result.getString("Ime"),result.getString("Prezime"));
				return v;
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
	
	
	public Vlasnik getByIdVlasnika(int IdVlasnika) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdVlasnikaQuery);
			ps.setInt(1, IdVlasnika);
			result = ps.executeQuery();
			if (result.next()) {
				Vlasnik v = new Vlasnik( result.getInt("IdVlasnika"),result.getString("KorisničkoIme"),result.getString("Lozinka"),result.getString("JMBG"),result.getString("Ime"),result.getString("Prezime"));
				return v;
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
