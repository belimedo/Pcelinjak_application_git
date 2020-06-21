package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PotvrdiKupovinuController extends Application {
	
	@FXML
	private Label labelVrstaMeda;
	@FXML
	private Label labelKolicinaMeda;
	@FXML
	private Label labelVrstaPropolisa;
	@FXML
	private Label labelKolicinaPropolisa;
	@FXML
	private Label labelKupac;
	@FXML
	private Label labelUkupnaCijena;
	@FXML 
	private Button buttonNext;
	
	private Stage thisStage;
	private UpravljajKupovinamaController upravljajKupovinom;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
