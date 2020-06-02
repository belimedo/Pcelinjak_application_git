package model.dto;

public class Vlasnik {
	
	private int IdVlasnika;
	private String KorisničkoIme;
	private String Lozinka;
	private String JMBG;
	private String Ime;
	private String Prezime;
	
	public int getIdVlasnika() {
		return IdVlasnika;
	}

	public void setIdVlasnika(int idVlasnika) {
		IdVlasnika = idVlasnika;
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

	public void setIme(String ime) {
		Ime = ime;
	}

	public String getIme() {
		return Ime;
	}

	
	public Vlasnik(int IdVlasnika,String KorisničkoIme, String Lozinka, String JMBG, String Ime,String Prezime) {
		
		this.IdVlasnika = IdVlasnika;
		this.KorisničkoIme = KorisničkoIme;
		this.Lozinka = Lozinka;
		this.JMBG = JMBG;
		this.Ime = Ime;
		this.Prezime = Prezime;
	}
	
	public void print() {
		System.out.println(Integer.toString(this.IdVlasnika)+" "+ KorisničkoIme +" "+ Lozinka +" "+ JMBG +" "+ Ime +" "+ Prezime);
	}

}
