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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.InformacijePcelinjakDao;
import model.dao.PcelinjakDao;
import model.dao.VlasnikDao;
import model.dao.ZaposleniDao;
import model.dto.InformacijePcelinjak;
import model.dto.Pcelinjak;
import model.dto.Zaposleni;

public class ZaposleniController extends Application {
	
	@FXML
	private Label labelVlasnik;
	@FXML
	private Label labelNazivPcelinjaka;
	@FXML
	private Button buttonDetaljiOPcelinjaku;
	@FXML
	private Button buttonPrikaziDrustva;
	@FXML
	private Button buttonPrikaziKupovine;
	@FXML
	private Button buttonLogout;
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
	
	private int IdZaposlenog;
	
	public int getIdZaposlenog() {
		return IdZaposlenog;
	}
	
	public ZaposleniController(int idZaposlenog) {
		IdZaposlenog = idZaposlenog;
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ZaposleniForm.fxml"));
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
		ZaposleniDao zd = new ZaposleniDao();
		InformacijePcelinjakDao ipd = new InformacijePcelinjakDao();
		
		Zaposleni zaposleni = zd.getByIdZaposlenog(IdZaposlenog);
		Pcelinjak pcelinjak = pd.getById(zaposleni.getIdPčelinjaka());
		
		colNaziv.setCellValueFactory(new PropertyValueFactory<>("Naziv"));
		colAdresa.setCellValueFactory(new PropertyValueFactory<>("Adresa"));
		colVlasnik.setCellValueFactory(new PropertyValueFactory<>("Vlasnik"));
		colBrojDrustava.setCellValueFactory(new PropertyValueFactory<>("BrojDrustava")); 
		colUkupnoMeda.setCellValueFactory(new PropertyValueFactory<>("UkupnoMeda"));
		colUkupnoPropolisa.setCellValueFactory(new PropertyValueFactory<>("UkupnoPropolisa"));
		colBrojZaposlenih.setCellValueFactory(new PropertyValueFactory<>("BrojZaposlenih"));
		
		InformacijePcelinjak ip = ipd.getByPcelinjakId(pcelinjak.getIdPcelinjaka());
		if (!tablePcelinjak.getItems().isEmpty()) { 
			
			tablePcelinjak.getItems().remove(0);
		}
		tablePcelinjak.getItems().add(ip);

		labelNazivPcelinjaka.setText(pcelinjak.getNazivPcelinjaka());
		labelVlasnik.setText(pcelinjak.getNazivPcelinjaka() + " - Vlasnik: "+ (new VlasnikDao().getByIdVlasnika(pcelinjak.getVLASNIK_IdVlasnika()).getPrezime() + " " + new VlasnikDao().getByIdVlasnika(pcelinjak.getVLASNIK_IdVlasnika()).getIme()));

	}
	
	@FXML
	public void detaljiPcelinjak() {
		
		PcelinjakDao pd = new PcelinjakDao();
		ZaposleniDao zd = new ZaposleniDao();
		Zaposleni zaposleni = zd.getByIdZaposlenog(IdZaposlenog);
		Pcelinjak pcelinjak = pd.getById(zaposleni.getIdPčelinjaka());
		
		UpravljajPcelinjakomConroller upc = new UpravljajPcelinjakomConroller(pcelinjak.getIdPcelinjaka(),this);
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
	
	public void showDrustva() {
		
		PcelinjakDao pd = new PcelinjakDao();
		ZaposleniDao zd = new ZaposleniDao();
		Zaposleni zaposleni = zd.getByIdZaposlenog(IdZaposlenog);
		Pcelinjak pcelinjak = pd.getById(zaposleni.getIdPčelinjaka());
		
		UpravljajDrustvimaController udc = new UpravljajDrustvimaController(pcelinjak.getIdPcelinjaka(),this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        udc.start(stage);
                        udc.initializeScene();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);
	}
	
	public void showKupovine() {
		
		PcelinjakDao pd = new PcelinjakDao();
		ZaposleniDao zd = new ZaposleniDao();
		Zaposleni zaposleni = zd.getByIdZaposlenog(IdZaposlenog);
		Pcelinjak pcelinjak = pd.getById(zaposleni.getIdPčelinjaka());
		
		UpravljajKupovinamaController ukc = new UpravljajKupovinamaController(pcelinjak.getIdPcelinjaka(),this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        ukc.start(stage);
                        ukc.initializeScene();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);
	}
	
	public void logout() {
		
		LoginController login = new LoginController();
		Timer timer = new Timer();
		thisStage.close();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        login.start(stage);
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
