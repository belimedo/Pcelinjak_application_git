package model.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import model.dto.FizickoLice;
import model.dto.Kupovina;
import model.dto.KupovinaDetaljno;
import model.dto.StavkaMed;
import model.dto.StavkaPropolis;
import model.dto.Trgovina;

public class KupovinaDetaljnoDao {
	
	public KupovinaDetaljnoDao() {}


	public List<KupovinaDetaljno> getByIdPcelinjaka(int IdPcelinjaka) {
		
		KupovinaDao kd = new KupovinaDao();
		TrgovinaDao	td = new TrgovinaDao();
		StavkaMedDao smd = new StavkaMedDao();
		FizickoLiceDao fld = new FizickoLiceDao();
		StavkaPropolisDao spd = new StavkaPropolisDao();
		
		LinkedList<Kupovina> kupovine = (LinkedList<Kupovina>) kd.getAllKupovinaByIdPcelinjaka(IdPcelinjaka);
		// Ukoliko postoje kupovine, tada mozemo dobiti i detalje o njima iz vise drugih tabela
		if (kupovine != null) {
			List<KupovinaDetaljno> kupovineDetaljno = new LinkedList<KupovinaDetaljno>();
			
			for (Kupovina k : kupovine) {
				LinkedList<StavkaMed> med = (LinkedList<StavkaMed>) smd.getAllByIdKupovine(k.getIdKupovine());
				LinkedList<StavkaPropolis> propolis = (LinkedList<StavkaPropolis>) spd.getAllByIdKupovine(k.getIdKupovine());
				FizickoLice fl = fld.getByIdKupca(k.getKUPAC_IdKupca());
				Trgovina tr	= td.getByIdKupca(k.getKUPAC_IdKupca());
				
				// Sada generisemo parametre za KupovinaDetaljno klasu
				String kupac = "";
				if(fl != null) {
					kupac = fl.getPezime() + " " + fl.getIme();
				}else if(tr != null) {
					kupac = tr.getNaziv();
				}
				
				BigDecimal cijenaMeda = new BigDecimal(0);
				double kolicinaMeda = 0;
				if(med != null) {
					for (StavkaMed m:med) {
						cijenaMeda = cijenaMeda.add(m.getCijena());
						kolicinaMeda += m.getKolicina();
					}
				}
				
				BigDecimal cijenaPropolisa = new BigDecimal(0);
				int kolicinaPropolisa = 0;
				if(propolis != null) {
					for (StavkaPropolis p: propolis) {
						cijenaPropolisa = cijenaPropolisa.add(p.getCijena());
						kolicinaPropolisa += p.getKolicina();
					}
				}
				
				// Dodamo novu kupovinu na kraju
				kupovineDetaljno.add(new KupovinaDetaljno(k.getIdKupovine(),k.getKUPAC_IdKupca(),k.getPCELINJAK_IdPcelinjaka(),k.getDatumKupovine(),
						k.getCijena(),kupac,cijenaMeda,kolicinaMeda,cijenaPropolisa,kolicinaPropolisa));
				
			}
			return kupovineDetaljno;
		}
		return null;
	}
}
