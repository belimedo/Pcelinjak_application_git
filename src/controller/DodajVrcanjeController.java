package controller;

import java.math.BigDecimal;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.IzvrcaniMedDao;
import model.dao.LijeciDao;
import model.dao.VrcaMedDao;
import model.dto.IzvrcaniMed;
import model.dto.Lijeci;
import util.PopUpWindow;

public class DodajVrcanjeController extends Application {
	
	@FXML
	private ChoiceBox<String> cbVrstaMeda;
	@FXML
	private TextField textfieldNovaVrstaMeda;
	@FXML
	private TextField textfieldKolicinaMeda;
	@FXML
	private TextField textfieldCijena;
	@FXML
	private CheckBox checkboxNoviMed;
	
	private UpravljajDrustvimaController callerController;
	
	@FXML
	private Stage thisStage;
	private int IdDrustva;
	private int IdZaposlenog;
	
	public DodajVrcanjeController(int idDrustva,int idZaposlenog,UpravljajDrustvimaController controller) {
		
		IdDrustva = idDrustva;
		IdZaposlenog = idZaposlenog;
		callerController = controller;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajVrcanjeForm.fxml"));
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
		
		LinkedList<IzvrcaniMed> izvrcaniMed = (LinkedList<IzvrcaniMed>) new IzvrcaniMedDao().getAllIzvracniMed();
		LinkedList<String> vrsteMeda= new LinkedList<String>();
		for(IzvrcaniMed im : izvrcaniMed) {
			vrsteMeda.add(im.getVrsta());
		}
		if(vrsteMeda.size() > 0)
			cbVrstaMeda.setValue(vrsteMeda.get(0));
		cbVrstaMeda.setItems(FXCollections.observableArrayList(vrsteMeda));
		checkboxNoviMed.setSelected(false); // Ovo mozda ne treba
	}
	
	public void addVrcanje() {
		
		if(checkboxNoviMed.isSelected()) { // Ako je selektovano, znaci da trebamo dodati novu vrstu lijeka u lijecenje
			
			String vrstaMeda = textfieldNovaVrstaMeda.getText();
			String errorMessage = "";
			double cijenaMeda = 0;
			double kolicinaMeda = 0;
			
			if (vrstaMeda == null || vrstaMeda.length() == 0) {
				
				errorMessage += "Potrebno je ispravno unijeti naziv novog lijeka ukoliko ste označili checkbox!\n";
				textfieldNovaVrstaMeda.setStyle("-fx-border-color: red");
				textfieldNovaVrstaMeda.setText("");
			}
			else {
				textfieldNovaVrstaMeda.setStyle("");
			}
			
			try {
				cijenaMeda = Double.parseDouble(textfieldCijena.getText());
			}catch(NumberFormatException ex) {
				
				errorMessage += "Unesite ponovo vrijednost Cijena meda. Vrijednost mora biti pozitivan broj.\n";
				textfieldCijena.setStyle("-fx-border-color: red");
				textfieldCijena.setText("");
			}
			
			if ( cijenaMeda < 0 || textfieldCijena.getText().length() == 0) {
				
				errorMessage += "Unesite ponovo vrijednost Cijena meda. Vrijednost mora biti pozitivan broj.\n";
				textfieldCijena.setStyle("-fx-border-color: red");
				textfieldCijena.setText("");
			}
			else {
				textfieldCijena.setStyle("");
			}
			
			try {
				kolicinaMeda = Integer.parseInt(textfieldKolicinaMeda.getText());
			}catch(NumberFormatException ex) {
				
				errorMessage += "Unesite ponovo vrijednost Kolicina izvrcanog meda. Vrijednost mora biti pozitivan broj.\n";
				textfieldKolicinaMeda.setStyle("-fx-border-color: red");
				textfieldKolicinaMeda.setText("");
			}
			
			if ( kolicinaMeda < 0  || textfieldKolicinaMeda.getText().length() == 0) {
				
				errorMessage += "Unesite ponovo vrijednost Kolicina izvrcanog meda. Vrijednost mora biti pozitivan broj.\n";
				textfieldKolicinaMeda.setStyle("-fx-border-color: red");
				textfieldKolicinaMeda.setText("");
			}
			else {
				textfieldKolicinaMeda.setStyle("");
			}
			
			if (errorMessage.length() > 0) {
				PopUpWindow.showMessage("Greška", "Greška pri unosu parametara.", errorMessage);
			}
			else {
				// Dodati novu vrstu izvrcanog meda, zatim dodati tabelu vrca_med
				new IzvrcaniMedDao().addNewIzvrcaniMed(vrstaMeda, kolicinaMeda, BigDecimal.valueOf(cijenaMeda));
				new VrcaMedDao().addNewVrcaMed(vrstaMeda, kolicinaMeda, IdDrustva, IdZaposlenog);
				backToPreviousForm();
			}
			
		}else {
			
			String errorMessage = "";
			String vrstaMeda = cbVrstaMeda.getValue();
			double kolicinaMeda = 0;
			
			if(vrstaMeda == null || vrstaMeda.length() == 0) {
				
				errorMessage += "Potrebno je izabrati vrstu meda koja se vrca.\nUkoliko vrsta ne postoji molim Vas da je dodate pritiskom na checkbox i popunjavanjem odgovarajucih polja.\n";
				cbVrstaMeda.setStyle("-fx-border-color: red");
			}
			else {
				cbVrstaMeda.setStyle("");
			}
			
			try {
				kolicinaMeda = Integer.parseInt(textfieldKolicinaMeda.getText());
			}catch(NumberFormatException ex) {
				
				errorMessage += "Unesite ponovo vrijednost Kolicina izvrcanog meda. Vrijednost mora biti pozitivan broj.\n";
				textfieldKolicinaMeda.setStyle("-fx-border-color: red");
				textfieldKolicinaMeda.setText("");
			}
			
			if ( kolicinaMeda < 0  || textfieldKolicinaMeda.getText().length() == 0) {
				
				errorMessage += "Unesite ponovo vrijednost Kolicina izvrcanog meda. Vrijednost mora biti pozitivan broj.\n";
				textfieldKolicinaMeda.setStyle("-fx-border-color: red");
				textfieldKolicinaMeda.setText("");
			}
			else {
				textfieldKolicinaMeda.setStyle("");
			}
			
			if (errorMessage.length() > 0) {
				PopUpWindow.showMessage("Greška", "Greška pri unosu parametara.", errorMessage);
			}
			else {
				
				new VrcaMedDao().addNewVrcaMed(vrstaMeda, kolicinaMeda, IdDrustva, IdZaposlenog);
				backToPreviousForm();
			}
		}
		
		
	}
	
	public void backToPreviousForm() {
		callerController.initializeScene();
		callerController = null;
		thisStage.close();
	}

}
