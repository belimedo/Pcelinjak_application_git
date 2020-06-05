package model.dto;

public class Drustvo {
	
	private int IdDrustva;
	private byte brojSanduka;
	private byte proizveloRoj;
	private byte velicinaLegla;
	private int kolicinaMedaURezervi;
	private int red;
	private int pozicijaURedu;
	private int PCELINJAKIdPcelinjaka;
	
	public Drustvo(int idDrustva, byte brojSanduka, byte proizveloRoj, byte velicinaLegla, int kolicinaMedaURezervi,
			int red, int pozicijaURedu, int pCELINJAKIdPcelinjaka) {

		IdDrustva = idDrustva;
		this.brojSanduka = brojSanduka;
		this.proizveloRoj = proizveloRoj;
		this.velicinaLegla = velicinaLegla;
		this.kolicinaMedaURezervi = kolicinaMedaURezervi;
		this.red = red;
		this.pozicijaURedu = pozicijaURedu;
		PCELINJAKIdPcelinjaka = pCELINJAKIdPcelinjaka;
	}
	
	public int getIdDrustva() {
		return IdDrustva;
	}
	public void setIdDrustva(int idDrustva) {
		IdDrustva = idDrustva;
	}
	public byte getBrojSanduka() {
		return brojSanduka;
	}
	public void setBrojSanduka(byte brojSanduka) {
		this.brojSanduka = brojSanduka;
	}
	public byte getProizveloRoj() {
		return proizveloRoj;
	}
	public void setProizveloRoj(byte proizveloRoj) {
		this.proizveloRoj = proizveloRoj;
	}
	public byte getVelicinaLegla() {
		return velicinaLegla;
	}
	public void setVelicinaLegla(byte velicinaLegla) {
		this.velicinaLegla = velicinaLegla;
	}
	public int getKolicinaMedaURezervi() {
		return kolicinaMedaURezervi;
	}
	public void setKolicinaMedaURezervi(int kolicinaMedaURezervi) {
		this.kolicinaMedaURezervi = kolicinaMedaURezervi;
	}
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getPozicijaURedu() {
		return pozicijaURedu;
	}
	public void setPozicijaURedu(int pozicijaURedu) {
		this.pozicijaURedu = pozicijaURedu;
	}
	public int getPCELINJAKIdPcelinjaka() {
		return PCELINJAKIdPcelinjaka;
	}
	public void setPCELINJAKIdPcelinjaka(int pCELINJAKIdPcelinjaka) {
		PCELINJAKIdPcelinjaka = pCELINJAKIdPcelinjaka;
	}
	

}
