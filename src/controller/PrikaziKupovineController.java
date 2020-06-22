package controller;

import java.util.List;

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
import model.dao.InformacijePcelinjakDao;
import model.dao.KupovinaDetaljnoDao;
import model.dao.PcelinjakDao;
import model.dto.KupovinaDetaljno;

public class PrikaziKupovineController extends Application {
	
	@FXML
	private Label labelVlasnik;
	@FXML
	private TableView tableKupovine;
	@FXML
	private TableColumn colDatumKupovine;
	@FXML
	private TableColumn colKolicinaMeda;
	@FXML
	private TableColumn colCijenaMeda;
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
	
	private Stage thisStage;
	
	private int IdPcelinjaka;
	
	public PrikaziKupovineController(int idPcelinjaka) {
		this.IdPcelinjaka = idPcelinjaka;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PrikaziKupovineForm.fxml"));
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
		colUkupnaCijena.setCellValueFactory(new PropertyValueFactory<>("ukupnaCijena"));
		colKupac.setCellValueFactory(new PropertyValueFactory<>("kupac")); 
		colKolicinaMeda.setCellValueFactory(new PropertyValueFactory<>("kolicinaMeda"));
		colKolicinaPropolisa.setCellValueFactory(new PropertyValueFactory<>("kolicinaPropolisa"));
		colCijenaPropolisa.setCellValueFactory(new PropertyValueFactory<>("cijenaPropolisa")); 
		colCijenaMeda.setCellValueFactory(new PropertyValueFactory<>("cijenaMeda")); 
		
		
		while(!tableKupovine.getItems().isEmpty()) {
			tableKupovine.getItems().remove(0);
		}
		for (KupovinaDetaljno kd : kupovine) {
			tableKupovine.getItems().add(kd);
		}
		
		labelVlasnik.setText(new PcelinjakDao().getById(IdPcelinjaka).getNazivPcelinjaka() + " - Vlasnik: "+ new InformacijePcelinjakDao().getByPcelinjakId(IdPcelinjaka).getVlasnik());
		
	}
	
	
	public void backToPreviousForm() {
		
			thisStage.close();
	}

}
