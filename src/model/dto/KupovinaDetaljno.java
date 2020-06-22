package model.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;

public class KupovinaDetaljno {
	
	// Dio promjenljivih potreban za pocetni prikaz;
	private int IdKupovine;
	private int IdKupca;
	private int IdPcelinjaka;
	private Date datumKupovine;
	private BigDecimal ukupnaCijena;
	private String kupac; // String koji predstavlja konkatenaciju Prezime + Ime ili naziv trgovine ukoliko je trgovina u pitanju
	// Dio promjenljivih potreban za prikaz detaljno
	private BigDecimal cijenaMeda; // Ukupna cijena kupljenog meda
	private double kolicinaMeda; // Kolika je ukupna kolicina meda kupljena
	private BigDecimal cijenaPropolisa; // Ukupna cijena kupljenog propolisa
	private int kolicinaPropolisa; // Kolika je kolicina propolisa kupljena
	
	public int getIdKupovine() {
		return IdKupovine;
	}
	public void setIdKupovine(int idKupovine) {
		IdKupovine = idKupovine;
	}
	public int getIdKupca() {
		return IdKupca;
	}
	public void setIdKupca(int idKupca) {
		IdKupca = idKupca;
	}
	public int getIdPcelinjaka() {
		return IdPcelinjaka;
	}
	public void setIdPcelinjaka(int idPcelinjaka) {
		IdPcelinjaka = idPcelinjaka;
	}
	public Date getDatumKupovine() {
		return datumKupovine;
	}
	public void setDatumKupovine(Date datumKupovine) {
		this.datumKupovine = datumKupovine;
	}
	public BigDecimal getUkupnaCijena() {
		return ukupnaCijena;
	}
	public void setUkupnaCijena(BigDecimal ukupnaCijena) {
		this.ukupnaCijena = ukupnaCijena;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	public BigDecimal getCijenaMeda() {
		return cijenaMeda;
	}
	public void setCijenaMeda(BigDecimal cijenaMeda) {
		this.cijenaMeda = cijenaMeda;
	}
	public double getKolicinaMeda() {
		return kolicinaMeda;
	}
	public void setKolicinaMeda(double kolicinaMeda) {
		this.kolicinaMeda = kolicinaMeda;
	}
	public BigDecimal getCijenaPropolisa() {
		return cijenaPropolisa;
	}
	public void setCijenaPropolisa(BigDecimal cijenaPropolisa) {
		this.cijenaPropolisa = cijenaPropolisa;
	}
	public int getKolicinaPropolisa() {
		return kolicinaPropolisa;
	}
	public void setKolicinaPropolisa(int kolicinaPropolisa) {
		this.kolicinaPropolisa = kolicinaPropolisa;
	}
	
	public KupovinaDetaljno(int idKupovine, int idKupca, int idPcelinjaka, Date datumKupovine, BigDecimal ukupnaCijena,
			String kupac, BigDecimal cijenaMeda, double kolicinaMeda, BigDecimal cijenaPropolisa,
			int kolicinaPropolisa) {
	
		IdKupovine = idKupovine;
		IdKupca = idKupca;
		IdPcelinjaka = idPcelinjaka;
		this.datumKupovine = datumKupovine;
		this.ukupnaCijena = ukupnaCijena;
		this.kupac = kupac;
		this.cijenaMeda = cijenaMeda;
		this.kolicinaMeda = kolicinaMeda;
		this.cijenaPropolisa = cijenaPropolisa;
		this.kolicinaPropolisa = kolicinaPropolisa;
	}
	
	
	
	
	
}
