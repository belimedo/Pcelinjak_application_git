package model.dto;

import java.sql.Date;

public class Lijeci {
	
	private int IdLijecenja;
	private Date DatumLijecenja;
	private String VrstaLijeka;
	private int DRUSTVO_IdDrustva;
	private int ZAPOSLENI_IdZaposlenog;
	public int getIdLijecenja() {
		return IdLijecenja;
	}
	public void setIdLijecenja(int idLijecenja) {
		IdLijecenja = idLijecenja;
	}
	public Date getDatumLijecenja() {
		return DatumLijecenja;
	}
	public void setDatumLijecenja(Date datumLijecenja) {
		DatumLijecenja = datumLijecenja;
	}
	public String getVrstaLijeka() {
		return VrstaLijeka;
	}
	public void setVrstaLijeka(String vrstaLijeka) {
		VrstaLijeka = vrstaLijeka;
	}
	public int getDRUSTVO_IdDrustva() {
		return DRUSTVO_IdDrustva;
	}
	public void setDRUSTVO_IdDrustva(int dRUSTVO_IdDrustva) {
		DRUSTVO_IdDrustva = dRUSTVO_IdDrustva;
	}
	public int getZAPOSLENI_IdZaposlenog() {
		return ZAPOSLENI_IdZaposlenog;
	}
	public void setZAPOSLENI_IdZaposlenog(int zAPOSLENI_IdZaposlenog) {
		ZAPOSLENI_IdZaposlenog = zAPOSLENI_IdZaposlenog;
	}
	public Lijeci(int idLijecenja, Date datumLijecenja, String vrstaLijeka, int dRUSTVO_IdDrustva,
			int zAPOSLENI_IdZaposlenog) {
	
		IdLijecenja = idLijecenja;
		DatumLijecenja = datumLijecenja;
		VrstaLijeka = vrstaLijeka;
		DRUSTVO_IdDrustva = dRUSTVO_IdDrustva;
		ZAPOSLENI_IdZaposlenog = zAPOSLENI_IdZaposlenog;
	}
	
	

}
