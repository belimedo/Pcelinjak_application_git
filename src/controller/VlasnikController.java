package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.DrustvoDao;
import model.dao.InformacijePcelinjakDao;
import model.dao.KupovinaDao;
import model.dao.LijeciDao;
import model.dao.PcelinjakDao;
import model.dao.PosjedujeMedDao;
import model.dao.PosjedujePropolisDao;
import model.dao.PregledaDao;
import model.dao.StavkaMedDao;
import model.dao.StavkaPropolisDao;
import model.dao.VlasnikDao;
import model.dao.VrcaMedDao;
import model.dao.ZaposleniDao;
import model.dto.Drustvo;
import model.dto.InformacijePcelinjak;
import model.dto.Kupovina;
import model.dto.Pcelinjak;
import model.dto.Vlasnik;
import model.dto.Zaposleni;

public class VlasnikController extends Application {
	
	private int IdVlasnika;
	
	public int getIdVlasnika() {
		return IdVlasnika;
	}

	@FXML
	private ChoiceBox<String> cbNazivPcelinjaka;
	@FXML
	private Button loginButton;
	@FXML
	private Button buttonDetaljiOPcelinjaku;
	@FXML
	private Button buttonPrikaziDrustva;
	@FXML
	private Button buttonPrikaziZaposlene;
	@FXML
	private Button buttonPrikaziKupovine;
	@FXML
	private Button buttonDodajPcelinjak;
	@FXML
	private Button buttonIzbrisiPcelinjak;
	@FXML
	private Button buttonLogout;
	@FXML
	private Label labelVlasnik;
	
	@FXML
	private TableView tablePcelinjak;
	@FXML
	private TableColumn colNaziv;
	@FXML
	private TableColumn colAdresa;
	@FXML
	private TableColumn colVlasnik;
	@FXML
	private TableColumn colBrojDrustava;
	@FXML
	private TableColumn colUkupnoMeda;
	@FXML
	private TableColumn colUkupnoPropolisa;
	@FXML
	private TableColumn colBrojZaposlenih;
	@FXML
	private Stage thisStage;
	
	public VlasnikController() {
		
	}
	
