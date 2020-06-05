package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import model.dto.Drustvo;
import model.dto.Pcelinjak;
import util.ConnectionPool;


public class PcelinjakDao {
	
	private String getByIdQuery 		= "select * from pčelinjak where IdPčelinjaka = ?";
	private String getByOwnerQuery 		= "select * from pčelinjak where VLASNIK_IdVlasnika = ?";
	private String getByNameQuery 		= "select * from pčelinjak where NazivPčelinjaka = ?";
	private String updateAddressQuery 	= "update Pčelinjak set AdresaPčelinjaka = ? where IdPčelinjaka = ?";
	private String addVrcalicaQuery		= "update Pčelinjak set BrojVrcalica = BrojVrcalica + ? where IdPčelinjaka = ?";
	private String removeVrcalicaQuery	= "update Pčelinjak set BrojVrcalica = BrojVrcalica - ? where IdPčelinjaka = ? and BrojVrcalica >= ?";
	private String addTegleZaAmbalazuQuery		= "update Pčelinjak set BrojTegliZaAmbalažu = BrojTegliZaAmbalažu + ? where IdPčelinjaka = ?";
	private String removeTegleZaAmbalazuQuery	= "update Pčelinjak set BrojTegliZaAmbalažu = BrojTegliZaAmbalažu - ? where IdPčelinjaka = ? and BrojTegliZaAmbalažu >= ?";
	private String addPcelinjakQuery	= "call dodaj_pcelinjak(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	
	
