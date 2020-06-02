package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.dto.InformacijePcelinjak;
import util.ConnectionPool;

public class InformacijePcelinjakDao {
	
	private String getInformacijePcelinjakByIdPcelinjaka = "select * from informacije_o_pčelinjaku where Id = ?";
	
	public InformacijePcelinjak getByPcelinjakId(int IdPcelinjaka) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getInformacijePcelinjakByIdPcelinjaka);
			ps.setInt(1, IdPcelinjaka);
			result = ps.executeQuery();
			if (result.next()) {
				
				InformacijePcelinjak ip = new InformacijePcelinjak(result.getInt("Id"), result.getString("Naziv"), result.getString("Adresa"), result.getString("Vlasnik"),
						result.getInt("BrojDruštava"),result.getDouble("UkupnoMeda"),result.getDouble("UkupnoPropolisa"), result.getInt("BrojZaposlenih"));
				return ip;
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
