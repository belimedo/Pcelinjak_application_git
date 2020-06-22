package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.PosjedujeMed;
import model.dto.PosjedujePropolis;
import util.ConnectionPool;

public class PosjedujePropolisDao {
	
	private String deleteByIdPcelijakaQuery = "delete from posjeduje_propolis where `PČELINJAK_IdPčelinjaka` = ?";
	private String getByIdPcelijakaQuery 	= "select * from posjeduje_propolis where `PČELINJAK_IdPčelinjaka` = ?";
	
	public List<PosjedujePropolis> getByIdPcelinjaka(int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdPcelijakaQuery);
			ps.setInt(1, IdPcelinjaka);
			result = ps.executeQuery();
			List<PosjedujePropolis> posjeduje = new LinkedList<PosjedujePropolis>();
			while (result.next()) {
				
				posjeduje.add(new PosjedujePropolis(result.getInt("PROPOLIS_IdPropolisa"),result.getInt("PČELINJAK_IdPčelinjaka"),result.getInt("Količina")));
			}
			return posjeduje;
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
			ps=connection.prepareStatement(deleteByIdPcelijakaQuery);
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
