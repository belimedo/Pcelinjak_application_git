package model.dto;

import java.math.BigDecimal;

public class IzvrcaniMed {
	
	private int IdIzvrcanogMeda;
	private String vrsta;
	private double kolicina;
	private BigDecimal cijena;
	
	public int getIdIzvrcanogMeda() {
		return IdIzvrcanogMeda;
	}
	public void setIdIzvrcanogMeda(int idIzvrcanogMeda) {
		IdIzvrcanogMeda = idIzvrcanogMeda;
	}
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	public double getKolicina() {
		return kolicina;
	}
	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}
	public BigDecimal getCijena() {
		return cijena;
	}
	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}

	public IzvrcaniMed(int idIzvrcanogMeda, String vrsta, double kolicina, BigDecimal cijena) {

		IdIzvrcanogMeda = idIzvrcanogMeda;
		this.vrsta = vrsta;
		this.kolicina = kolicina;
		this.cijena = cijena;
	}
	
	

}
