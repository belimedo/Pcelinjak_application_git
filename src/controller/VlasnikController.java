package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.InformacijePcelinjakDao;
import model.dao.PcelinjakDao;
import model.dao.VlasnikDao;
import model.dto.InformacijePcelinjak;
import model.dto.Pcelinjak;
import model.dto.Vlasnik;

public class VlasnikController extends Application {
	
	private int IdVlasnika;
	
	public int getIdVlasnika() {
		return IdVlasnika;
	}

	@FXML
	private ChoiceBox<String> cbNazivPcelinjaka;
	@FXML
	private Button loginButton;
	@FXML
	private Button buttonDetaljiOPcelinjaku;
	@FXML
	private Button buttonPrikaziDrustva;
	@FXML
	private Button buttonPrikaziZaposlene;
	@FXML
	private Button buttonPrikaziTransakcije;
	@FXML
	private Button buttonDodajPcelinjak;
	@FXML
	private Button buttonIzbrisiPcelinjak;
	@FXML
	private Label labelVlasnik;
	
	@FXML
	private TableView tablePcelinjak;
	@FXML
	private TableColumn colNaziv;
	@FXML
	private TableColumn colAdresa;
	@FXML
	private TableColumn colVlasnik;
	@FXML
	private TableColumn colBrojDrustava;
	@FXML
	private TableColumn colUkupnoMeda;
	@FXML
	private TableColumn colUkupnoPropolisa;
	@FXML
	private TableColumn colBrojZaposlenih;
	@FXML
	private Stage thisStage;
	
	public VlasnikController() {
		
	}
	
	public VlasnikController(int IdVlasnika) {
		this.IdVlasnika = IdVlasnika;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VlasnikForm.fxml"));
		loader.setController(this);
		Parent root = loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Pcelinjak - Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e->{
            e.consume();
            System.out.println("Application is ending, please wait for few moments...");
            System.exit(0); // Da pogasimo sve stage-ove sto su ostali
        });
        primaryStage.show();
        thisStage = primaryStage;
	}
	
	
	public void initializeScene() {
		
		PcelinjakDao pd = new PcelinjakDao();
		List<Pcelinjak> PcelinjakByVlasnik = pd.getByOwner(IdVlasnika);
		List<String> PcelinjakImena = new LinkedList<String>();
		List<Integer> PcelinjakId = new LinkedList<Integer>();
		InformacijePcelinjakDao ipd = new InformacijePcelinjakDao();
		
		for (Pcelinjak p : PcelinjakByVlasnik) {
			PcelinjakImena.add(p.getNazivPcelinjaka());
			PcelinjakId.add(p.getIdPcelinjaka());
		}
		
		
		colNaziv.setCellValueFactory(new PropertyValueFactory<>("Naziv"));
		colAdresa.setCellValueFactory(new PropertyValueFactory<>("Adresa"));
		colVlasnik.setCellValueFactory(new PropertyValueFactory<>("Vlasnik"));
		colBrojDrustava.setCellValueFactory(new PropertyValueFactory<>("BrojDrustava")); 
		colUkupnoMeda.setCellValueFactory(new PropertyValueFactory<>("UkupnoMeda"));
		colUkupnoPropolisa.setCellValueFactory(new PropertyValueFactory<>("UkupnoPropolisa"));
		colBrojZaposlenih.setCellValueFactory(new PropertyValueFactory<>("BrojZaposlenih"));
		
		
		cbNazivPcelinjaka.setItems(FXCollections.observableArrayList(PcelinjakImena));
		
		// Postavljamo Listener-a za promjenu vrijednosti!
		cbNazivPcelinjaka.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbNazivPcelinjaka.setValue(newName);
			if(newName != null) {
				
				InformacijePcelinjak ip = ipd.getByPcelinjakId(pd.getByName(newName).getIdPcelinjaka());
				if (!tablePcelinjak.getItems().isEmpty()) {
					
					tablePcelinjak.getItems().remove(0);
				}
				tablePcelinjak.getItems().add(ip);
				labelVlasnik.setText(newName + " - Vlasnik: "+ip.getVlasnik());
			}
		});
		
		cbNazivPcelinjaka.setValue(PcelinjakImena.get(0));
		labelVlasnik.setText(PcelinjakImena.get(0) + " - Vlasnik: "+ (ipd.getByPcelinjakId(pd.getByName(PcelinjakImena.get(0)).getIdPcelinjaka())).getVlasnik());
		
	}
	
	@FXML
	public void detaljiPcelinjak() {
		

		int IdPcelinjaka = (new PcelinjakDao().getByName(cbNazivPcelinjaka.getValue())).getIdPcelinjaka();
		System.out.println(IdPcelinjaka);	
		UpravljajPcelinjakomConroller upc = new UpravljajPcelinjakomConroller(IdPcelinjaka,this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        upc.start(stage);
                        upc.initializeScene();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);
		
	}
	
	
	@FXML
	public void addPcelinjak() {
		
		DodajPcelinjakController dpc = new DodajPcelinjakController(this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        dpc.start(stage);
                        //upc.initializeScene();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);

	}
	
	@FXML
	public void deletePcelinjak() {
		
	}
	
	public void testZaposleniAdd() {
		
		DodajZaposlenogController dzc = new DodajZaposlenogController(4);//pd.getByName(naziv).getIdPcelinjaka());
		
		int i = dzc.addedZaposleni;
		for (i = 0; i<3; i++) {
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
	

}
