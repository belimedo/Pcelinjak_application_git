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
import model.dao.PcelinjakDao;
import model.dao.SandukDao;
import model.dto.Drustvo;
import model.dto.Pcelinjak;
import model.dto.Sanduk;

public class UpravljajDrustvimaController extends Application {
	
	@FXML
	private Button buttonDodajDrustvo;
	@FXML
	private Button buttonUkloniDrustvo;
	@FXML
	private Button buttonPovratakNazad;
	@FXML
	private Button buttonDodajSanduk;
	@FXML
	private Button buttonUkloniSanduk;
	@FXML
	private Button buttonVrcanje;
	@FXML
	private Button buttonPrikaziVrcanje;
	@FXML
	private Button buttonLijecenje;
	@FXML
	private Button buttonListaLijecenja;
	@FXML
	private Button buttonPregled;
	@FXML
	private Button buttonListaPregledanja;
	@FXML
	private TextField textfieldBrojTegli;
	@FXML
	private TextField textfieldBrojVrcalica;
	@FXML
	private TextField textfieldAdresa;
	@FXML
	private Label labelVlasnik;
	@FXML
	private TableView tableDrustvo;
	@FXML
	private TableColumn colRed;
	@FXML
	private TableColumn colPozicijaURedu;
	@FXML
	private TableColumn colBrojSanduka;
	@FXML
	private TableColumn colVelicinaLegla;
	@FXML
	private TableColumn colKolicinaMeda;
	@FXML
	private TableColumn colProizveloRoj;
	@FXML
	private Stage thisStage;
	
	// Ovaj kontroler se moze pozvati iz dva kontrolera, vlasnikController i ZaposleniController, zbog toga postoje ova dva pod. clana i dva konstruktora
	private VlasnikController vlasnikController 	= null;
	
	private ZaposleniController zaposleniController = null;
	
	private int IdPcelinjaka;
	
	public UpravljajDrustvimaController() {
		
	}
	
	public UpravljajDrustvimaController(int idPcelinjaka,VlasnikController vc) {
		
		IdPcelinjaka = idPcelinjaka;
		vlasnikController = vc;
	}
	
	public UpravljajDrustvimaController(int idPcelinjaka,ZaposleniController zc) {
		
		IdPcelinjaka = idPcelinjaka;
		zaposleniController = zc;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpravljajDrustvimaForm.fxml"));
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
		
		DrustvoDao dd = new DrustvoDao();
		LinkedList<Drustvo> drustva = (LinkedList<Drustvo>)dd.getByPcelinjakId(IdPcelinjaka);
		
		colRed.setCellValueFactory(new PropertyValueFactory<>("red"));
		colPozicijaURedu.setCellValueFactory(new PropertyValueFactory<>("pozicijaURedu"));
		colBrojSanduka.setCellValueFactory(new PropertyValueFactory<>("brojSanduka")); 
		colVelicinaLegla.setCellValueFactory(new PropertyValueFactory<>("velicinaLegla")); 
		colKolicinaMeda.setCellValueFactory(new PropertyValueFactory<>("kolicinaMedaURezervi")); 
		colProizveloRoj.setCellValueFactory(new PropertyValueFactory<>("proizveloRoj"));
		
		
		while(!tableDrustvo.getItems().isEmpty()) {
			tableDrustvo.getItems().remove(0);
		}
		for (Drustvo d : drustva) {
			tableDrustvo.getItems().add(d);
		}
		
		if (vlasnikController != null) {
			buttonVrcanje.setVisible(false);
			buttonLijecenje.setVisible(false);
			buttonPregled.setVisible(false);
		}else if (zaposleniController != null) {

			buttonVrcanje.setVisible(true);
			buttonLijecenje.setVisible(true);
			buttonPregled.setVisible(true);
		}
		
		labelVlasnik.setText(new PcelinjakDao().getById(IdPcelinjaka).getNazivPcelinjaka() + " - Vlasnik: "+ new InformacijePcelinjakDao().getByPcelinjakId(IdPcelinjaka).getVlasnik());
		
	}
	
	public void addDrustvo() {
		
		DodajDrustvoController ddc = new DodajDrustvoController(IdPcelinjaka, this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        ddc.start(stage);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);
	}
	
	public void deleteDrustvo() {
		
		Drustvo dr = (Drustvo) tableDrustvo.getSelectionModel().getSelectedItem();
		if(dr != null) {
			new DrustvoDao().delteById(dr.getIdDrustva());
			initializeScene();
		}
		
	}
	
	public void addSanduk() {
		
		Drustvo dr = (Drustvo) tableDrustvo.getSelectionModel().getSelectedItem();
		SandukDao sd = new SandukDao();
		Sanduk sanduk = sd.getTopSandukByIdDrustva(dr.getIdDrustva());
		sd.addSanduk(dr.getIdDrustva(), dr.getPCELINJAKIdPcelinjaka(),sanduk.getBoja(), sanduk.getBrojRamova());
		initializeScene();
	}
	
	public void deleteSanduk() {
		
		Drustvo dr = (Drustvo) tableDrustvo.getSelectionModel().getSelectedItem();
		if(dr != null) {
			new SandukDao().deleteSanduk(dr.getIdDrustva());
			initializeScene();
		}
	}
	
	public void addVrcanje() {
		
		
	}
	
	public void showAllVrcanja() {
		
		Drustvo dr = (Drustvo) tableDrustvo.getSelectionModel().getSelectedItem();
		if(dr != null) {
			PrikaziVrcanjaController pvc = new PrikaziVrcanjaController(dr.getIdDrustva());
			Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run()
	            { 
	            	
	                Platform.runLater(() ->
	                {
	                    try {
	                     
	                        Stage stage = new Stage();
	                        pvc.start(stage);
	                        pvc.initializeScene();
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
	
	public void addLijecenje() {
		
	}
	
	public void showAllLijecenja() {
		// TODO: ovdje odraditi if provjeru da li je markirano drustvo da nemam Exception! 
		Drustvo dr = (Drustvo) tableDrustvo.getSelectionModel().getSelectedItem();
		if(dr != null) {
			PrikaziLijecenjaController plc = new PrikaziLijecenjaController(dr.getIdDrustva());
			Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run()
	            { 
	            	
	                Platform.runLater(() ->
	                {
	                    try {
	                     
	                        Stage stage = new Stage();
	                        plc.start(stage);
	                        plc.initializeScene();
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
	
	public void addPregled() {
		
		Drustvo dr = (Drustvo) tableDrustvo.getSelectionModel().getSelectedItem();
		if(dr != null) {
			DodajPregledaController dpc = new DodajPregledaController(dr.getIdDrustva(),zaposleniController.getIdZaposlenog(),this);
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
	                        dpc.initializeScene();
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
	
	public void showAllPregledi() {
		
		Drustvo dr = (Drustvo) tableDrustvo.getSelectionModel().getSelectedItem();
		if(dr != null) {
			PrikaziPregledeController ppc = new PrikaziPregledeController(dr.getIdDrustva());
			Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run()
	            { 
	            	
	                Platform.runLater(() ->
	                {
	                    try {
	                     
	                        Stage stage = new Stage();
	                        ppc.start(stage);
	                        ppc.initializeScene();
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
	
	public void backToPreviousForm() {
		
		if (vlasnikController != null) {
			vlasnikController.initializeScene();
			vlasnikController = null;
			thisStage.close();
		}else if (zaposleniController != null) {
			zaposleniController.initializeScene();
			zaposleniController = null;
			thisStage.close();
		}
		
	}

}