	/**
	 * 
	 */
	public List<Pcelinjak> getAll() {
		// TODO ovdje vracam listu 'chelinjaka
		return null;
	}
	
	
	public List<Pcelinjak> getByOwner(int IdVlasnika) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByOwnerQuery);
			ps.setInt(1, IdVlasnika);
			result = ps.executeQuery();
			List<Pcelinjak> listPcelinjak = new LinkedList<Pcelinjak>();
			while (result.next()) {
				String naziv = result.getString("NazivPčelinjaka");
				listPcelinjak.add(new Pcelinjak(result.getInt("IdPčelinjaka"),result.getString("NazivPčelinjaka"),result.getString("AdresaPčelinjaka"),result.getInt("BrojDruštava"),
						result.getInt("BrojVrcalica"),result.getInt("BrojTegliZaAmbalažu"),result.getInt("BrojZaposlenih"),result.getInt("VLASNIK_IdVlasnika")));
				System.out.println(naziv);
			}
			return listPcelinjak;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
		
	}
	
	/**
	 * Vraca pcelinjak prema proslijedjenom Id-ju
	 * @param Id
	 * @return
	 */
	public Pcelinjak getById(int Id) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByIdQuery);
			ps.setInt(1, Id);
			result = ps.executeQuery();
			if (result.next()) {
				Pcelinjak p = new Pcelinjak(result.getInt("IdPčelinjaka"),result.getString("NazivPčelinjaka"),result.getString("AdresaPčelinjaka"),result.getInt("BrojDruštava"),
						result.getInt("BrojVrcalica"),result.getInt("BrojTegliZaAmbalažu"),result.getInt("BrojZaposlenih"),result.getInt("VLASNIK_IdVlasnika"));
				return p;
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
	
	/**
	 * Metoda koja vraca Pcelinjak na osnovu naziva pcelinjaka
	 * @param NazivPcelinjaka
	 * @return
	 */
	public Pcelinjak getByName(String NazivPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(getByNameQuery);
			ps.setString(1, NazivPcelinjaka);
			result = ps.executeQuery();
			if (result.next()) {
				Pcelinjak p = new Pcelinjak(result.getInt("IdPčelinjaka"),result.getString("NazivPčelinjaka"),result.getString("AdresaPčelinjaka"),result.getInt("BrojDruštava"),
						result.getInt("BrojVrcalica"),result.getInt("BrojTegliZaAmbalažu"),result.getInt("BrojZaposlenih"),result.getInt("VLASNIK_IdVlasnika"));
				return p;
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
	
	/**
	 * Metoda koja azurira adresu pcelinjaka
	 * @param novaAdresa
	 * @param IdPcelinjaka
	 */
	public void updateAddress(String novaAdresa,int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(updateAddressQuery);
			ps.setString(1, novaAdresa);
			ps.setInt(2, IdPcelinjaka);
			ps.executeUpdate();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
	}
	
	/**
	 * Metoda koja dodaje nove vrcalice
	 * @param noveVrcalice
	 * @param IdPcelinjaka
	 * @return vraca 0 ukoliko nije uspjesan upit, vrijednost vecu od 0 ukoliko je uspjesan
	 */
	public int addVrcalica(int noveVrcalice,int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addVrcalicaQuery);
			ps.setInt(1, noveVrcalice);
			ps.setInt(2, IdPcelinjaka);
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
	
	/**
	 * Metoda koja oduzima brojVrcalica od terenutnog broja
	 * @param brojVrcalica
	 * @param IdPcelinjaka
	 * @return vraca 0 ukoliko nije uspjesan upit, vrijednost vecu od 0 ukoliko je uspjesan
	 */
	public int removeVrcalica(int brojVrcalica,int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(removeVrcalicaQuery);
			ps.setInt(1, brojVrcalica);
			ps.setInt(2, IdPcelinjaka);
			ps.setInt(3, brojVrcalica);
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

	/**
	 * Metoda koja dodaje nove tegle za ambalazu
	 * @param noveTegle
	 * @param IdPcelinjaka
	 * @return vraca 0 ukoliko nije uspjesan upit, vrijednost vecu od 0 ukoliko je uspjesan
	 */
	public int addTegleZaAmbalazu(int noveTegle,int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addTegleZaAmbalazuQuery);
			ps.setInt(1, noveTegle);
			ps.setInt(2, IdPcelinjaka);
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
	
	/**
	 * Metoda koja oduzima brojTegli od terenutnog broja
	 * @param brojTegli
	 * @param IdPcelinjaka
	 * @return vraca 0 ukoliko nije uspjesan upit, vrijednost vecu od 0 ukoliko je uspjesan
	 */
	public int removeTegleZaAmbalazu(int brojTegli,int IdPcelinjaka) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(removeTegleZaAmbalazuQuery);
			ps.setInt(1, brojTegli);
			ps.setInt(2, IdPcelinjaka);
			ps.setInt(3, brojTegli);
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
	
	/**
	 * Metoda koja dodaje novi pcelinjak u bazu podataka na osnovu 
	 * @param nazivPcelinjaka
	 * @param adresaPcelinjaka
	 * @param brojDrustava
	 * @param brojVrcalica
	 * @param brojTegliZaAmbalazu
	 * @param brojZaposlenih
	 * @param IdVlasnika
	 * @return
	 */
	public int addPcelinjak(String nazivPcelinjaka,String adresaPcelinjaka,int brojDrustava,int brojVrcalica, int brojTegliZaAmbalazu,
			int brojZaposlenih,int IdVlasnika) {
		
		// TODO: dobiti Id pcelinjaka ovdje preko LastID....
		String boja = "bijela";
		byte brojSanduka 	= 2;
		byte proizveloRoj 	= 0;
		byte velicinaLegla	= 4;
		byte kolicinaMeda	= 5;
		byte brojRamova		= 10;
		int godina			= 2020; //TODO: Ovo promijeniti
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionPool.getInstance().checkOut();
			ps=connection.prepareStatement(addPcelinjakQuery);
			ps.setString(1, nazivPcelinjaka);
			ps.setString(2, adresaPcelinjaka);
			ps.setInt(3, brojDrustava);
			ps.setInt(4, brojVrcalica);
			ps.setInt(5, brojTegliZaAmbalazu);
			ps.setInt(6, brojZaposlenih);
			ps.setInt(7, IdVlasnika);
			ps.setByte(8, brojSanduka);
			ps.setByte(9, proizveloRoj);
			ps.setByte(10, velicinaLegla);
			ps.setByte(11, kolicinaMeda);
			ps.setInt(12, godina);
			ps.setString(13, boja);
			ps.setByte(14, brojRamova);
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

	public int deletePcelinjak(int IdPcelinjaka) {
		
		/**
		 *  Kod brisanja pcelinjaka potrebno je pobrisati: 
		 *  1. Sva drustva i sve sanduke +
		 *  2. Sve zaposlene i sve tabele tipa vrca, lijeci, pregleda
		 *  3. Sve kupovine, tabele stavka_med, stavka_propolis, posjeduje_med i posjeduje_propolis
		 */
		
		new DrustvoDao().deleteAllDrustvaFromPcelinjak(IdPcelinjaka);
		
		
		return 0;
	}


}
