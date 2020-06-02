package model.dto;

public class Zaposleni {
	
	private int IdZaposlenog;
	private java.math.BigDecimal Plata;
	private String KorisničkoIme;
	private String Lozinka;
	private String JMBG;
	private String Ime;
	private String Prezime;
	private int IdPčelinjaka;
	
	public int getIdZaposlenog() {
		return IdZaposlenog;
	}

	public void setIdZaposlenog(int idZaposlenog) {
		IdZaposlenog = idZaposlenog;
	}

	public java.math.BigDecimal getPlata() {
		return Plata;
	}

	public void setPlata(java.math.BigDecimal plata) {
		Plata = plata;
	}

	public String getKorisničkoIme() {
		return KorisničkoIme;
	}

	public void setKorisničkoIme(String korisničkoIme) {
		KorisničkoIme = korisničkoIme;
	}

	public String getLozinka() {
		return Lozinka;
	}

	public void setLozinka(String lozinka) {
		Lozinka = lozinka;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getPrezime() {
		return Prezime;
	}

	public void setPrezime(String prezime) {
		Prezime = prezime;
	}

	public int getIdPčelinjaka() {
		return IdPčelinjaka;
	}

	public void setIdPčelinjaka(int idPčelinjaka) {
		IdPčelinjaka = idPčelinjaka;
	}

	public void setIme(String ime) {
		Ime = ime;
	}

	public String getIme() {
		return Ime;
	}
	
	public Zaposleni(int IdZaposlenog,String KorisničkoIme, String Lozinka, String JMBG, String Ime,String Prezime,java.math.BigDecimal Plata,int IdPčelinjaka) {
		
		this.IdZaposlenog = IdZaposlenog;
		this.Plata = Plata;
		this.KorisničkoIme = KorisničkoIme;
		this.Lozinka = Lozinka;
		this.JMBG = JMBG;
		this.Ime = Ime;
		this.Prezime = Prezime;
		this.IdPčelinjaka = IdPčelinjaka;
		
	}

}
