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
import model.dao.InformacijeLijeciDao;
import model.dao.InformacijePcelinjakDao;
import model.dao.InformacijeVrcaDao;
import model.dao.PcelinjakDao;
import model.dto.Drustvo;
import model.dto.InformacijeLijeci;
import model.dto.InformacijeVrca;

public class PrikaziLijecenjaController extends Application {
	
	@FXML
	private Label labelVlasnik;
	@FXML
	private TableView tableLijecenja;
	@FXML
	private TableColumn colDatumLijecenja;
	@FXML
	private TableColumn colVrstaLijeka;
	@FXML
	private TableColumn colZaposleni;
	@FXML
	private Button buttonPvratakNazad;
	@FXML
	private Stage thisStage;
	
	private int IdDrustva;
	
	public PrikaziLijecenjaController() {}
	
	public PrikaziLijecenjaController(int idDrustva) {
		IdDrustva = idDrustva;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PrikaziLijecenjaForm.fxml"));
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
		
		InformacijeLijeciDao ild = new InformacijeLijeciDao();
		LinkedList<InformacijeLijeci> lijecenja = (LinkedList<InformacijeLijeci>)ild.getByIdDrustva(IdDrustva);
		
		
		colDatumLijecenja.setCellValueFactory(new PropertyValueFactory<>("datumLijecenja"));
		colVrstaLijeka.setCellValueFactory(new PropertyValueFactory<>("vrstaLijeka")); 
		colZaposleni.setCellValueFactory(new PropertyValueFactory<>("zaposleni")); 
		
		
		while(!tableLijecenja.getItems().isEmpty()) {
			tableLijecenja.getItems().remove(0);
		}
		for (InformacijeLijeci il : lijecenja) {
			tableLijecenja.getItems().add(il);
		}
		
		labelVlasnik.setText("Pčelinjak - " + new PcelinjakDao().getById(new DrustvoDao().getByIdDrustva(IdDrustva).getPCELINJAKIdPcelinjaka()).getNazivPcelinjaka());

		
	}
	
	
	public void backToPreviousForm() {
		thisStage.close();
	}

}
