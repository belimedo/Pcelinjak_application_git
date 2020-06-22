package model.dto;

public class FizickoLice {
	
	private int IdKupca;
	private String JMBG;
	private String Ime;
	private String Pezime;
	
	public int getIdKupca() {
		return IdKupca;
	}
	public void setIdKupca(int idKupca) {
		IdKupca = idKupca;
	}
	public String getJMBG() {
		return JMBG;
	}
	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}
	public String getIme() {
		return Ime;
	}
	public void setIme(String ime) {
		Ime = ime;
	}
	public String getPezime() {
		return Pezime;
	}
	public void setPezime(String pezime) {
		Pezime = pezime;
	}
	
	public FizickoLice(int idKupca, String jMBG, String ime, String pezime) {
		
		IdKupca = idKupca;
		JMBG = jMBG;
		Ime = ime;
		Pezime = pezime;
	}
	
	

}
