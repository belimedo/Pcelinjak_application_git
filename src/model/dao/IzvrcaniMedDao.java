package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.IzvrcaniMed;
import model.dto.PosjedujeMed;
import util.ConnectionPool;

public class IzvrcaniMedDao {
	
	private String getByIdMedaQuery = "select * from izvrcani_med where IdIzvrcanogMeda = ?";
	private String getAllIzvraciMedQuery = "select * from izvrcani_med";
	private String addNewMedQuery = "insert into izvrcani_med (`Vrsta`,`Količina`,`Cijena`) values(?,?,?)";
	private String getMaxIdMeda	= "select max(IdIzvrcanogMeda) from izvrcani_med";
	
	public int getMaxIdIzvrcanogMeda() {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getMaxIdMeda);
			result = ps.executeQuery();
			if(result.next())
				return result.getInt(1);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int addNewIzvrcaniMed(String vrsta,double kolicina,BigDecimal cijena) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdMedaQuery);
			ps.setString(1, vrsta);
			ps.setDouble(2, kolicina);
			ps.setBigDecimal(3, cijena);
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
	
	public List<IzvrcaniMed> getAllIzvracniMed() {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getAllIzvraciMedQuery);
			result = ps.executeQuery();
			List<IzvrcaniMed> listIzvrcaniMed = new LinkedList<IzvrcaniMed>();
			while(result.next()) {		
				listIzvrcaniMed.add( new IzvrcaniMed(result.getInt("IdIzvrcanogMeda"),result.getString("Vrsta"),result.getDouble("Količina"),result.getBigDecimal("Cijena")));
			}
			return listIzvrcaniMed;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}

	public IzvrcaniMed getByIdMeda(int IdMeda) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdMedaQuery);
			ps.setInt(1, IdMeda);
			result = ps.executeQuery();
			if(result.next()) {		
				return new IzvrcaniMed(result.getInt("IdIzvrcanogMeda"),result.getString("Vrsta"),result.getDouble("Količina"),result.getBigDecimal("Cijena"));
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
