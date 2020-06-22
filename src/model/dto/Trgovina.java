package model.dto;

public class Trgovina {

	private int IdKupca;
	private String naziv;
	private String adresa;
	
	public int getIdKupca() {
		return IdKupca;
	}
	public void setIdKupca(int idKupca) {
		IdKupca = idKupca;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	public Trgovina(int idKupca, String naziv, String adresa) {
		
		IdKupca = idKupca;
		this.naziv = naziv;
		this.adresa = adresa;
	}
	
	
	
}
