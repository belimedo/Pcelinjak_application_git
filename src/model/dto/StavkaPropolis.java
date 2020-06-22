package model.dto;

import java.math.BigDecimal;

public class StavkaPropolis {

	private int PROPOLIS_IdPropolisa;
	private int KUPOVINA_IdKupovine;
	private int kolicina;
	private java.math.BigDecimal cijena;
	
	public int getPROPOLIS_IdPropolisa() {
		return PROPOLIS_IdPropolisa;
	}
	public void setPROPOLIS_IdPropolisa(int pROPOLIS_IdPropolisa) {
		PROPOLIS_IdPropolisa = pROPOLIS_IdPropolisa;
	}
	public int getKUPOVINA_IdKupovine() {
		return KUPOVINA_IdKupovine;
	}
	public void setKUPOVINA_IdKupovine(int kUPOVINA_IdKupovine) {
		KUPOVINA_IdKupovine = kUPOVINA_IdKupovine;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public java.math.BigDecimal getCijena() {
		return cijena;
	}
	public void setCijena(java.math.BigDecimal cijena) {
		this.cijena = cijena;
	}
	public StavkaPropolis(int pROPOLIS_IdPropolisa, int kUPOVINA_IdKupovine, int kolicina, BigDecimal cijena) {
		
		PROPOLIS_IdPropolisa = pROPOLIS_IdPropolisa;
		KUPOVINA_IdKupovine = kUPOVINA_IdKupovine;
		this.kolicina = kolicina;
		this.cijena = cijena;
	}

	
}
