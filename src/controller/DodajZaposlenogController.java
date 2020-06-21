package controller;



import java.math.BigDecimal;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.ZaposleniDao;
import model.dto.Zaposleni;
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
	
	public static int addedZaposleni = 0; // Staticki blok koji se inicijalizuje samo jednom, provjera da li je dovoljan broj zapolenih dodat se moze odaditi provjerom ovog broja prije i poslije dodavanja.
	private static LinkedList<Zaposleni> zaposleniZaDodavanje = null;
	
	private Stage thisStage;
	private int maxZaposlenih; // Ovaj podatak clan nam je potreban kada dodajemo nekoliko zaposlenih da znamo kada je potrebno vratiti se nazad
	private int IdPcelinjaka;
	private DodajPcelinjakController pcelinjakController = null; // Kada pozivamo iz dodajPcelinjak
	private UpravljajZaposlenimaController zaposleniController = null; // Kada pozivamo iz upravljajZaposlenima
	
	public DodajZaposlenogController() {
		
	}
	
	public DodajZaposlenogController(int maxZaposlenih,DodajPcelinjakController controller) {
		
		pcelinjakController	= controller;
		this.maxZaposlenih	= maxZaposlenih;
	}
	
	public DodajZaposlenogController(int maxZaposlenih,UpravljajZaposlenimaController controller,int idPcelinjaka) {
		
		zaposleniController	= controller;
		this.maxZaposlenih	= maxZaposlenih;
		IdPcelinjaka = idPcelinjaka;
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
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.toFront();
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
			textfieldJMBG.setStyle("-fx-border-color: red");
			textfieldJMBG.setText("");
		}
		else {
			try {
				Long.parseLong(JMBG); // Samo za provjeru da li je svih 13 karaktera integer vrijednost
			}
			catch (NumberFormatException ex){
				
				errorMessage += "Unesite ponovo vrijednost JBMG zaposlenog.JMBG mora sadrzavati 13 cifara.\n";
				textfieldJMBG.setStyle("-fx-border-color: red");
				textfieldJMBG.setText("");
			}
			textfieldJMBG.setStyle("");
		}
		
		BigDecimal plata = BigDecimal.valueOf(0);
		
		try {
			plata = BigDecimal.valueOf(Double.parseDouble(textfieldPlata.getText())); // Parsiramo tekst u double pa zatim u BigDecimal	
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
			// Ako je ovo prvi zaposleni koji se dodaje, onda se generisati nova lista
			if (zaposleniZaDodavanje == null) {
				
				zaposleniZaDodavanje = new LinkedList<Zaposleni>();
			}
			zaposleniZaDodavanje.add(new Zaposleni(0,korisnickoIme,lozinka,JMBG,ime,prezime,plata,0));
			addedZaposleni++;
			// Ispitujemo da li je broj zaposlenih dostigao zeljeni i ako je pcelinjak kontroler razlicit od null, pozovi metodu za kreiranje
			if(addedZaposleni == maxZaposlenih && pcelinjakController != null) {
				
				pcelinjakController.createPcelinjak(zaposleniZaDodavanje);
				zaposleniZaDodavanje = null;
				pcelinjakController = null;
			}
			if(addedZaposleni == maxZaposlenih && zaposleniController != null) {
				
				Zaposleni z = zaposleniZaDodavanje.get(0);
				new ZaposleniDao().addZaposleni(z.getPlata(), z.getKorisničkoIme(), z.getLozinka(), z.getJMBG(), z.getIme(), z.getPrezime(), IdPcelinjaka);
				zaposleniController.initializeScene();
				zaposleniZaDodavanje = null;
				zaposleniController = null;
			}
				
			System.out.println(addedZaposleni);
			thisStage.close();
		}
		
		
	}

}



