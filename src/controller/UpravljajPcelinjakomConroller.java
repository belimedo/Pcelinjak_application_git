package controller;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.InformacijePcelinjakDao;
import model.dao.PcelinjakDao;
import model.dto.Pcelinjak;
import util.PopUpWindow;

public class UpravljajPcelinjakomConroller extends Application {
	
	private int IdPcelinjaka;
	private VlasnikController callerController;
	
	@FXML
	private Button buttonPromijeniAdresu;
	@FXML
	private Button buttonPovratakNazad;
	@FXML
	private Button buttonDodajVrcalicu;
	@FXML
	private Button buttonIzbrisiVrcalicu;
	@FXML
	private Button buttonDodajTegle;
	@FXML
	private Button buttonIzbrisiTegle;
	@FXML
	private TextField textfieldBrojTegli;
	@FXML
	private TextField textfieldBrojVrcalica;
	@FXML
	private TextField textfieldAdresa;
	@FXML
	private Label labelVlasnik;
	@FXML
	private TableView tablePcelinjak;
	@FXML
	private TableColumn colNaziv;
	@FXML
	private TableColumn colAdresa;
	@FXML
	private TableColumn colBrojVrcalica;
	@FXML
	private TableColumn colBrojDrustava;
	@FXML
	private TableColumn colBrojTegli;
	@FXML
	private TableColumn colBrojZaposlenih;
	@FXML
	private Stage thisStage;
	
	public UpravljajPcelinjakomConroller() {
		
	}
	
