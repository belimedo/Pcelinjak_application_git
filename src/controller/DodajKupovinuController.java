package controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.FizickoLiceDao;
import model.dao.InformacijePcelinjakDao;
import model.dao.IzvrcaniMedDao;
import model.dao.KupacDao;
import model.dao.KupovinaDao;
import model.dao.KupovinaDetaljnoDao;
import model.dao.PcelinjakDao;
import model.dao.PosjedujeMedDao;
import model.dao.PosjedujePropolisDao;
import model.dao.PropolisDao;
import model.dao.StavkaMedDao;
import model.dao.StavkaPropolisDao;
import model.dao.TrgovinaDao;
import model.dto.InformacijePcelinjak;
import model.dto.IzvrcaniMed;
import model.dto.KupovinaDetaljno;
import model.dto.PosjedujeMed;
import model.dto.PosjedujePropolis;
import util.PopUpWindow;

public class DodajKupovinuController extends Application {
	
	
	@FXML
	private Button buttonPovratakNazad;
	@FXML
	private Button buttonNovoFizickoLice;
	@FXML
	private Button buttonNovaTrgovina;
	@FXML
	private Button buttonPotvrdi;
	@FXML
	private TextField textfieldKolicinaMeda;
	@FXML
	private TextField textfieldKolicinaPropolisa;
	@FXML
	private ChoiceBox<String> cbVrstaMeda;
	@FXML
	private ChoiceBox<String> cbVrstaPropolisa;
	@FXML
	private ChoiceBox<String> cbKupac;
	
	private Stage thisStage;
	private UpravljajKupovinamaController callerController = null;
	private int IdPcelinjaka;
	
	public DodajKupovinuController(int idPcelinjaka, UpravljajKupovinamaController controller) {
		IdPcelinjaka = idPcelinjaka;
		callerController = controller;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajKupovinuForm.fxml"));
		loader.setController(this);
		Parent root = loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Pcelinjak - Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setOnCloseRequest(e->{
            e.consume();
            backToPreviousForm();
        });
        primaryStage.show();
        thisStage = primaryStage;
		
	}
	
