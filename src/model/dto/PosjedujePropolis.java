package model.dto;

public class PosjedujePropolis {
	
	private int IdPropolisa;
	private int PCELINJAK_IdPcelinjaka;
	private int Kolicina;
	
	public int getIdPropolisa() {
		return IdPropolisa;
	}
	public void setIdPropolisa(int idPropolisa) {
		IdPropolisa = idPropolisa;
	}
	public int getPCELINJAK_IdPcelinjaka() {
		return PCELINJAK_IdPcelinjaka;
	}
	public void setPCELINJAK_IdPcelinjaka(int pCELINJAK_IdPcelinjaka) {
		PCELINJAK_IdPcelinjaka = pCELINJAK_IdPcelinjaka;
	}
	public int getKolicina() {
		return Kolicina;
	}
	public void setKolicina(int kolicina) {
		Kolicina = kolicina;
	}
	public PosjedujePropolis(int idPropolisa, int pCELINJAK_IdPcelinjaka, int kolicina) {
		
		IdPropolisa = idPropolisa;
		PCELINJAK_IdPcelinjaka = pCELINJAK_IdPcelinjaka;
		Kolicina = kolicina;
	}
	
	

}
