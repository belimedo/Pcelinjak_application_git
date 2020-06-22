package model.dto;

import java.sql.Date;
import java.math.BigDecimal;

public class Kupovina {
	
	private int  IdKupovine;
	private int  KUPAC_IdKupca;
	private int  PCELINJAK_IdPcelinjaka;
	private Date datumKupovine;
	private BigDecimal cijena;
	
	public int getIdKupovine() {
		return IdKupovine;
	}
	public void setIdKupovine(int idKupovine) {
		IdKupovine = idKupovine;
	}
	public int getKUPAC_IdKupca() {
		return KUPAC_IdKupca;
	}
	public void setKUPAC_IdKupca(int kUPAC_IdKupca) {
		KUPAC_IdKupca = kUPAC_IdKupca;
	}
	public int getPCELINJAK_IdPcelinjaka() {
		return PCELINJAK_IdPcelinjaka;
	}
	public void setPCELINJAK_IdPcelinjaka(int pCELINJAK_IdPcelinjaka) {
		PCELINJAK_IdPcelinjaka = pCELINJAK_IdPcelinjaka;
	}
	public Date getDatumKupovine() {
		return datumKupovine;
	}
	public void setDatumKupovine(Date datumKupovine) {
		this.datumKupovine = datumKupovine;
	}
	public BigDecimal getCijena() {
		return cijena;
	}
	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}
	public Kupovina(int idKupovine, int kUPAC_IdKupca, int pCELINJAK_IdPcelinjaka, Date datumKupovine,
			BigDecimal cijena) {

		IdKupovine = idKupovine;
		KUPAC_IdKupca = kUPAC_IdKupca;
		PCELINJAK_IdPcelinjaka = pCELINJAK_IdPcelinjaka;
		this.datumKupovine = datumKupovine;
		this.cijena = cijena;
	}
	
	

}
