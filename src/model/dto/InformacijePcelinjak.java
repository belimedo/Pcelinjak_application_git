package model.dto;

public class InformacijePcelinjak {
	
	private int IdPcelinjaka;
	private String Naziv;
	private String Adresa;
	private String Vlasnik;
	private int BrojDrustava;
	private double UkupnoMeda;
	private double UkupnoPropolisa;
	private int BrojZaposlenih;
	
	
	public InformacijePcelinjak(int idPcelinjaka, String naziv, String adresa, String vlasnik,
			int brojDrustava,double ukupnoMeda, double ukupnoPropolisa, int brojZaposlenih) {
	
		IdPcelinjaka = idPcelinjaka;
		Naziv = naziv;
		Adresa = adresa;
		Vlasnik = vlasnik;
		BrojDrustava=brojDrustava;
		UkupnoMeda = ukupnoMeda;
		UkupnoPropolisa = ukupnoPropolisa;
		BrojZaposlenih = brojZaposlenih;
	}
	
	public int getIdPcelinjaka() {
		return IdPcelinjaka;
	}
	
	public void setIdPcelinjaka(int idPcelinjaka) {
		IdPcelinjaka = idPcelinjaka;
	}
	
	public String getNaziv() {
		return Naziv;
	}
	
	public void setNaziv(String naziv) {
		Naziv = naziv;
	}
	
	public String getAdresa() {
		return Adresa;
	}
	
	public void setAdresa(String adresa) {
		Adresa = adresa;
	}
	
	
	public String getVlasnik() {
		return Vlasnik;
	}
	
	public void setVlasnik(String vlasnik) {
		Vlasnik = vlasnik;
	}
	
	public int getBrojDrustava() {
		return BrojDrustava;
	}

	public void setBrojDrustava(int brojDrustava) {
		BrojDrustava = brojDrustava;
	}
	
	public double getUkupnoMeda() {
		return UkupnoMeda;
	}
	
	public void setUkupnoMeda(double ukupnoMeda) {
		UkupnoMeda = ukupnoMeda;
	}
	
	public double getUkupnoPropolisa() {
		return UkupnoPropolisa;
	}
	
	public void setUkupnoPropolisa(double ukupnoPropolisa) {
		UkupnoPropolisa = ukupnoPropolisa;
	}
	
	public int getBrojZaposlenih() {
		return BrojZaposlenih;
	}
	
	public void setBrojZaposlenih(int brojZaposlenih) {
		BrojZaposlenih = brojZaposlenih;
	}
	
	

}
