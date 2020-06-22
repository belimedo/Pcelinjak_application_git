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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.DrustvoDao;
import model.dto.Pcelinjak;
import util.PopUpWindow;

public class DodajDrustvoController extends Application {
	
	@FXML
	private TextField textfieldRed;
	@FXML
	private TextField textfieldPozicijaURedu;
	@FXML
	private TextField textfieldBrojSanduka;
	@FXML
	private TextField textfieldBrojRamova;
	@FXML
	private TextField textfieldVelicinaLegla;
	@FXML
	private TextField textfieldRezervaMeda;
	@FXML
	private TextField textfieldBoja;
	@FXML
	private Button buttonAccept;
	
	@FXML 
	private Stage thisStage;
	
	private int IdPcelinjaka;
	private UpravljajDrustvimaController callerController = null;
	
	public DodajDrustvoController() {}
	
	public DodajDrustvoController(int idPcelinjaka, UpravljajDrustvimaController udc) {
		
		IdPcelinjaka = idPcelinjaka;
		callerController = udc;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajDrustvoForm.fxml"));
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
	
	
	public void addDrustvo() {
		
		String errorMessage = "";
		
		int red				= -1;
		int pozicijaURedu 	= -1; 
		byte brojSanduka 	= -1;
		byte brojRamova 	= -1;
		byte velicinaLegla	= -1;
		byte rezervaMeda	= -1;
		
		DrustvoDao drustvoDao = new DrustvoDao();
		
		try {
			red = Integer.parseInt(textfieldRed.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Red. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldRed.setStyle("-fx-border-color: red");
			textfieldRed.setText("");
		}
		if (red < 0 || textfieldRed.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Red. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldRed.setStyle("-fx-border-color: red");
			textfieldRed.setText("");
		}
		else {
			textfieldRed.setStyle("");
		}
		
		
		try {
			pozicijaURedu = Integer.parseInt(textfieldPozicijaURedu.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Pozicija u redu. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldPozicijaURedu.setStyle("-fx-border-color: red");
			textfieldPozicijaURedu.setText("");
		}
		
		if ( pozicijaURedu < drustvoDao.getLastPositionInRow(red, IdPcelinjaka) || textfieldPozicijaURedu.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Pozicija u redu. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldPozicijaURedu.setStyle("-fx-border-color: red");
			textfieldPozicijaURedu.setText("");
		}
		else {
			textfieldPozicijaURedu.setStyle("");
		}
		
		try {
			brojSanduka = Byte.parseByte(textfieldBrojSanduka.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Broj sanduka. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojSanduka.setStyle("-fx-border-color: red");
			textfieldBrojSanduka.setText("");
		}
		if (brojSanduka < 0 || textfieldBrojSanduka.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Broj sanduka. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojSanduka.setStyle("-fx-border-color: red");
			textfieldBrojSanduka.setText("");
		}
		else {
			textfieldBrojSanduka.setStyle("");
		}
		
		try {
			brojRamova = Byte.parseByte(textfieldBrojRamova.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Broj ramova. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojRamova.setStyle("-fx-border-color: red");
			textfieldBrojRamova.setText("");
		}
		if (brojRamova < 0 || textfieldBrojRamova.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Broj ramova. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldBrojRamova.setStyle("-fx-border-color: red");
			textfieldBrojRamova.setText("");
		}
		else {
			textfieldBrojRamova.setStyle("");
		}
		
		
		try {
			velicinaLegla = Byte.parseByte(textfieldVelicinaLegla.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Veličina legla. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldVelicinaLegla.setStyle("-fx-border-color: red");
			textfieldVelicinaLegla.setText("");
		}
		if (velicinaLegla < 0 || textfieldVelicinaLegla.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Veličina legla. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldVelicinaLegla.setStyle("-fx-border-color: red");
			textfieldVelicinaLegla.setText("");
		}
		else {
			textfieldVelicinaLegla.setStyle("");
		}
		
		try {
			rezervaMeda = Byte.parseByte(textfieldRezervaMeda.getText());
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Rezerva meda. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldRezervaMeda.setStyle("-fx-border-color: red");
			textfieldRezervaMeda.setText("");
		}
		if (rezervaMeda < 0 || textfieldRezervaMeda.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Rezerva meda. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldRezervaMeda.setStyle("-fx-border-color: red");
			textfieldRezervaMeda.setText("");
		}
		else {
			textfieldRezervaMeda.setStyle("");
		}
		
		String boja = textfieldBoja.getText();
		
		if (boja == null || boja.length() == 0) {
			
			errorMessage +="Unesite ponovo vrijednost Boja drustva\n";
			textfieldBoja.setStyle("-fx-border-color: red");
			textfieldBoja.setText("");
		}
		else {

			textfieldBoja.setStyle("");
		}
		
		if (errorMessage.length() > 0) {
			PopUpWindow.showMessage("Greška", "Greška pri unosu parametara.", errorMessage);
		}
		else {
			
			
			if (drustvoDao.addDrustvo(brojSanduka, velicinaLegla, rezervaMeda, red, pozicijaURedu, boja, brojRamova, IdPcelinjaka) > 0) {
				
				backToPreviousForm();
			}else {
				PopUpWindow.showMessage("Greška", "Neuspješno dodavanje društva", "Molim Vas provjerite da li ste popunili odgovarajuće parametre!");
			}
		}
	}
	
	public void backToPreviousForm() {
		
		if(callerController != null) {
			
			callerController.initializeScene();
			thisStage.close();
		}
		
	}


}