	public VlasnikController(int IdVlasnika) {
		this.IdVlasnika = IdVlasnika;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/VlasnikForm.fxml"));
		loader.setController(this);
		Parent root = loader.load();
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Pcelinjak - Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e->{
            e.consume();
            System.out.println("Application is ending, please wait for few moments...");
            System.exit(0); // Da pogasimo sve stage-ove sto su ostali
        });
        primaryStage.show();
        thisStage = primaryStage;
	}
	
	
	public void initializeScene() {
		
		PcelinjakDao pd = new PcelinjakDao();
		List<Pcelinjak> PcelinjakByVlasnik = pd.getByOwner(IdVlasnika);
		List<String> PcelinjakImena = new LinkedList<String>();
		List<Integer> PcelinjakId = new LinkedList<Integer>();
		InformacijePcelinjakDao ipd = new InformacijePcelinjakDao();
		
		String nazivPcelinjaka = "";
		
		for (Pcelinjak p : PcelinjakByVlasnik) {
			PcelinjakImena.add(p.getNazivPcelinjaka());
			PcelinjakId.add(p.getIdPcelinjaka());
		}
		
		
		colNaziv.setCellValueFactory(new PropertyValueFactory<>("Naziv"));
		colAdresa.setCellValueFactory(new PropertyValueFactory<>("Adresa"));
		colVlasnik.setCellValueFactory(new PropertyValueFactory<>("Vlasnik"));
		colBrojDrustava.setCellValueFactory(new PropertyValueFactory<>("BrojDrustava")); 
		colUkupnoMeda.setCellValueFactory(new PropertyValueFactory<>("UkupnoMeda"));
		colUkupnoPropolisa.setCellValueFactory(new PropertyValueFactory<>("UkupnoPropolisa"));
		colBrojZaposlenih.setCellValueFactory(new PropertyValueFactory<>("BrojZaposlenih"));
		
		
		// Postavljamo Listener-a za promjenu vrijednosti
		cbNazivPcelinjaka.getSelectionModel().selectedItemProperty().addListener((ov, oldName, newName) -> {
			cbNazivPcelinjaka.setValue(newName);
			if(newName != null) {
				
				InformacijePcelinjak ip = null;
				try {
					int IdPcelinjaka = pd.getByName(newName).getIdPcelinjaka();
					ip = ipd.getByPcelinjakId(IdPcelinjaka);
				} catch(NullPointerException ex) {} // Jednostavno nista se nece desiti, javice exception ali nema uticaja na kod
				
				if (!tablePcelinjak.getItems().isEmpty()) { // Ovim omogucavam da imam samo 1 vrstu u tabeli
						
					tablePcelinjak.getItems().remove(0);
				}
				
				if (ip != null) {
					
					tablePcelinjak.getItems().add(ip);
					labelVlasnik.setText(newName + " - Vlasnik: "+ip.getVlasnik());
				}
			}
		});
		
		
		if (PcelinjakImena.size() > 0) 	{	
			nazivPcelinjaka = PcelinjakImena.get(0);
		}
		cbNazivPcelinjaka.setItems(FXCollections.observableArrayList(PcelinjakImena));
		cbNazivPcelinjaka.setValue(nazivPcelinjaka);
		labelVlasnik.setText(nazivPcelinjaka + " - Vlasnik: "+ (new VlasnikDao().getByIdVlasnika(IdVlasnika).getPrezime() + " " + new VlasnikDao().getByIdVlasnika(IdVlasnika).getIme()));

		
				
	}
	
	@FXML
	public void detaljiPcelinjak() {
		

		int IdPcelinjaka = (new PcelinjakDao().getByName(cbNazivPcelinjaka.getValue())).getIdPcelinjaka();
		
		UpravljajPcelinjakomConroller upc = new UpravljajPcelinjakomConroller(IdPcelinjaka,this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        upc.start(stage);
                        upc.initializeScene();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);
		
	}
	
	
	@FXML
	public void addPcelinjak() {
		
		DodajPcelinjakController dpc = new DodajPcelinjakController(this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        dpc.start(stage);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);

	}
	
	@FXML
	public void deletePcelinjak() {
		
		if(cbNazivPcelinjaka.getValue()!= null) {
		
		PcelinjakDao pd = new PcelinjakDao();
		int IdPcelinjaka = pd.getByName(cbNazivPcelinjaka.getValue()).getIdPcelinjaka();

		DrustvoDao drustvoDao 			= new DrustvoDao();
		ZaposleniDao zaposleniDao 		= new ZaposleniDao();
		LijeciDao lijeciDao 			= new LijeciDao();
		VrcaMedDao vrcaMedDao 			= new VrcaMedDao();
		PregledaDao pregledaDao 		= new PregledaDao();
		KupovinaDao kupovinaDao			= new KupovinaDao();
		StavkaMedDao stMedDao 			= new StavkaMedDao();
		StavkaPropolisDao stPropolisDao	= new StavkaPropolisDao();
		
		LinkedList<Drustvo> listDrustva = (LinkedList<Drustvo>) drustvoDao.getByPcelinjakId(IdPcelinjaka);
		LinkedList<Zaposleni> listZaposleni = (LinkedList<Zaposleni>) zaposleniDao.getAllByPcelinjakId(IdPcelinjaka);
		
		for (Drustvo d : listDrustva) {
			lijeciDao.deleteLijeciByIdDrustva(d.getIdDrustva());
			vrcaMedDao.deleteVrcaMedByIdDrustva(d.getIdDrustva());
			pregledaDao.deletePregledaByIdDrustva(d.getIdDrustva());
		}
		
		// Mislim da je ovo sad suvisno ali hajd'...
		for (Zaposleni z : listZaposleni) {
			lijeciDao.deleteLijeciByIdZaposlenog(z.getIdZaposlenog());
			vrcaMedDao.deleteVrcaMedByIdZaposlenog(z.getIdZaposlenog());
			pregledaDao.deletePregledaByIdZaposlenog(z.getIdZaposlenog());
		}
		
		drustvoDao.deleteAllDrustvaFromPcelinjak(IdPcelinjaka);
		zaposleniDao.deleteAllZaposleniFromPcelinjak(IdPcelinjaka);
		
		new PosjedujePropolisDao().deleteByIdPcelinjaka(IdPcelinjaka);
		new PosjedujeMedDao().deleteByIdPcelinjaka(IdPcelinjaka);
		
		LinkedList<Kupovina> kupovine = (LinkedList<Kupovina>)kupovinaDao.getAllKupovinaByIdPcelinjaka(IdPcelinjaka);
		
		for (Kupovina k:kupovine) {
			
			stMedDao.deleteByIdKupovine(k.getIdKupovine());
			stPropolisDao.deleteByIdKupovine(k.getIdKupovine());
		}
		
		kupovinaDao.deleteByIdPcelinjaka(IdPcelinjaka);
		initializeScene();
		}
	}
	
	public void showDrustva() {
		
		int IdPcelinjaka = (new PcelinjakDao().getByName(cbNazivPcelinjaka.getValue())).getIdPcelinjaka();
		
		UpravljajDrustvimaController udc = new UpravljajDrustvimaController(IdPcelinjaka,this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        udc.start(stage);
                        udc.initializeScene();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);
	}
	
	
	public void showZaposlene() {
		
		int IdPcelinjaka = (new PcelinjakDao().getByName(cbNazivPcelinjaka.getValue())).getIdPcelinjaka();
		
		UpravljajZaposlenimaController uzc = new UpravljajZaposlenimaController(IdPcelinjaka,this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        uzc.start(stage);
                        uzc.initializeScene();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);
	}
	
	public void showKupovine() {
		
		int IdPcelinjaka = (new PcelinjakDao().getByName(cbNazivPcelinjaka.getValue())).getIdPcelinjaka();
		
		UpravljajKupovinamaController ukc = new UpravljajKupovinamaController(IdPcelinjaka,this);
		Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        ukc.start(stage);
                        ukc.initializeScene();
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);
	}
	
	public void logout() {
		
		LoginController login = new LoginController();
		Timer timer = new Timer();
		thisStage.close();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            { 
            	
                Platform.runLater(() ->
                {
                    try {
                     
                        Stage stage = new Stage();
                        login.start(stage);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    
                });
                timer.cancel();
            }
        }, 0);
	}
	
}
