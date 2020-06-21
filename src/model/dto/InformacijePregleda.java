package model.dto;

import java.sql.Date;

public class InformacijePregleda {
	
	private int IdPregleda;
	private int IdDrustva;
	private Date datumPregleda;
	private byte velicinaLegla;
	private byte kolicinaMedaURezervi;
	private byte proizveloRoj;
	private String zaposleni;
	
	public int getIdPregleda() {
		return IdPregleda;
	}
	public void setIdPregleda(int idPregleda) {
		IdPregleda = idPregleda;
	}
	public int getIdDrustva() {
		return IdDrustva;
	}
	public void setIdDrustva(int idDrustva) {
		IdDrustva = idDrustva;
	}
	public Date getDatumPregleda() {
		return datumPregleda;
	}
	public void setDatumPregleda(Date datumPregleda) {
		this.datumPregleda = datumPregleda;
	}
	public byte getVelicinaLegla() {
		return velicinaLegla;
	}
	public void setVelicinaLegla(byte velicinaLegla) {
		this.velicinaLegla = velicinaLegla;
	}
	public byte getKolicinaMedaURezervi() {
		return kolicinaMedaURezervi;
	}
	public void setKolicinaMedaURezervi(byte kolicinaMedaURezervi) {
		this.kolicinaMedaURezervi = kolicinaMedaURezervi;
	}
	public byte getProizveloRoj() {
		return proizveloRoj;
	}
	public void setProizveloRoj(byte proizveloRoj) {
		this.proizveloRoj = proizveloRoj;
	}
	public String getZaposleni() {
		return zaposleni;
	}
	public void setZaposleni(String zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	public InformacijePregleda(int idPregleda, int idDrustva, Date datumPregleda, byte velicinaLegla,
			byte kolicinaMedaURezervi, byte proizveloRoj, String zaposleni) {
		
		IdPregleda = idPregleda;
		IdDrustva = idDrustva;
		this.datumPregleda = datumPregleda;
		this.velicinaLegla = velicinaLegla;
		this.kolicinaMedaURezervi = kolicinaMedaURezervi;
		this.proizveloRoj = proizveloRoj;
		this.zaposleni = zaposleni;
	}
	
	
	

}
