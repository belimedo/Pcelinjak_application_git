package controller;

import java.util.LinkedList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.dao.LijeciDao;
import model.dto.Lijeci;
import util.PopUpWindow;

public class DodajLijecenjeController extends Application {
	
	@FXML
	private ChoiceBox<String> cbVrstaLijeka;
	@FXML
	private TextField textfieldVrstaLijeka;
	@FXML
	private CheckBox checkboxNoviLijek;
	
	private UpravljajDrustvimaController callerController;
	
	@FXML
	private Stage thisStage;
	private int IdDrustva;
	private int IdZaposlenog;
	
	public DodajLijecenjeController(int idDrustva,int idZaposlenog,UpravljajDrustvimaController controller) {
		
		IdDrustva = idDrustva;
		IdZaposlenog = idZaposlenog;
		callerController = controller;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DodajLijecenjeForm.fxml"));
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
		
		LinkedList<Lijeci> lijecenja = (LinkedList<Lijeci>) new LijeciDao().getAllLijeci();
		LinkedList<String> vrsteLijeka = new LinkedList<String>();
		for(Lijeci l : lijecenja) {
			vrsteLijeka.add(l.getVrstaLijeka());
		}
		if(vrsteLijeka.size() > 0)
			cbVrstaLijeka.setValue(vrsteLijeka.get(0));
		cbVrstaLijeka.setItems(FXCollections.observableArrayList(vrsteLijeka));
		checkboxNoviLijek.setSelected(false); // Ovo mozda ne treba
	}
	
	public void addLijecenje() {
		
		if(checkboxNoviLijek.isSelected()) { // Ako je selektovano, znaci da trebamo dodati novu vrstu lijeka u lijecenje
			
			String vrstaLijeka = textfieldVrstaLijeka.getText();
			String errorMessage = "";
			
			if (vrstaLijeka == null || vrstaLijeka.length() == 0) {
				
				errorMessage += "Potrebno je ispravno unijeti naziv novog lijeka ukoliko ste označili checkbox!\n";
				textfieldVrstaLijeka.setStyle("-fx-border-color: red");
				textfieldVrstaLijeka.setText("");
			}
			else {
				textfieldVrstaLijeka.setStyle("");
			}
			
			if (errorMessage.length() > 0) {
				PopUpWindow.showMessage("Greška", "Greška pri unosu parametara.", errorMessage);
			}
			else {
				new LijeciDao().addLijeci(vrstaLijeka, IdDrustva, IdZaposlenog);
				backToPreviousForm();
			}
			
		}else {
			
			String vrstaLijeka = cbVrstaLijeka.getValue();
			new LijeciDao().addLijeci(vrstaLijeka, IdDrustva, IdZaposlenog);
			backToPreviousForm();
		}
		
		
	}
	
	public void backToPreviousForm() {
		callerController.initializeScene();
		callerController = null;
		thisStage.close();
	}

}
