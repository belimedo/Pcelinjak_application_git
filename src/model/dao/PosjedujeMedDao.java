package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.InformacijePregleda;
import model.dto.PosjedujeMed;
import util.ConnectionPool;

public class PosjedujeMedDao {
	
private String deleteByIdPcelijakaQuery = "delete from posjeduje_med where `PČELINJAK_IdPčelinjaka` = ?";
private String getByIdPcelijakaQuery 	= "select * from posjeduje_med where `PČELINJAK_IdPčelinjaka` = ?";
private String addNewPosjedujeMedQuery 	= "insert into posjeduje_med (`PČELINJAK_IdPčelinjaka`,`IZVRCANI_MED_MED_IdMeda`,`Količina`) values (?,?,?);";

	public int addPosjedujeMed(int IdPcelinjaka,int IdMeda,int kolicina) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addNewPosjedujeMedQuery);
			ps.setInt(1, IdPcelinjaka);
			ps.setInt(2, IdMeda);
			ps.setDouble(3, kolicina);
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
	
	public List<PosjedujeMed> getByIdPcelinjaka(int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdPcelijakaQuery);
			ps.setInt(1, IdPcelinjaka);
			result = ps.executeQuery();
			List<PosjedujeMed> posjeduje = new LinkedList<PosjedujeMed>();
			while (result.next()) {
				
				posjeduje.add(new PosjedujeMed(result.getInt("IZVRCANI_MED_MED_IdMeda"),result.getInt("PČELINJAK_IdPčelinjaka"),result.getDouble("Količina")));
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
