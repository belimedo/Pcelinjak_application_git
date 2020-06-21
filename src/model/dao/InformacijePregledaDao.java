package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import model.dto.InformacijePregleda;
import model.dto.InformacijeVrca;
import util.ConnectionPool;

public class InformacijePregledaDao {
	

	private String getAllByIdDrustva = "select * from informacije_o_pregledanju where IdDruštva = ?";
	
	
	public List<InformacijePregleda> getByIdDrustva(int IdDrustva) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getAllByIdDrustva);
			ps.setInt(1, IdDrustva);
			result = ps.executeQuery();
			List<InformacijePregleda> infoPregleda = new LinkedList<InformacijePregleda>();
			while (result.next()) {
				infoPregleda.add(new InformacijePregleda(result.getInt("IdPregleda"),result.getInt("IdDruštva"),result.getDate("DatumPregleda"),result.getByte("VeličinaLegla"),result.getByte("KoličinaMedaURezervi"),result.getByte("ProizveloRoj"),result.getString("Zaposleni")));
			}
			return infoPregleda;
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
