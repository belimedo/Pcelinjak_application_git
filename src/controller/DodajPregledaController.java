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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.dao.DrustvoDao;
import model.dao.PregledaDao;
import model.dao.SandukDao;
import model.dto.Drustvo;
import model.dto.Sanduk;
import util.PopUpWindow;

public class DodajPregledaController extends Application {
	
	@FXML 
	private Stage thisStage;
	@FXML
	private Button buttonAccept;
	@FXML
	private ChoiceBox<Byte> cbVelicinaLegla;
	@FXML
	private ChoiceBox<Byte> cbMedURezervi;
	@FXML
	private CheckBox checkboxProizveloRoj;
	
	private int IdDrustva;
	private int IdZaposlenog;
	private UpravljajDrustvimaController callerController;
	
	public DodajPregledaController(int idDrustva,int idZaposlenog,UpravljajDrustvimaController controller) {

		IdDrustva = idDrustva;
		IdZaposlenog = idZaposlenog;
		callerController = controller;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajPregledaForm.fxml"));
		loader.setController(this);
		Parent root = loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Pcelinjak - Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setOnCloseRequest(e->{
            e.consume();    
            exitStage();
        });
        primaryStage.show();
        thisStage = primaryStage;		
	}
	private void exitStage() {
		PopUpWindow.showMessage("Izlazak iz prozora", "Napustate unos", "Izlaskom iz ovog prozora bez potvrdjivanja podataka\n dodavanje pčelinjaka neće biti završeno.");
        thisStage.close();
	}
	public void initializeScene() {
		
		Sanduk sanduk = new SandukDao().getTopSandukByIdDrustva(IdDrustva);
		Drustvo drustvo = new DrustvoDao().getByIdDrustva(IdDrustva);
		List<Byte> ramovi = new LinkedList<Byte>();
		for(byte i = 0; i < sanduk.getBrojRamova()*drustvo.getBrojSanduka();i++) {
			ramovi.add((byte) (i+1));
		}
		cbVelicinaLegla.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbVelicinaLegla.setValue(newName);
		});
		cbMedURezervi.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbMedURezervi.setValue(newName);
		});
		
		cbVelicinaLegla.setItems(FXCollections.observableArrayList(ramovi));
		cbMedURezervi.setItems(FXCollections.observableArrayList(ramovi));
	}
	
	public void addPregled() {
		
		byte proizveloRoj = 0;
		if(checkboxProizveloRoj.isSelected())
			proizveloRoj = 1;
		byte velicinaLegla = cbVelicinaLegla.getValue();
		byte medURezervi = cbMedURezervi.getValue();
		
		new PregledaDao().addPregleda(velicinaLegla, medURezervi, proizveloRoj, IdDrustva, IdZaposlenog);
		callerController.initializeScene();
		callerController = null;
		thisStage.close();
		
	}

}
