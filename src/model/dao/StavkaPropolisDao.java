package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.StavkaMed;
import model.dto.StavkaPropolis;
import util.ConnectionPool;

public class StavkaPropolisDao {
	
private String deleteByIdKupovineQuery 	= "delete from stavka_propolis where `KUPOVINA_IdKupovine` = ?";
private String getByIdKupovineQuery	= "select * from stavka_propolis where `KUPOVINA_IdKupovine` = ?";
private String addStavkaPropolisQuery 	= "insert into stavka_propolis (`KUPOVINA_IdKupovine`,`PROPOLIS_IdPropolisa`,`Količina`,`Cijena`) values (?,?,?,?);";

	public int addStavkaPropolis(int IdKupovine,int IdPropolisa,int kolicina,BigDecimal cijena) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addStavkaPropolisQuery);
			ps.setInt(1, IdKupovine);
			ps.setInt(2, IdPropolisa);
			ps.setInt(3, kolicina);
			ps.setBigDecimal(4, cijena);
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

public List<StavkaPropolis> getAllByIdKupovine(int IdKupovine) {
	
	Connection connection = null;
	PreparedStatement ps  = null;
	ResultSet result = null;
	
	try {
		connection = ConnectionPool.getInstance().checkOut();
		ps=connection.prepareStatement(getByIdKupovineQuery);
		ps.setInt(1, IdKupovine);
		result = ps.executeQuery();
		List<StavkaPropolis> stavke = new LinkedList<StavkaPropolis>();
		while(result.next()) {
			stavke.add(new StavkaPropolis(result.getInt("PROPOLIS_IdPropolisa"),result.getInt("KUPOVINA_IdKupovine"),result.getInt("Količina"),result.getBigDecimal("Cijena")));
		}
		return stavke;
	}catch (Exception ex) {
		ex.printStackTrace();
	}
	finally {
		ConnectionPool.getInstance().checkIn(connection);
	}
	return null;
}
	
	
	public int deleteByIdKupovine(int IdKupovine) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteByIdKupovineQuery);
			ps.setInt(1, IdKupovine);
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
