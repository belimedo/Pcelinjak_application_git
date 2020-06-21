package model.dto;

import java.sql.Date;

public class InformacijeLijeci {
	
	private int IdLijecenja;
	private int IdDrustva;
	private Date datumLijecenja;
	private byte vrstaLijeka;
	private String zaposleni;
	
	public int getIdLijecenja() {
		return IdLijecenja;
	}
	public void setIdLijecenja(int idLijecenja) {
		IdLijecenja = idLijecenja;
	}
	public int getIdDrustva() {
		return IdDrustva;
	}
	public void setIdDrustva(int idDrustva) {
		IdDrustva = idDrustva;
	}
	public Date getDatumLijecenja() {
		return datumLijecenja;
	}
	public void setDatumLijecenja(Date datumLijecenja) {
		this.datumLijecenja = datumLijecenja;
	}
	public byte getVrstaLijeka() {
		return vrstaLijeka;
	}
	public void setVrstaLijeka(byte vrstaLijeka) {
		this.vrstaLijeka = vrstaLijeka;
	}
	public String getZaposleni() {
		return zaposleni;
	}
	public void setZaposleni(String zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	public InformacijeLijeci(int idLijecenja, int idDrustva, Date datumLijecenja, byte vrstaLijeka, String zaposleni) {
		
		IdLijecenja = idLijecenja;
		IdDrustva = idDrustva;
		this.datumLijecenja = datumLijecenja;
		this.vrstaLijeka = vrstaLijeka;
		this.zaposleni = zaposleni;
	}
	
	

}
