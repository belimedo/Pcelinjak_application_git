package model.dto;

import java.sql.Date;

public class Sanduk {
	
	private byte brojSandukaUDrustvu;
	private Date godinaKupovine;
	private String boja;
	private byte brojRamova;
	private int DRUSTVO_IdDrustva;
	
	public byte getBrojSandukaUDrustvu() {
		return brojSandukaUDrustvu;
	}
	public void setBrojSandukaUDrustvu(byte brojSandukaUDrustvu) {
		this.brojSandukaUDrustvu = brojSandukaUDrustvu;
	}
	public Date getGodinaKupovine() {
		return godinaKupovine;
	}
	public void setGodinaKupovine(Date godinaKupovine) {
		this.godinaKupovine = godinaKupovine;
	}
	public String getBoja() {
		return boja;
	}
	public void setBoja(String boja) {
		this.boja = boja;
	}
	public byte getBrojRamova() {
		return brojRamova;
	}
	public void setBrojRamova(byte brojRamova) {
		this.brojRamova = brojRamova;
	}
	public int getDRUSTVO_IdDrustva() {
		return DRUSTVO_IdDrustva;
	}
	public void setDRUSTVO_IdDrustva(int dRUSTVO_IdDrustva) {
		DRUSTVO_IdDrustva = dRUSTVO_IdDrustva;
	}
	
	public Sanduk(byte brojSandukaUDrustvu, Date godinaKupovine, String boja, byte brojRamova, int dRUSTVO_IdDrustva) {
		
		this.brojSandukaUDrustvu = brojSandukaUDrustvu;
		this.godinaKupovine = godinaKupovine;
		this.boja = boja;
		this.brojRamova = brojRamova;
		DRUSTVO_IdDrustva = dRUSTVO_IdDrustva;
	}
	
	

}
