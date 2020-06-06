package controller;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.dao.PcelinjakDao;
import util.PopUpWindow;

public class DodajPcelinjakController extends Application {
	
	@FXML 
	private Stage thisStage;
	@FXML
	private TextField textfieldNazivPcelinjaka;
	@FXML
	private TextField textfieldAdresaPcelinjaka;
	@FXML
	private TextField textfieldBrojDrustava;
	@FXML
	private TextField textfieldBrojVrcalica;
	@FXML
	private TextField textfieldBrojTegli;
	@FXML
	private TextField textfieldBrojZaposlenih;
	@FXML
	private ChoiceBox<String> cbVlasnik;
	@FXML
	private Button buttonAccept;
	
	private VlasnikController callerController;
	
	public DodajPcelinjakController(VlasnikController callerController) {

		this.callerController = callerController;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajPcelinjakForm.fxml"));
		loader.setController(this);
		Parent root = loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Pcelinjak - Dodaj pčelinjak");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e->{
            e.consume();    
            PopUpWindow.showMessage("Izlazak iz prozora", "Napustate unos", "Izlaskom iz ovog prozora bez potvrdjivanja podataka\n dodavanje pčelinjaka neće biti završeno.");
            primaryStage.close();
        });
        primaryStage.show();
        thisStage = primaryStage;		
	}
	
	@FXML
	public void addPcelinjak() {
		
		//TODO: Procitati sve podatke, parsirati ih, ispraviti ukoliko neki nije dobar dodati, 
		// zatim pozvati formu za radnike odredjen broj puta
		String errorMessage = "";
		
		int brojVrcalica 	= -1; // Postavljeno kao default vrijednost zbog try-catcha
		int brojZaposlenih 	= -1;
		int brojDrustava 	= -1;
		int brojTegli		= -1;
		
		String naziv = textfieldNazivPcelinjaka.getText();
		
		if (naziv == null || naziv.length() == 0) {
			
			errorMessage +="Unesite ponovo vrijednost Naziv pčelinjaka\n";
			textfieldNazivPcelinjaka.setStyle("-fx-border-color: red");
			textfieldNazivPcelinjaka.setText("");
		}
		else {

			textfieldNazivPcelinjaka.setStyle("");
		}
		
		String adresa = textfieldAdresaPcelinjaka.getText();
		
		if (adresa == null || adresa.length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Adresa pčelinjaka\n";
			textfieldAdresaPcelinjaka.setStyle("-fx-border-color: red");
			textfieldAdresaPcelinjaka.setText("");
		}
		else {
			textfieldAdresaPcelinjaka.setStyle("");
		}
		
		try {
			brojVrcalica = Integer.parseInt(textfieldBrojVrcalica.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Broj Vrcalica. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojVrcalica.setStyle("-fx-border-color: red");
			textfieldBrojVrcalica.setText("");
		}
		if (brojVrcalica < 0 || textfieldBrojVrcalica.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Broj Vrcalica. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojVrcalica.setStyle("-fx-border-color: red");
			textfieldBrojVrcalica.setText("");
		}
		else {
			textfieldBrojVrcalica.setStyle("");
		}
		
		try {
			brojZaposlenih = Integer.parseInt(textfieldBrojZaposlenih.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Broj Zaposlenih. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojZaposlenih.setStyle("-fx-border-color: red");
			textfieldBrojZaposlenih.setText("");
		}
		if (brojZaposlenih < 0 || textfieldBrojZaposlenih.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Broj Zaposlenih. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojZaposlenih.setStyle("-fx-border-color: red");
			textfieldBrojZaposlenih.setText("");
		}
		else {
			textfieldBrojZaposlenih.setStyle("");
		}
		
		try {
			brojTegli = Integer.parseInt(textfieldBrojTegli.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Broj Tegli. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojTegli.setStyle("-fx-border-color: red");
			textfieldBrojTegli.setText("");
		}
		if (brojTegli < 0 || textfieldBrojTegli.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Broj Tegli. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojTegli.setStyle("-fx-border-color: red");
			textfieldBrojTegli.setText("");
		}
		else {
			textfieldBrojTegli.setStyle("");
		}
		
		try {
			brojDrustava = Integer.parseInt(textfieldBrojDrustava.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Broj Društava. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojDrustava.setStyle("-fx-border-color: red");
			textfieldBrojDrustava.setText("");
		}
		if (brojDrustava < 0 || textfieldBrojDrustava.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Broj Društava. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojDrustava.setStyle("-fx-border-color: red");
			textfieldBrojDrustava.setText("");
		}
		else {
			textfieldBrojDrustava.setStyle("");
		}
		
		if (errorMessage.length() > 0) {
			PopUpWindow.showMessage("Greška", "Greška pri unosu parametara.", errorMessage);
		}
		else {
			
			PcelinjakDao pd = new PcelinjakDao();
			pd.addPcelinjak(naziv,adresa,brojDrustava,brojVrcalica,brojTegli,brojZaposlenih,callerController.getIdVlasnika());
			DodajZaposlenogController dzc = new DodajZaposlenogController(pd.getByName(naziv).getIdPcelinjaka());
			
			int i = dzc.addedZaposleni;
			if (i + brojZaposlenih >=  dzc.addedZaposleni) {
				
				System.out.println("Usao u dodavanje!");
				
				Timer timer = new Timer();
		        timer.schedule(new TimerTask() {
		        	@Override
		            public void run()
		            { 
		            	
		                Platform.runLater(() ->
		                {
		                    try {
		                     
		                        Stage stage = new Stage();
		                        dzc.start(stage);
		                        
		                    }
		                    catch (Exception ex) {
		                        ex.printStackTrace();
		                    }

		                    
		                });
		                timer.cancel();
		            }
		        	}, 0);
	        	}
		}
		PopUpWindow.showMessage("Uspješno dodavanje", "Dodan pčelinjak", "Uspješno ste dodali novi pčelinjak " + naziv);
		callerController.initializeScene();
		thisStage.close();
		}
		
}
