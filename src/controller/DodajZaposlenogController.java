package controller;



import java.math.BigDecimal;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.ZaposleniDao;
import util.PopUpWindow;

public class DodajZaposlenogController extends Application {
	
	
	@FXML
	private TextField textfieldIme;
	@FXML
	private TextField textfieldPrezime;
	@FXML
	private TextField textfieldJMBG;
	@FXML
	private TextField textfieldPlata;
	@FXML
	private TextField textfieldKorisnickoIme;
	@FXML
	private PasswordField passfieldLozinka;
	@FXML 
	private Button	buttonAccept;
	
	@FXML
	private Stage thisStage;
	
	private int idPcelinjaka;
	
	public static int addedZaposleni = 0; // Staticki blok koji se inicijalizuje samo jednom, provjera da li je dovoljan broj zapolenih dodat se moze odaditi provjerom ovog broja prije i poslije dodavanja.
	
	public DodajZaposlenogController(int pIdPcelinjaka) {
		
		this.idPcelinjaka = pIdPcelinjaka;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajZaposlenogForm.fxml"));
		loader.setController(this);
		Parent root = loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Pcelinjak - Dodaj zaposlenog");
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
	
	public void addZaposlenog() {
		
		String errorMessage = "";
		
		String ime = textfieldIme.getText();
		
		if (ime == null || ime.length() == 0) {
			
			errorMessage +="Unesite ponovo vrijednost Ime zaposlenog\n";
			textfieldIme.setStyle("-fx-border-color: red");
			textfieldIme.setText("");
		}
		else {
			textfieldIme.setStyle("");
		}
		
		String prezime = textfieldPrezime.getText();
		
		if (prezime == null || prezime.length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Prezime zaposlenog\n";
			textfieldPrezime.setStyle("-fx-border-color: red");
			textfieldPrezime.setText("");
		}
		else {
			textfieldPrezime.setStyle("");
		}
		
		String JMBG = textfieldJMBG.getText();
		
		if (JMBG == null || JMBG.length() != 13) {
			
			errorMessage += "Unesite ponovo vrijednost JBMG zaposlenog.JMBG mora sadrzavati 13 cifara.\n";
			textfieldPrezime.setStyle("-fx-border-color: red");
			textfieldPrezime.setText("");
		}
		else {
			try {
				Integer.parseInt(JMBG); // Samo za provjeru da li je svih 13 karaktera integer vrijednost
			}
			catch (NumberFormatException ex){
				
				errorMessage += "Unesite ponovo vrijednost JBMG zaposlenog.JMBG mora sadrzavati 13 cifara.\n";
				textfieldPrezime.setStyle("-fx-border-color: red");
				textfieldPrezime.setText("");
			}
			textfieldPrezime.setStyle("");
		}
		
		BigDecimal plata = BigDecimal.valueOf(0);
		
		try {
			BigDecimal.valueOf(Double.parseDouble(textfieldPlata.getText())); // Parsiramo tekst u double pa zatim u BigDecimal	
		}catch(NumberFormatException ex) {
			
			errorMessage += "Unesite ponovo vrijednost Plata zaposlneog. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldPlata.setStyle("-fx-border-color: red");
			textfieldPlata.setText("");
		}
		if (plata.intValue() < 0 || textfieldPlata.getText().length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Plata zaposlneog. Vrijednost mora biti pozitivan cjelobrojni broj.\n";
			textfieldPlata.setStyle("-fx-border-color: red");
			textfieldPlata.setText("");
		}
		else {
			textfieldPlata.setStyle("");
		}
		
		String korisnickoIme = textfieldKorisnickoIme.getText();
		
		if (korisnickoIme == null || korisnickoIme.length() == 0) {
			
			errorMessage +="Unesite ponovo vrijednost Korisničko ime zaposlenog\n";
			textfieldKorisnickoIme.setStyle("-fx-border-color: red");
			textfieldKorisnickoIme.setText("");
		}
		else {
			textfieldKorisnickoIme.setStyle("");
		}
		
		String lozinka = passfieldLozinka.getText();
		
		if (lozinka == null || lozinka.length() < 4) {
			
			errorMessage += "Unesite ponovo vrijednost Lozinka zaposlenog. Lozinka mora biti duza od 4 karaktera.\n";
			passfieldLozinka.setStyle("-fx-border-color: red");
			passfieldLozinka.setText("");
		}
		else {
			passfieldLozinka.setStyle("");
		}
		
		if (errorMessage.length() > 0) {
			PopUpWindow.showMessage("Greška", "Greška pri unosu parametara.", errorMessage);
		}
		else {
			new ZaposleniDao().addZaposleni(plata, korisnickoIme, lozinka, JMBG, korisnickoIme, prezime, idPcelinjaka);
			addedZaposleni++;
			thisStage.close();
		}
		
		
	}

}



