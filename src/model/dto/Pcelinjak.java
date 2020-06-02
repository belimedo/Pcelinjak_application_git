package model.dto;

public class Pcelinjak {
	
	private int IdPcelinjaka;
	private String NazivPcelinjaka;
	private String AdresaPcelinjaka;
	private int BrojDrustava;
	private int BrojVrcalica;
	private int BrojTegliZaAmbalazu;
	private int BrojZaposlenih;
	private int VLASNIK_IdVlasnika;
	
	
	
	public Pcelinjak(int idPcelinjaka, String nazivPcelinjaka, String adresaPcelinjaka, int brojDrustava,
			int brojVrcalica, int brojTegliZaAmbalazu, int brojZaposlenih, int vLASNIK_IdVlasnika) {
		IdPcelinjaka = idPcelinjaka;
		NazivPcelinjaka = nazivPcelinjaka;
		AdresaPcelinjaka = adresaPcelinjaka;
		BrojDrustava = brojDrustava;
		BrojVrcalica = brojVrcalica;
		BrojTegliZaAmbalazu = brojTegliZaAmbalazu;
		BrojZaposlenih = brojZaposlenih;
		VLASNIK_IdVlasnika = vLASNIK_IdVlasnika;
	}
	
	public Pcelinjak() {
		// TODO: Obrisi
	}



	public int getIdPcelinjaka() {
		return IdPcelinjaka;
	}



	public void setIdPcelinjaka(int idPcelinjaka) {
		IdPcelinjaka = idPcelinjaka;
	}



	public String getNazivPcelinjaka() {
		return NazivPcelinjaka;
	}



	public void setNazivPcelinjaka(String nazivPcelinjaka) {
		NazivPcelinjaka = nazivPcelinjaka;
	}



	public String getAdresaPcelinjaka() {
		return AdresaPcelinjaka;
	}



	public void setAdresaPcelinjaka(String adresaPcelinjaka) {
		AdresaPcelinjaka = adresaPcelinjaka;
	}



	public int getBrojDrustava() {
		return BrojDrustava;
	}



	public void setBrojDrustava(int brojDrustava) {
		BrojDrustava = brojDrustava;
	}



	public int getBrojVrcalica() {
		return BrojVrcalica;
	}



	public void setBrojVrcalica(int brojVrcalica) {
		BrojVrcalica = brojVrcalica;
	}



	public int getBrojTegliZaAmbalazu() {
		return BrojTegliZaAmbalazu;
	}



	public void setBrojTegliZaAmbalazu(int brojTegliZaAmbalazu) {
		BrojTegliZaAmbalazu = brojTegliZaAmbalazu;
	}



	public int getBrojZaposlenih() {
		return BrojZaposlenih;
	}



	public void setBrojZaposlenih(int brojZaposlenih) {
		BrojZaposlenih = brojZaposlenih;
	}



	public int getVLASNIK_IdVlasnika() {
		return VLASNIK_IdVlasnika;
	}



	public void setVLASNIK_IdVlasnika(int vLASNIK_IdVlasnika) {
		VLASNIK_IdVlasnika = vLASNIK_IdVlasnika;
	}



	public void bla()  {
		System.out.println(";ćčpđ");
	}
}
