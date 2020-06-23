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
import model.dao.TrgovinaDao;
import util.PopUpWindow;

public class DodajKupcaTrgovinaController extends Application {

	@FXML
	private TextField textfieldNaziv; 
	@FXML
	private TextField textfieldAdresa;
	@FXML
	private Button buttonContinue;
	@FXML
	private Stage thisStage;
	
private DodajKupovinuController callerController = null;
	
	public DodajKupcaTrgovinaController(DodajKupovinuController cc) {
		callerController = cc;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajKupcaTrgovinaForm.fxml"));
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
	
	public void addTrgovina() {
		
		String errorMessage = "";
		
		String naziv = textfieldNaziv.getText();
		
		if (naziv == null || naziv.length() == 0) {
			
			errorMessage +="Unesite ponovo vrijednost Ime fizičkog lica\n";
			textfieldNaziv.setStyle("-fx-border-color: red");
			textfieldNaziv.setText("");
		}
		else {
			textfieldNaziv.setStyle("");
		}
		
		String adresa = textfieldAdresa.getText();
		
		if (adresa == null || adresa.length() == 0) {
			
			errorMessage += "Unesite ponovo vrijednost Prezime fizičkog lica\n";
			textfieldAdresa.setStyle("-fx-border-color: red");
			textfieldAdresa.setText("");
		}
		else {
			textfieldAdresa.setStyle("");
		}
		
		if (errorMessage.length() > 0) {
			PopUpWindow.showMessage("Greška", "Greška pri unosu parametara.", errorMessage);
		}
		else {
			// Generisati kupca, zatim generisati fizicko lice
			KupacDao kd = new KupacDao();
			kd.addKupac();
			if(new TrgovinaDao().addTrgovina(kd.getMaxIdKupca(), naziv, adresa) > 0) {
				PopUpWindow.showMessage("Uspješna akcija", "Uspješno ste dodali novu trgovinu: "+ naziv + " na adresi " + adresa,"");
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
