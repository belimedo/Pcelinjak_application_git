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
import model.dao.InformacijeVrcaDao;
import model.dao.PcelinjakDao;
import model.dto.Drustvo;
import model.dto.InformacijeVrca;

public class PrikaziVrcanjaController extends Application {
	
	@FXML
	private Label labelVlasnik;
	@FXML
	private TableView tableVrcanja;
	@FXML
	private TableColumn colDatumVrcanja;
	@FXML
	private TableColumn colVrstaMeda;
	@FXML
	private TableColumn colKolicinaIzvrcanogMeda;
	@FXML
	private TableColumn colZaposleni;
	@FXML
	private Button buttonPvratakNazad;
	@FXML
	private Stage thisStage;
	
	private int IdDrustva;
	
	public PrikaziVrcanjaController() {}
	
	public PrikaziVrcanjaController(int idDrustva) {
		IdDrustva = idDrustva;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PrikaziVrcanjaForm.fxml"));
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
		
		InformacijeVrcaDao ivd = new InformacijeVrcaDao();
		LinkedList<InformacijeVrca> vrcanja = (LinkedList<InformacijeVrca>)ivd.getByIdDrustva(IdDrustva);
		
		colDatumVrcanja.setCellValueFactory(new PropertyValueFactory<>("datumVrcanja"));
		colVrstaMeda.setCellValueFactory(new PropertyValueFactory<>("vrstaMeda"));
		colKolicinaIzvrcanogMeda.setCellValueFactory(new PropertyValueFactory<>("kolicinaMeda")); 
		colZaposleni.setCellValueFactory(new PropertyValueFactory<>("zaposleni")); 
		
		
		while(!tableVrcanja.getItems().isEmpty()) {
			tableVrcanja.getItems().remove(0);
		}
		for (InformacijeVrca iv : vrcanja) {
			tableVrcanja.getItems().add(iv);
		}
		
		labelVlasnik.setText("Pčelinjak - " + new PcelinjakDao().getById(new DrustvoDao().getByIdDrustva(IdDrustva).getPCELINJAKIdPcelinjaka()).getNazivPcelinjaka());

		
	}
	
	
	public void backToPreviousForm() {
		thisStage.close();
	}

}
