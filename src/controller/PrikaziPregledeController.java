package controller;

import java.sql.Date;
import java.util.LinkedList;

import javafx.application.Application;
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
import model.dao.InformacijePregledaDao;
import model.dao.InformacijeVrcaDao;
import model.dao.PcelinjakDao;
import model.dto.Drustvo;
import model.dto.InformacijePregleda;
import model.dto.InformacijeVrca;

public class PrikaziPregledeController extends Application {
	
	@FXML
	private Label labelVlasnik;
	@FXML
	private TableView tablePregledi;
	@FXML
	private TableColumn colDatumPregleda;
	@FXML
	private TableColumn colVelicinaLegla;
	@FXML
	private TableColumn colMedURezervi;
	@FXML
	private TableColumn colProizveloRoj;
	@FXML
	private TableColumn colZaposleni;
	@FXML
	private Button buttonPvratakNazad;
	@FXML
	private Stage thisStage;
	
	private int IdDrustva;
	
	public PrikaziPregledeController() {}
	
	public PrikaziPregledeController(int idDrustva) {
		IdDrustva = idDrustva;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PrikaziPregledeForm.fxml"));
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
		
		InformacijePregledaDao ipd = new InformacijePregledaDao();
		LinkedList<InformacijePregleda> pregledanja = (LinkedList<InformacijePregleda>)ipd.getByIdDrustva(IdDrustva);
		
		colDatumPregleda.setCellValueFactory(new PropertyValueFactory<>("datumPregleda"));
		colVelicinaLegla.setCellValueFactory(new PropertyValueFactory<>("velicinaLegla"));
		colMedURezervi.setCellValueFactory(new PropertyValueFactory<>("kolicinaMedaURezervi")); 
		colProizveloRoj.setCellValueFactory(new PropertyValueFactory<>("proizveloRoj")); 
		colZaposleni.setCellValueFactory(new PropertyValueFactory<>("zaposleni")); 
		
		
		while(!tablePregledi.getItems().isEmpty()) {
			tablePregledi.getItems().remove(0);
		}
		for (InformacijePregleda ip : pregledanja) {
			tablePregledi.getItems().add(ip);
		}
		
		labelVlasnik.setText("Pčelinjak - " + new PcelinjakDao().getById(new DrustvoDao().getByIdDrustva(IdDrustva).getPCELINJAKIdPcelinjaka()).getNazivPcelinjaka());

		
	}
	
	
	public void backToPreviousForm() {
		thisStage.close();
	}

}
