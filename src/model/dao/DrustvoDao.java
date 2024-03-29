package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import model.dto.Drustvo;
import util.ConnectionPool;

public class DrustvoDao {
	
	private String getByPcelinjakIdQuery 	= "select * from društvo where PČELINJAK_IdPčelinjaka = ?";
	private String deleteByIdQuery 			= "call izbrisi_drustvo(?)";
	private String addDrustvoQuery 			= "call dodaj_drustvo(?,?,?,?,?,?,?,?,?,?)";
	private String getNumberOfRowsQuery		= "select max(Red) from društvo where PČELINJAK_IdPčelinjaka = ?";
	private String getGetMaxPositionInRowQuery	= "select max(PozicijaURedu) from društvo where PČELINJAK_IdPčelinjaka = ? and Red = ?";
	private String getByIdDrustvaQuery		= "select * from društvo where IdDruštva = ?";
	
	public int delteById(int IdDrustva) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(deleteByIdQuery);
			ps.setInt(1, IdDrustva);
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
	
	public void deleteAllDrustvaFromPcelinjak(int IdPcelinjaka) {
		
		LinkedList<Drustvo> drustva = (LinkedList<Drustvo>)this.getByPcelinjakId(IdPcelinjaka);
		for (Drustvo d : drustva) {
			this.delteById(d.getIdDrustva());
		}
	}
	
	public List<Drustvo> getByPcelinjakId(int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByPcelinjakIdQuery);
			ps.setInt(1, IdPcelinjaka);
			result = ps.executeQuery();
			List<Drustvo> listDrustvo = new LinkedList<Drustvo>();
			while (result.next()) {
				
				listDrustvo.add(new Drustvo(result.getInt("IdDruštva"), result.getByte("BrojSanduka"), result.getByte("ProizveloRoj"), result.getByte("VeličinaLegla"),result.getInt("KoličinaMedaURezervi"),
						result.getInt("Red"), result.getInt("PozicijaURedu"),result.getInt("PČELINJAK_IdPčelinjaka")));
			}
			return listDrustvo;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}

	public Drustvo getByIdDrustva(int IdDrustva) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdDrustvaQuery);
			ps.setInt(1, IdDrustva);
			result = ps.executeQuery();
			if(result.next()) {
				
				return new Drustvo(result.getInt("IdDruštva"), result.getByte("BrojSanduka"), result.getByte("ProizveloRoj"), result.getByte("VeličinaLegla"),result.getInt("KoličinaMedaURezervi"),
						result.getInt("Red"), result.getInt("PozicijaURedu"),result.getInt("PČELINJAK_IdPčelinjaka"));
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
	
	public int addDrustvo(byte brojSanduka,byte veličinaLegla, byte količinaMedaURezervi, int red , int pozicijaURedu, String boja , byte brojRamova,int IdPčelinjaka) {
		
		int godina = Calendar.getInstance().get(Calendar.YEAR);
		byte proizveloRoj = 0;
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addDrustvoQuery);
			ps.setByte(1, brojSanduka);
			ps.setByte(2, proizveloRoj);
			ps.setByte(3, veličinaLegla);
			ps.setByte(4, količinaMedaURezervi);
			ps.setInt(5, red);
			ps.setInt(6, pozicijaURedu);
			ps.setInt(7, IdPčelinjaka);
			ps.setInt(8, godina);
			ps.setString(9, boja);
			ps.setByte(10, brojRamova);
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

	public int getNumberOfRows(int IdPcelinjaka) {
		
		Connection connection 	= null;
		PreparedStatement ps 	= null;
		ResultSet rs = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getNumberOfRowsQuery);
			ps.setInt(1, IdPcelinjaka);
			rs= ps.executeQuery();
			while(rs.next())
				return rs.getInt(1);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		
		return -1;
	}
	
	public int getLastPositionInRow(int row, int IdPcelinjaka) {
		
		Connection connection 	= null;
		PreparedStatement ps 	= null;
		ResultSet rs = null;
		
		int result = -1;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getGetMaxPositionInRowQuery);
			ps.setInt(1, IdPcelinjaka);
			ps.setInt(2,row);
			rs= ps.executeQuery();
			while(rs.next())
				result = rs.getInt(1);
			System.out.println(result);
			return result;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		
		return result;
	}

}
