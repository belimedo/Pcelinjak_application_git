package controller;

import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.InformacijePcelinjakDao;
import model.dao.IzvrcaniMedDao;
import model.dao.KupovinaDetaljnoDao;
import model.dao.PcelinjakDao;
import model.dao.PosjedujeMedDao;
import model.dao.PosjedujePropolisDao;
import model.dao.PropolisDao;
import model.dto.InformacijePcelinjak;
import model.dto.IzvrcaniMed;
import model.dto.KupovinaDetaljno;

public class DodajKupovinuController extends Application {
	
	
	@FXML
	private Button buttonPovratakNazad;
	@FXML
	private Button buttonNovoFizickoLice;
	@FXML
	private Button buttonNovaTrgovina;
	@FXML
	private Button buttonPotvrdi;
	@FXML
	private TextField textfieldKolicinaMeda;
	@FXML
	private TextField textfieldKolicinaPropolisa;
	@FXML
	private ChoiceBox<String> cbVrstaMeda;
	@FXML
	private ChoiceBox<String> cbVrstaPropolisa;
	@FXML
	private ChoiceBox<String> cbKupac;
	
	private Stage thisStage;
	private UpravljajKupovinamaController callerController = null;
	private int IdPcelinjaka;
	
	public DodajKupovinuController(int idPcelinjaka, UpravljajKupovinamaController controller) {
		IdPcelinjaka = idPcelinjaka;
		callerController = controller;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajKupovinuForm.fxml"));
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
		
		IzvrcaniMedDao imd = new IzvrcaniMedDao();
		PropolisDao pd = new PropolisDao();
		PosjedujeMedDao pmd = new PosjedujeMedDao();
		PosjedujePropolisDao ppd = new PosjedujePropolisDao();
		
		
		
		List<String> vrsteMeda = new LinkedList<String>();
		List<String> vrstePropolisa = new LinkedList<String>();
		List<String> kupci = new LinkedList<String>();
		
		cbVrstaMeda.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbVrstaMeda.setValue(newName); });
		
		cbVrstaPropolisa.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbVrstaPropolisa.setValue(newName); });
		
		cbKupac.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbKupac.setValue(newName); });
		
		cbVrstaMeda.setItems(FXCollections.observableArrayList(vrsteMeda));
		cbVrstaPropolisa.setItems(FXCollections.observableArrayList(vrstePropolisa));
		cbKupac.setItems(FXCollections.observableArrayList(kupci));
		
		if (PcelinjakImena.size() > 0) 	{	
			nazivPcelinjaka = PcelinjakImena.get(0);
		}
		cbVrstaMeda.setValue(nazivPcelinjaka);
	}
	
	public void addKupovina() {
		
		
	}
	
	
	public void addFizickoLice() {
		
		
	}
	
	public void addTrgovina() {
		
		
	}
	
	public void backToPreviousForm() {
		
		if (callerController != null) {
			callerController.initializeScene();
			callerController = null;
			thisStage.close();
		}
	}
	
	

}
