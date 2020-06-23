package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.ConnectionPool;

public class KupacDao {
	
	private String getMaxIdKupcaQuery 	= "select max(IdKupca) from kupac;";
	private String addKupacQuery		= "insert into kupac() values();";
	
	public int addKupac() {
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addKupacQuery);
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
	
	public int getMaxIdKupca() {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getMaxIdKupcaQuery);
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

}
