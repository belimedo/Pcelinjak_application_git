package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
