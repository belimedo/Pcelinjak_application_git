package controller;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PrikaziKupovineController extends Application {
	
	@FXML
	private Label labelVlasnik;
	@FXML
	private TableView tableKupovine;
	@FXML
	private TableColumn colDatumKupovine;
	@FXML
	private TableColumn colVrstaMeda;
	@FXML
	private TableColumn colKolicinaMeda;
	@FXML
	private TableColumn colCijenaMeda;
	@FXML
	private TableColumn colVrstaPropolisa;
	@FXML
	private TableColumn colKolicinaPropolisa;
	@FXML
	private TableColumn colCijenaPropolisa;
	@FXML
	private TableColumn colUkupnaCijena;
	@FXML
	private TableColumn colKupac;
	@FXML
	private Button buttonPovratakNazad;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
