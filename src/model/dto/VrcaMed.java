package model.dto;

import java.sql.Date;

public class VrcaMed {
	
	private int IdVrcanja;
	private Date DatumVrcanja;
	private String VrstaMeda;
	private double KolicinaMeda;
	private int DRUSTVO_IdDrustva;
	private int ZAPOSLENI_IdZaposlenog;
	
	public int getIdVrcanja() {
		return IdVrcanja;
	}
	public void setIdVrcanja(int idVrcanja) {
		IdVrcanja = idVrcanja;
	}
	public Date getDatumVrcanja() {
		return DatumVrcanja;
	}
	public void setDatumVrcanja(Date datumVrcanja) {
		DatumVrcanja = datumVrcanja;
	}
	public String getVrstaMeda() {
		return VrstaMeda;
	}
	public void setVrstaMeda(String vrstaMeda) {
		VrstaMeda = vrstaMeda;
	}
	public double getKolicinaMeda() {
		return KolicinaMeda;
	}
	public void setKolicinaMeda(double kolicinaMeda) {
		KolicinaMeda = kolicinaMeda;
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
	public VrcaMed(int idVrcanja, Date datumVrcanja, String vrstaMeda, double kolicinaMeda, int dRUSTVO_IdDrustva,
			int zAPOSLENI_IdZaposlenog) {
	
		IdVrcanja = idVrcanja;
		DatumVrcanja = datumVrcanja;
		VrstaMeda = vrstaMeda;
		KolicinaMeda = kolicinaMeda;
		DRUSTVO_IdDrustva = dRUSTVO_IdDrustva;
		ZAPOSLENI_IdZaposlenog = zAPOSLENI_IdZaposlenog;
	}
	
	

}
