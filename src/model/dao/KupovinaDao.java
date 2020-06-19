package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.dto.Kupovina;
import model.dto.Zaposleni;
import util.ConnectionPool;

public class KupovinaDao {
	
	private String deleteByIdPcelijakQuery 			= "delete from kupovina where `PČELINJAK_IdPčelinjaka` = ?";
	private String getKupovineByIdPcelinjakaQuery 	= "select * from kupovina where `PČELINJAK_IdPčelinjaka` = ?";
	
	
	public List<Kupovina> getKupovinaByPcelinjakId(int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getKupovineByIdPcelinjakaQuery);
			ps.setInt(1, IdPcelinjaka);
			result = ps.executeQuery();
			List<Kupovina> listKupovine = new LinkedList<Kupovina>();
			while (result.next()) {
				listKupovine.add(new Kupovina(result.getInt("IdKupovine"),result.getInt("KUPAC_IdKupca"),result.getInt("PČELINJAK_IdPčelinjaka"),result.getDate("Datum"),result.getBigDecimal("Cijena")));
			}
			return listKupovine;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}
	
	public int deleteByIdPcelinjaka(int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteByIdPcelijakQuery);
			ps.setInt(1, IdPcelinjaka);
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
