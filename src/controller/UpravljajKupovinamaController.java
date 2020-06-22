package controller;

import java.util.LinkedList;
import java.util.List;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.DrustvoDao;
import model.dao.InformacijePcelinjakDao;
import model.dao.KupovinaDetaljnoDao;
import model.dao.PcelinjakDao;
import model.dto.Drustvo;
import model.dto.KupovinaDetaljno;

public class UpravljajKupovinamaController extends Application {
	
	@FXML
	private Label labelVlasnik;
	@FXML
	private TableView tableKupovine;
	@FXML
	private TableColumn colDatumKupovine;
	@FXML
	private TableColumn colCijena;
	@FXML
	private TableColumn colKupac;
	@FXML
	private Button buttonPovratakNazad;
	@FXML
	private Button buttonDetaljiKupovine;
	@FXML
	private Button buttonNovaKupovina;
	@FXML
	private Stage thisStage;
	
	private int IdPcelinjaka;
	private VlasnikController 	vlasnikController 	= null;
	private ZaposleniController zaposleniController = null;
	
	public UpravljajKupovinamaController(int idPcelinjaka,VlasnikController vc) {
		
		IdPcelinjaka = idPcelinjaka;
		vlasnikController = vc;
	}

	public UpravljajKupovinamaController(int idPcelinjaka,ZaposleniController zc) {
		
		IdPcelinjaka = idPcelinjaka;
		zaposleniController = zc;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UpravljajKupovinamaForm.fxml"));
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
		
		List<KupovinaDetaljno> kupovine = new KupovinaDetaljnoDao().getByIdPcelinjaka(IdPcelinjaka);
		
		colDatumKupovine.setCellValueFactory(new PropertyValueFactory<>("datumKupovine"));
		colCijena.setCellValueFactory(new PropertyValueFactory<>("ukupnaCijena"));
		colKupac.setCellValueFactory(new PropertyValueFactory<>("kupac")); 
		
		
		while(!tableKupovine.getItems().isEmpty()) {
			tableKupovine.getItems().remove(0);
		}
		for (KupovinaDetaljno kd : kupovine) {
			tableKupovine.getItems().add(kd);
		}
		
		labelVlasnik.setText(new PcelinjakDao().getById(IdPcelinjaka).getNazivPcelinjaka() + " - Vlasnik: "+ new InformacijePcelinjakDao().getByPcelinjakId(IdPcelinjaka).getVlasnik());
		
	}
	
	public void showDetails() {
		
		PrikaziKupovineController pkc = new PrikaziKupovineController(IdPcelinjaka);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        pkc.start(stage);
                        pkc.initializeScene();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    
                });
                timer.cancel();
            }
        }, 0);
		
	}
	
	public void newPurchase() {
		
	}
	
	public void backToPreviousForm() {
		
		if (vlasnikController != null) {
			vlasnikController.initializeScene();
			vlasnikController = null;
			thisStage.close();
		}else if (zaposleniController != null) {
			//zaposleniController.initializeScene();
			//zaposleniController = null;
			thisStage.close();
		}
	}
	
	

}
