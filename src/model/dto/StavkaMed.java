package model.dto;

import java.math.BigDecimal;

public class StavkaMed {
	
	private int IZVRCANI_MED_IdMeda;
	private int KUPOVINA_IdKupovine;
	private double kolicina;
	private java.math.BigDecimal cijena;
	
	public int getIZVRCANI_MED_IdMeda() {
		return IZVRCANI_MED_IdMeda;
	}
	public void setIZVRCANI_MED_IdMeda(int iZVRCANI_MED_IdMeda) {
		IZVRCANI_MED_IdMeda = iZVRCANI_MED_IdMeda;
	}
	public int getKUPOVINA_IdKupovine() {
		return KUPOVINA_IdKupovine;
	}
	public void setKUPOVINA_IdKupovine(int kUPOVINA_IdKupovine) {
		KUPOVINA_IdKupovine = kUPOVINA_IdKupovine;
	}
	public double getKolicina() {
		return kolicina;
	}
	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}
	public java.math.BigDecimal getCijena() {
		return cijena;
	}
	public void setCijena(java.math.BigDecimal cijena) {
		this.cijena = cijena;
	}
	public StavkaMed(int iZVRCANI_MED_IdMeda, int kUPOVINA_IdKupovine, double kolicina, BigDecimal cijena) {
		
		IZVRCANI_MED_IdMeda = iZVRCANI_MED_IdMeda;
		KUPOVINA_IdKupovine = kUPOVINA_IdKupovine;
		this.kolicina = kolicina;
		this.cijena = cijena;
	}
	
	
	

}
