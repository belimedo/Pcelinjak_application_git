package model.dto;

public class PosjedujeMed {
	
	private int IZVRCANI_MED_IdMeda;
	private int PCELINJAK_IdPcelinjaka;
	private double Kolicina;
	
	public int getIZVRCANI_MED_IdMeda() {
		return IZVRCANI_MED_IdMeda;
	}
	public void setIZVRCANI_MED_IdMeda(int iZVRCANI_MED_IdMeda) {
		IZVRCANI_MED_IdMeda = iZVRCANI_MED_IdMeda;
	}
	public int getPCELINJAK_IdPcelinjaka() {
		return PCELINJAK_IdPcelinjaka;
	}
	public void setPCELINJAK_IdPcelinjaka(int pCELINJAK_IdPcelinjaka) {
		PCELINJAK_IdPcelinjaka = pCELINJAK_IdPcelinjaka;
	}
	public double getKolicina() {
		return Kolicina;
	}
	public void setKolicina(double kolicina) {
		Kolicina = kolicina;
	}
	public PosjedujeMed(int iZVRCANI_MED_IdMeda, int pCELINJAK_IdPcelinjaka, double kolicina) {
	
		IZVRCANI_MED_IdMeda = iZVRCANI_MED_IdMeda;
		PCELINJAK_IdPcelinjaka = pCELINJAK_IdPcelinjaka;
		Kolicina = kolicina;
	}
	
	
	
}