	public UpravljajPcelinjakomConroller(int IdPcelinjaka, Application vc) {
		this.IdPcelinjaka = IdPcelinjaka;
		callerController = (VlasnikController)vc;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpravljajPcelinjakom.fxml"));
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
            System.out.println("Going back to Vlasnik form...");
            backToVlasnikForm();
        });
        primaryStage.show();
        thisStage = primaryStage;
		
	}
	
	public void initializeScene() {
		
		PcelinjakDao pd = new PcelinjakDao();
		Pcelinjak p = pd.getById(IdPcelinjaka);
		
		colNaziv.setCellValueFactory(new PropertyValueFactory<>("NazivPcelinjaka"));
		colAdresa.setCellValueFactory(new PropertyValueFactory<>("AdresaPcelinjaka"));
		colBrojDrustava.setCellValueFactory(new PropertyValueFactory<>("BrojDrustava")); 
		colBrojVrcalica.setCellValueFactory(new PropertyValueFactory<>("BrojVrcalica")); 
		colBrojTegli.setCellValueFactory(new PropertyValueFactory<>("BrojTegliZaAmbalazu")); 
		colBrojZaposlenih.setCellValueFactory(new PropertyValueFactory<>("BrojZaposlenih"));
		
		if (!tablePcelinjak.getItems().isEmpty())
			tablePcelinjak.getItems().remove(0);
		tablePcelinjak.getItems().add(p);
		
		labelVlasnik.setText(p.getNazivPcelinjaka() + " - Vlasnik: "+ new InformacijePcelinjakDao().getByPcelinjakId(p.getIdPcelinjaka()).getVlasnik());
		
		
	}
	
	@FXML
	public void backToVlasnikForm() {
		
		callerController.initializeScene();
		thisStage.close();
	}
	
	@FXML
	public void changeAdresaPcelinjaka() {
		
		String novaAdresa = textfieldAdresa.getText();
		if (novaAdresa == null || novaAdresa.length() == 0) {
			PopUpWindow.showMessage("Greška!","Prazno polje","Molim Vas unesite vrijednost nove adrese u odgovarajuće polje.");
		}
		else {
			new PcelinjakDao().updateAddress(novaAdresa,IdPcelinjaka);
			this.initializeScene();
			textfieldAdresa.setText("");
		}	
	}
	
	@FXML
	public void addVrcalica() {
		
		String value = textfieldBrojVrcalica.getText();
		Integer number = 0;
		if (value == null || value.length() == 0) {
			PopUpWindow.showMessage("Greška!","Prazno polje","Molim Vas unesite broj vrcalica u odgovarajuće polje.");
			return;
		}
		else {
			try {
				number = Integer.parseInt(value);
			}
			catch (NumberFormatException ex) {
				PopUpWindow.showMessage("Greška!","Neispravan unos","Broj vrcalica mora biti pozitivan cjelobrojan broj.");
				return;
			}
			if (number <= 0) {
				PopUpWindow.showMessage("Greška!","Neispravan unos","Broj vrcalica mora biti pozitivan cjelobrojan broj.");
				return;
			}
			else {
				new PcelinjakDao().addVrcalica(number,IdPcelinjaka);
				this.initializeScene();
				textfieldBrojVrcalica.setText("");
				return;
			}
		}
	}
	
	@FXML
	public void deleteVrcalica() {
		
		String value = textfieldBrojVrcalica.getText();
		Integer number = 0;
		if (value == null || value.length() == 0) {
			PopUpWindow.showMessage("Greška!","Prazno polje","Molim Vas unesite broj vrcalica u odgovarajuće polje.");
			return;
		}
		else {
			try {
				number = Integer.parseInt(value);
			}
			catch (NumberFormatException ex) {
				PopUpWindow.showMessage("Greška!","Neispravan unos","Broj vrcalica mora biti pozitivan cjelobrojan broj.");
				return;
			}
			if (number <= 0) {
				PopUpWindow.showMessage("Greška!","Neispravan unos","Broj vrcalica mora biti pozitivan cjelobrojan broj.");
				return;
			}
			else {
				int tmp = new PcelinjakDao().removeVrcalica(number,IdPcelinjaka);
				if (tmp == 0) {
					PopUpWindow.showMessage("Greška!","Neispravan unos","Broj vrcalica koje se brisu ne moze biti veci od broja trenutnih vrcalica.");
					return;
				}
				else {
					this.initializeScene();
					textfieldBrojVrcalica.setText("");
					return;
				}
			}
		}
	}
	
	@FXML
	public void addTegla() {

		String value = textfieldBrojTegli.getText();
		Integer number = 0;
		if (value == null || value.length() == 0) {
			PopUpWindow.showMessage("Greška!","Prazno polje","Molim Vas unesite broj tegli u odgovarajuće polje.");
			return;
		}
		else {
			try {
				number = Integer.parseInt(value);
			}
			catch (NumberFormatException ex) {
				PopUpWindow.showMessage("Greška!","Neispravan unos","Broj tegli mora biti pozitivan cjelobrojan broj.");
				return;
			}
			if (number <= 0) {
				PopUpWindow.showMessage("Greška!","Neispravan unos","Broj tegli mora biti pozitivan cjelobrojan broj.");
				return;
			}
			else {
				new PcelinjakDao().addTegleZaAmbalazu(number,IdPcelinjaka);
				this.initializeScene();
				textfieldBrojTegli.setText("");
				return;
			}
		}
	}
	
	@FXML
	public void deleteTegla() {
		
		String value = textfieldBrojTegli.getText();
		Integer number = 0;
		if (value == null || value.length() == 0) {
			PopUpWindow.showMessage("Greška!","Prazno polje","Molim Vas unesite broj tegli u odgovarajuće polje.");
			return;
		}
		else {
			try {
				number = Integer.parseInt(value);
			}
			catch (NumberFormatException ex) {
				PopUpWindow.showMessage("Greška!","Neispravan unos","Broj tegli mora biti pozitivan cjelobrojan broj.");
				return;
			}
			if (number <= 0) {
				PopUpWindow.showMessage("Greška!","Neispravan unos","Broj tegli mora biti pozitivan cjelobrojan broj.");
				return;
			}
			else {
				int tmp = new PcelinjakDao().removeTegleZaAmbalazu(number,IdPcelinjaka);
				if (tmp == 0) {
					PopUpWindow.showMessage("Greška!","Neispravan unos","Broj tegli koje se brisu ne moze biti veci od broja trenutnih vrcalica.");
					return;
				}
				else {
					this.initializeScene();
					textfieldBrojTegli.setText("");
					return;
				}
			}
		}
	}
}
