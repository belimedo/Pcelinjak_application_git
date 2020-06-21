package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.InformacijePcelinjak;
import model.dto.InformacijeVrca;
import util.ConnectionPool;

public class InformacijeVrcaDao {
	
	private String getAllByIdDrustva = "select * from informacije_o_vrcanju where IdDruštva = ?";
	
	
	public List<InformacijeVrca> getByIdDrustva(int IdDrustva) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getAllByIdDrustva);
			ps.setInt(1, IdDrustva);
			result = ps.executeQuery();
			List<InformacijeVrca> infoVrca = new LinkedList<InformacijeVrca>();
			while (result.next()) {
				
				infoVrca.add(new InformacijeVrca(result.getInt("IdVrcanja"),result.getInt("IdDruštva"),result.getDate("DatumVrcanja"),result.getString("VrstaMeda"),result.getDouble("KoličinaMeda"),result.getString("Zaposleni")));
			}
			return infoVrca;
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
