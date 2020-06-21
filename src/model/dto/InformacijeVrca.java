package model.dto;

import java.sql.Date;

public class InformacijeVrca {
	
	private int IdVrcanja;
	private int IdDrustva;
	private Date datumVrcanja;
	private String vrstaMeda;
	private double kolicinaMeda;
	private String zaposleni;
	
	public int getIdVrcanja() {
		return IdVrcanja;
	}
	public void setIdVrcanja(int idVrcanja) {
		IdVrcanja = idVrcanja;
	}
	public int getIdDrustva() {
		return IdDrustva;
	}
	public void setIdDrustva(int idDrustva) {
		IdDrustva = idDrustva;
	}
	public Date getDatumVrcanja() {
		return datumVrcanja;
	}
	public void setDatumVrcanja(Date datumVrcanja) {
		this.datumVrcanja = datumVrcanja;
	}
	public String getVrstaMeda() {
		return vrstaMeda;
	}
	public void setVrstaMeda(String vrstaMeda) {
		this.vrstaMeda = vrstaMeda;
	}
	public double getKolicinaMeda() {
		return kolicinaMeda;
	}
	public void setKolicinaMeda(double kolicinaMeda) {
		this.kolicinaMeda = kolicinaMeda;
	}
	public String getZaposleni() {
		return zaposleni;
	}
	public void setZaposleni(String zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	public InformacijeVrca(int idVrcanja, int idDrustva, Date datumVrcanja, String vrstaMeda, double kolicinaMeda,
			String zaposleni) {
		
		IdVrcanja = idVrcanja;
		IdDrustva = idDrustva;
		this.datumVrcanja = datumVrcanja;
		this.vrstaMeda = vrstaMeda;
		this.kolicinaMeda = kolicinaMeda;
		this.zaposleni = zaposleni;
	}
	
	
}
