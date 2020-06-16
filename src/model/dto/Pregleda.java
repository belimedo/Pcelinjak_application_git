package model.dto;

import java.sql.Date;

public class Pregleda {
	
	private int IdPregleda;
	private Date DatumPregleda;
	private byte VelicinaLegla;
	private byte KolicinaMedaURezervi;
	private byte ProizveloRoj;
	private int DRUSTVO_IdDrustva;
	private int ZAPOSLENI_IdZaposlenog;
	public int getIdPregleda() {
		return IdPregleda;
	}
	public void setIdPregleda(int idPregleda) {
		IdPregleda = idPregleda;
	}
	public Date getDatumPregleda() {
		return DatumPregleda;
	}
	public void setDatumPregleda(Date datumPregleda) {
		DatumPregleda = datumPregleda;
	}
	public byte getVelicinaLegla() {
		return VelicinaLegla;
	}
	public void setVelicinaLegla(byte velicinaLegla) {
		VelicinaLegla = velicinaLegla;
	}
	public byte getKolicinaMedaURezervi() {
		return KolicinaMedaURezervi;
	}
	public void setKolicinaMedaURezervi(byte kolicinaMedaURezervi) {
		KolicinaMedaURezervi = kolicinaMedaURezervi;
	}
	public byte getProizveloRoj() {
		return ProizveloRoj;
	}
	public void setProizveloRoj(byte proizveloRoj) {
		ProizveloRoj = proizveloRoj;
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
	
	public Pregleda(int idPregleda, Date datumPregleda, byte velicinaLegla, byte kolicinaMedaURezervi,
			byte proizveloRoj, int dRUSTVO_IdDrustva, int zAPOSLENI_IdZaposlenog) {
	
		IdPregleda = idPregleda;
		DatumPregleda = datumPregleda;
		VelicinaLegla = velicinaLegla;
		KolicinaMedaURezervi = kolicinaMedaURezervi;
		ProizveloRoj = proizveloRoj;
		DRUSTVO_IdDrustva = dRUSTVO_IdDrustva;
		ZAPOSLENI_IdZaposlenog = zAPOSLENI_IdZaposlenog;
	}

	
}