	public void initializeScene() {
		
		List<String> vrsteMeda = new LinkedList<String>();
		List<String> vrstePropolisa = new LinkedList<String>();
		HashMap<String,Integer> kupci = new HashMap<String,Integer>();
		
		
		IzvrcaniMedDao imd = new IzvrcaniMedDao();
		PropolisDao pd = new PropolisDao();
		PosjedujeMedDao pmd = new PosjedujeMedDao();
		PosjedujePropolisDao ppd = new PosjedujePropolisDao();
		
		
		FizickoLiceDao fld = new FizickoLiceDao();
		TrgovinaDao trd = new TrgovinaDao();
		KupacDao kupacDao = new KupacDao();
		
		int maxIdKupca = kupacDao.getMaxIdKupca();
		for (int i = 0; i <= maxIdKupca; i++) {
			if(fld.getByIdKupca(i) != null) {
				kupci.put(fld.getByIdKupca(i).getPezime()+" "+fld.getByIdKupca(i).getIme(),i);
			}
			if(trd.getByIdKupca(i) != null) {
				kupci.put(trd.getByIdKupca(i).getNaziv(),i);
			}
		}
		
		if(kupci.size() > 0) {
			cbKupac.setValue(kupci.keySet().toArray(new String[0])[0]);
		}
		
		LinkedList<PosjedujeMed> posjedujeMed = (LinkedList<PosjedujeMed>) pmd.getByIdPcelinjaka(IdPcelinjaka);
		LinkedList<PosjedujePropolis> posjedujePropolis = (LinkedList<PosjedujePropolis>) ppd.getByIdPcelinjaka(IdPcelinjaka);
		
		if(posjedujeMed != null) {
			for(PosjedujeMed m : posjedujeMed) {
					vrsteMeda.add(imd.getByIdMeda(m.getIZVRCANI_MED_IdMeda()).getVrsta());
			}
			vrsteMeda.add("-");
			cbVrstaMeda.setValue(vrsteMeda.get(0));
		}
		if(posjedujePropolis != null) {
			for(PosjedujePropolis p : posjedujePropolis) {
				vrstePropolisa.add(pd.getByIdPropolisa(p.getIdPropolisa()).getVrsta());
			}
			vrstePropolisa.add("-");
			cbVrstaPropolisa.setValue(vrstePropolisa.get(0));
		}
		
		cbVrstaMeda.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbVrstaMeda.setValue(newName); });
		
		cbVrstaPropolisa.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbVrstaPropolisa.setValue(newName); });
		
		cbKupac.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbKupac.setValue(newName); });
		
		cbVrstaMeda.setItems(FXCollections.observableArrayList(vrsteMeda));
		cbVrstaPropolisa.setItems(FXCollections.observableArrayList(vrstePropolisa));
		cbKupac.setItems(FXCollections.observableArrayList(kupci.keySet()));
	}
	
	public void addKupovina() {
		
		String errorMessage = "";
		String vrstaMeda = "";
		String vrstaPropolisa = "";
		double kolicinaMeda = -1;
		int kolicinaProp 	= -1;
		
		// Da testiram je li kolicina koja se uzima vise nego sto pcelinjak posjeduje
		PropolisDao pd = new PropolisDao();
		IzvrcaniMedDao imd = new IzvrcaniMedDao();
		PosjedujePropolisDao ppd = new PosjedujePropolisDao();
		PosjedujeMedDao pmd = new PosjedujeMedDao();
		StavkaMedDao stavkaMedDao = new StavkaMedDao();
		StavkaPropolisDao stavkaPropDao = new StavkaPropolisDao();
		KupovinaDao kupovinaDao = new KupovinaDao();
		
		FizickoLiceDao fld = new FizickoLiceDao();
		TrgovinaDao trd = new TrgovinaDao();
		KupacDao kupacDao = new KupacDao();
		

		HashMap<String,Integer> kupci = new HashMap<String,Integer>();
		LinkedList<PosjedujeMed> posjedujeMed = (LinkedList<PosjedujeMed>) pmd.getByIdPcelinjaka(IdPcelinjaka);
		LinkedList<PosjedujePropolis> posjedujePropolis = (LinkedList<PosjedujePropolis>) ppd.getByIdPcelinjaka(IdPcelinjaka);
		
		int maxIdKupca = kupacDao.getMaxIdKupca();
		for (int i = 0; i <= maxIdKupca; i++) {
			if(fld.getByIdKupca(i) != null) {
				kupci.put(fld.getByIdKupca(i).getPezime()+" "+fld.getByIdKupca(i).getIme(),i);
			}
			if(trd.getByIdKupca(i) != null) {
				kupci.put(trd.getByIdKupca(i).getNaziv(),i);
			}
		}
		
		PosjedujeMed izabraniMed = null;
		PosjedujePropolis izabraniPropolis = null;
		int idKupca = kupci.get(cbKupac.getValue());
		
		if(cbVrstaMeda.getValue().compareTo("-") != 0) { // Ukoliko neko zeli da kupi iskljucivo propolis 
			
			vrstaMeda = cbVrstaMeda.getValue();
			
			for(PosjedujeMed m : posjedujeMed ) {
				
				if(imd.getByIdMeda(m.getIZVRCANI_MED_IdMeda()).getVrsta().compareTo(vrstaMeda) == 0 ) {
					izabraniMed = m;
					break;
				}
			}
			
			try {
				kolicinaMeda = Double.parseDouble(textfieldKolicinaMeda.getText());
			}catch(NumberFormatException ex) {
				
				errorMessage += "Unesite ponovo vrijednost Kolicina meda. Vrijednost mora biti pozitivan broj, manji od ukupne količine meda koja iznosi "
						+ izabraniMed.getKolicina() +".\n";
				textfieldKolicinaMeda.setStyle("-fx-border-color: red");
				textfieldKolicinaMeda.setText("");
			}
			
			if ( kolicinaMeda < 0 || kolicinaMeda > izabraniMed.getKolicina() || textfieldKolicinaMeda.getText().length() == 0) {
				
				errorMessage += "Unesite ponovo vrijednost Kolicina meda. Vrijednost mora biti pozitivan broj, manji od ukupne količine meda koja iznosi "
						+ izabraniMed.getKolicina() +".\n";
				textfieldKolicinaMeda.setStyle("-fx-border-color: red");
				textfieldKolicinaMeda.setText("");
			}
			else {
				textfieldKolicinaMeda.setStyle("");
			}
		}
		
		if (cbVrstaPropolisa.getValue().compareTo("-") != 0) {
			
			vrstaPropolisa = cbVrstaPropolisa.getValue();
			
			for(PosjedujePropolis p : posjedujePropolis ) {
				if(pd.getByIdPropolisa(p.getIdPropolisa()).getVrsta().compareTo(vrstaPropolisa) == 0) {
					izabraniPropolis = p;
					break;
				}
			}
			
			try {
				kolicinaProp = Integer.parseInt(textfieldKolicinaPropolisa.getText());
			}catch(NumberFormatException ex) {
				
				errorMessage += "Unesite ponovo vrijednost Kolicina propolisa. Vrijednost mora biti pozitivan broj, manji od ukupne količine propolisa koja iznosi "
						+ izabraniPropolis.getKolicina() +".\n";
				textfieldKolicinaPropolisa.setStyle("-fx-border-color: red");
				textfieldKolicinaPropolisa.setText("");
			}
			
			if ( kolicinaProp < 0 || kolicinaProp > izabraniPropolis.getKolicina() || textfieldKolicinaPropolisa.getText().length() == 0) {
				
				errorMessage += "Unesite ponovo vrijednost Kolicina propolisa. Vrijednost mora biti pozitivan broj, manji od ukupne količine propolisa koja iznosi "
						+ izabraniPropolis.getKolicina() +".\n";
				textfieldKolicinaPropolisa.setStyle("-fx-border-color: red");
				textfieldKolicinaPropolisa.setText("");
			}
			else {
				textfieldKolicinaPropolisa.setStyle("");
			}
		}
		// Sada je potrebno generisati kupovinu, zatim generisati stavka med i stavka propolis
		if(izabraniMed == null && izabraniPropolis == null) {
			errorMessage += "Potrebno je izabrati jednu od stavki na prodaju i unijeti korektne vrijednosti!";
		}
		if(errorMessage.length() == 0) {
			
			double cijenaUkupno =0;
			double cijenaPropolis = -1;
			double cijenaMed = -1;
			
			if(izabraniMed != null) {
				cijenaMed = imd.getByIdMeda(izabraniMed.getIZVRCANI_MED_IdMeda()).getCijena().doubleValue() * kolicinaMeda;
				cijenaUkupno += cijenaMed;
			}
			if(izabraniPropolis != null) {
				cijenaPropolis = pd.getByIdPropolisa(izabraniPropolis.getIdPropolisa()).getCijena().doubleValue() * kolicinaProp;
				cijenaUkupno += cijenaPropolis;
			}
			
			kupovinaDao.addKupovina(idKupca, IdPcelinjaka, BigDecimal.valueOf(cijenaUkupno));
			int idKupovine = kupovinaDao.getMaxIdKupovine();
			if(izabraniMed != null)
				stavkaMedDao.addStavkaMed(idKupovine, izabraniMed.getIZVRCANI_MED_IdMeda(), kolicinaMeda, BigDecimal.valueOf(cijenaMed));
			if(izabraniPropolis != null)
				stavkaPropDao.addStavkaPropolis(idKupovine, izabraniPropolis.getIdPropolisa(), kolicinaProp, BigDecimal.valueOf(cijenaPropolis));
			
			PopUpWindow.showMessage("Uspješna akcija", "Uspješno ste izvršili kupovinu.","Kupovinu je obavio kupac " + cbKupac.getValue()+ " u iznosu od " + cijenaUkupno);
			backToPreviousForm();
		}else {
			PopUpWindow.showMessage("Greška", "Neuspješno dodavanje kupovine",errorMessage);
		}
		
	}
	
	
	public void addFizickoLice() {
		
		DodajKupcaFizickoLiceController dkflc = new DodajKupcaFizickoLiceController(this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        dkflc.start(stage);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                });
                timer.cancel();
            }
        }, 0);
        
	}
	
	public void addTrgovina() {
		
		DodajKupcaTrgovinaController dktc = new DodajKupcaTrgovinaController(this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        dktc.start(stage);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                });
                timer.cancel();
            }
        }, 0);
		
		
	}
	
	public void backToPreviousForm() {
		
		if (callerController != null) {
			callerController.initializeScene();
			callerController = null;
			thisStage.close();
		}
	}
	
	

}
