package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.FizickoLiceDao;
import model.dao.KupacDao;
import util.PopUpWindow;

public class DodajKupcaFizickoLiceController extends Application {

	@FXML
	private TextField textfieldIme; 
	@FXML
	private TextField textfieldPrezime; 
	@FXML
	private TextField textfieldJMBG;
	@FXML
	private Button buttonContinue;
	@FXML
	private Stage thisStage;
	
	private DodajKupovinuController callerController = null;
	
	public DodajKupcaFizickoLiceController(DodajKupovinuController cc) {
		callerController = cc;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajKupcaFizickoLiceForm.fxml"));
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
	
	private void backToPreviousForm() {
		PopUpWindow.showMessage("Izlazak iz prozora", "Napustate unos", "Izlaskom iz ovog prozora bez potvrdjivanja podataka\n dodavanje fizickog lica neće biti završeno.");
        thisStage.close();
	}
	
	public void addFizickoLice() {
		
		String errorMessage = "";
		
		String ime = textfieldIme.getText();
		
		if (ime == null || ime.length() == 0) {
			
			errorMessage +="Unesite ponovo vrijednost Ime fizičkog lica\n";
			textfieldIme.setStyle("-fx-border-color: red");
			textfieldIme.setText("");
		}
		else {
			textfieldIme.setStyle("");
		}
		
		String prezime = textfieldPrezime.getText();
		
		if (prezime == null || prezime.length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Prezime fizičkog lica\n";
			textfieldPrezime.setStyle("-fx-border-color: red");
			textfieldPrezime.setText("");
		}
		else {
			textfieldPrezime.setStyle("");
		}
		
		String JMBG = textfieldJMBG.getText();
		
		if (JMBG == null || JMBG.length() != 13) {
			
			errorMessage += "Unesite ponovo vrijednost JBMG fizičkog lica.JMBG mora sadrzavati 13 cifara.\n";
			textfieldJMBG.setStyle("-fx-border-color: red");
			textfieldJMBG.setText("");
		}
		else {
			try {
				Long.parseLong(JMBG); // Samo za provjeru da li je svih 13 karaktera integer vrijednost
			}
			catch (NumberFormatException ex){
				
				errorMessage += "Unesite ponovo vrijednost JBMG fizičkog lica.JMBG mora sadrzavati 13 cifara.\n";
				textfieldJMBG.setStyle("-fx-border-color: red");
				textfieldJMBG.setText("");
			}
			textfieldJMBG.setStyle("");
		}
		
		if (errorMessage.length() > 0) {
			PopUpWindow.showMessage("Greška", "Greška pri unosu parametara.", errorMessage);
		}
		else {
			// Generisati kupca, zatim generisati fizicko lice
			KupacDao kd = new KupacDao();
			kd.addKupac();
			if(new FizickoLiceDao().addFizickoLice(kd.getMaxIdKupca(), JMBG, ime, prezime) > 0) {
				PopUpWindow.showMessage("Uspješna akcija", "Uspješno ste dodali novo fizičko lice:"+ prezime + " " + ime + " ("+JMBG + ")", "");
				callerController.initializeScene();
				callerController = null;
				thisStage.close();
			}
			else {
				PopUpWindow.showMessage("Greška", "Neuspješno dodavanje.", "Došlo je do greške pri dodavanju lica.\nProvjerite vrijednost JMBG da li je jedinstvena.");
			}
		}
	}

}
