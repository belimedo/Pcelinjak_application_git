package model.dto;

import java.math.BigDecimal;

public class Propolis {
	
	private int IdPropolisa;
	private String vrsta;
	private int kolicina;
	private BigDecimal cijena;
	
	public int getIdPropolisa() {
		return IdPropolisa;
	}
	public void setIdPropolisa(int idPropolisa) {
		IdPropolisa = idPropolisa;
	}
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public BigDecimal getCijena() {
		return cijena;
	}
	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}

	public Propolis(int idPropolisa, String vrsta, int kolicina, BigDecimal cijena) {

		IdPropolisa = idPropolisa;
		this.vrsta = vrsta;
		this.kolicina = kolicina;
		this.cijena = cijena;
	}
	
	

}
