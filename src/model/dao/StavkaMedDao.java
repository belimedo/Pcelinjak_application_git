package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.StavkaMed;
import util.ConnectionPool;

public class StavkaMedDao {
	
	private String deleteByIdKupovineQuery = "delete from stavka_med where `KUPOVINA_IdKupovine` = ?";
	private String getByIdKupovineQuery	= "select * from stavka_med where `KUPOVINA_IdKupovine` = ?";
	
	
	public List<StavkaMed> getAllByIdKupovine(int IdKupovine) {
		
		Connection connection = null;
		PreparedStatement ps  = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdKupovineQuery);
			ps.setInt(1, IdKupovine);
			result = ps.executeQuery();
			List<StavkaMed> stavke = new LinkedList<StavkaMed>();
			while(result.next()) {
				stavke.add(new StavkaMed(result.getInt("IZVRCANI_MED_MED_IdMeda"),result.getInt("KUPOVINA_IdKupovine"),result.getDouble("Količina"),result.getBigDecimal("Cijena")));
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
