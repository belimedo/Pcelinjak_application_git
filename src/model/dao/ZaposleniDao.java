package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import model.dto.Zaposleni;
import util.ConnectionPool;

public class ZaposleniDao {
	
private String getByUsernameQuery = "select * from Zaposleni where KorisničkoIme = ? and Lozinka = ?";
	
	public Zaposleni getByUsername(String username, String pass) {
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
				Zaposleni z = new Zaposleni( result.getInt("IdZaposlenog"),result.getString("KorisničkoIme"),result.getString("Lozinka"),result.getString("JMBG"),result.getString("Ime"),result.getString("Prezime"),result.getBigDecimal("Plata"),result.getInt("PČELINJAK_IdPčelinjaka"));
				return z;
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
	
	public int addZaposleni() {
		return 0;
	}
	
	public int deleteZaposleni() {
		return 0;
	}
	
	public List<Zaposleni> getAllByPcelinjakId(int IdPcelinjaka) {
		return null;
	}

}
