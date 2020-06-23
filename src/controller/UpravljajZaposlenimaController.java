package controller;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
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
import model.dao.DrustvoDao;
import model.dao.InformacijePcelinjakDao;
import model.dao.LijeciDao;
import model.dao.PcelinjakDao;
import model.dao.PregledaDao;
import model.dao.SandukDao;
import model.dao.VrcaMedDao;
import model.dao.ZaposleniDao;
import model.dto.Drustvo;
import model.dto.Pcelinjak;
import model.dto.Sanduk;
import model.dto.Zaposleni;

public class UpravljajZaposlenimaController extends Application {
	
	
	@FXML
	private Button buttonPovratakNazad;
	@FXML
	private Button buttonDodajZaposlenog;
	@FXML
	private Button buttonIzbrisiZaposlenog;
	@FXML
	private Label labelVlasnik;
	@FXML
	private TableView tableZaposleni;
	@FXML
	private TableColumn colIme;
	@FXML
	private TableColumn colPrezime;
	@FXML
	private TableColumn colJMBG;
	@FXML
	private TableColumn colPlata;
	@FXML
	private TableColumn colKorisnickoIme;
	@FXML
	private Stage thisStage;
	
	private VlasnikController vlasnikController = null;
	
	private int IdPcelinjaka;
	
	public UpravljajZaposlenimaController() {
		
	}
	
	public UpravljajZaposlenimaController(int idPcelinjaka,VlasnikController vc) {
		
		IdPcelinjaka = idPcelinjaka;
		vlasnikController = vc;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpravljajZaposlenimaForm.fxml"));
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
		
		ZaposleniDao zd = new ZaposleniDao();
		LinkedList<Zaposleni> zaposleni = (LinkedList<Zaposleni>)zd.getAllByPcelinjakId(IdPcelinjaka);
		
		colIme.setCellValueFactory(new PropertyValueFactory<>("Ime"));
		colPrezime.setCellValueFactory(new PropertyValueFactory<>("Prezime"));
		colJMBG.setCellValueFactory(new PropertyValueFactory<>("JMBG")); 
		colPlata.setCellValueFactory(new PropertyValueFactory<>("Plata")); 
		colKorisnickoIme.setCellValueFactory(new PropertyValueFactory<>("KorisničkoIme")); 
		
		
		
		while(!tableZaposleni.getItems().isEmpty()) {
			tableZaposleni.getItems().remove(0);
		}
		for (Zaposleni z : zaposleni) {
			tableZaposleni.getItems().add(z);
		}
		thisStage.toFront();
		labelVlasnik.setText(new PcelinjakDao().getById(IdPcelinjaka).getNazivPcelinjaka() + " - Vlasnik: "+ new InformacijePcelinjakDao().getByPcelinjakId(IdPcelinjaka).getVlasnik());
		
	}
	
	public void addZaposlenog() {
		
		int maxNumber = new DodajZaposlenogController().addedZaposleni + 1; // Samo jednog zaposlenog dodajemo
		
		DodajZaposlenogController dzc = new DodajZaposlenogController(maxNumber,this,IdPcelinjaka);
		
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
	
	public void deleteZaposlenog() {
		
		Zaposleni zaposleni = (Zaposleni) tableZaposleni.getSelectionModel().getSelectedItem();
		new PregledaDao().deletePregledaByIdZaposlenog(zaposleni.getIdZaposlenog());
		new LijeciDao().deleteLijeciByIdZaposlenog(zaposleni.getIdZaposlenog());
		new VrcaMedDao().deleteVrcaMedByIdZaposlenog(zaposleni.getIdZaposlenog());
		if(zaposleni != null) {
			new ZaposleniDao().deleteById(zaposleni.getIdZaposlenog());
			initializeScene();
		}

	}

	
	public void backToPreviousForm() {
		
		vlasnikController.initializeScene();
		vlasnikController = null;
		thisStage.close();
	}

}
