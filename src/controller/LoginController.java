package controller;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.dao.VlasnikDao;
import model.dao.ZaposleniDao;
import model.dto.Vlasnik;
import model.dto.Zaposleni;
import util.PopUpWindow;

public class LoginController extends Application {
	
	@FXML
	private PasswordField passwordField;
	    
	@FXML
	private Button loginButton;
	    
	@FXML
	private TextField usernameField;
	
	@FXML
	private Stage loginStage;
	
	/**
	 * Ova f-ja provjerava login podatke, ukoliko su ispravni prosljedjuje na odgovarajucu formu, ukoliko nisu 
	 * ispravni prikazuje odgovarajucu poruku
	 */
	@FXML
	public void loginAction() {
		
		String username = usernameField.getText();
		String password = passwordField.getText();
		// Nema potrebe da se ista provjerava ukoliko je prazna celija
		if(username.length() != 0 && password.length() != 0) { 
			VlasnikDao vd = new VlasnikDao();
			ZaposleniDao zd = new ZaposleniDao();
			Vlasnik v = vd.getByUsername(username, password);
			if (v != null) {
				
				getAnotherStage(0,v.getIdVlasnika());
				v.print();
				loginStage.close();
				System.out.println(loginStage.getTitle());
			}
			else {
				
				Zaposleni z = zd.getByUsername(username, password);
				if (z != null) {
					
					getAnotherStage(1,z.getIdZaposlenog());
					loginStage.close();
					System.out.println(loginStage.getTitle());
				}
				else {
					usernameField.setText("");
					passwordField.setText("");
					PopUpWindow.showMessage("Greška!","Pogrešan unos","Molim Vas ponovite unos podataka.");
					//errorLoginInput(1);
				}
			}
			System.out.println("LOGIN!"+username+" - "+password);
			
		}
		else {
			usernameField.setText("");
			passwordField.setText("");
			PopUpWindow.showMessage("Greška!","Prazno polje","Jedno od polja je ostalo prazno.Molim Vas ponovite unos podataka.");
			//errorLoginInput(0);
		}
	}
	
	private void getAnotherStage(int stageType, int Id) {
		
		if (stageType == 0) {
			
			VlasnikController vc = new VlasnikController(Id);
			Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run()
	            { 
	            	
	                Platform.runLater(() ->
	                {
	                    try {
	                     
	                        Stage stage = new Stage();
	                        vc.start(stage);
	                        vc.initializeScene();
	                    }
	                    catch (IOException ex) {
	                        ex.printStackTrace();
	                    }

	                    
	                });
	                timer.cancel();
	            }
	        }, 0);
		}
		else {
			
			ZaposleniController zc = new ZaposleniController();
			Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run()
	            {
	                
	                Platform.runLater(() ->
	                {
	                    try {
	                        //Parent root = FXMLLoader.load(getClass().getResource("/view/vlasnik.fxml"));
	                        Stage stage = new Stage();
	                        zc.start(stage);
	                        
	                    }
	                    catch (IOException ex) {
	                        ex.printStackTrace();
	                    }

	                    
	                });
	                timer.cancel();
	            }
	        }, 1);
		}
		
	}
	
	private void errorLoginInput(int errorType) {
		
		if (errorType == 0) {
			
			 Label infoLabel= new Label();
		     Label msgLabel= new Label();

		        Timer timer = new Timer();
		        timer.schedule(new TimerTask() {
		            @Override
		            public void run() {
		                Platform.runLater(() ->
		                {
		                    VBox root = new VBox();

		                    root.getChildren().addAll(infoLabel,msgLabel);
		                    root.setAlignment(Pos.CENTER);
		                    root.setSpacing(20);
		                    root.setPadding(new Insets(10,10,10,10));
		                    root.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), null, null)));

		                    infoLabel.setText("Pogrešan unos!");
		                    msgLabel.setText("Jedno od polja je prazno, ponovite unos!");
		                    Stage stage = new Stage();
		                    stage.setTitle("Greška!");
		                    stage.setScene(new Scene(root));
		                    stage.setResizable(false);
		                    stage.setAlwaysOnTop(true);
		                    stage.initModality(Modality.APPLICATION_MODAL);
		                    PauseTransition delay = new PauseTransition(Duration.seconds(3));
		                    delay.setOnFinished( event -> stage.close() );
		                    delay.play();
		                    stage.show();

		                });
		                timer.cancel();
		            }
		            }, 0);
			
		}
		else {
			
			Label infoLabel= new Label();
		     Label msgLabel= new Label();

		        Timer timer = new Timer();
		        timer.schedule(new TimerTask() {
		            @Override
		            public void run() {
		                Platform.runLater(() ->
		                {
		                    VBox root = new VBox();

		                    root.getChildren().addAll(infoLabel,msgLabel);
		                    root.setAlignment(Pos.CENTER);
		                    root.setSpacing(20);
		                    root.setPadding(new Insets(10,10,10,10));
		                    root.setBackground(new Background(new BackgroundFill(Paint.valueOf("lightblue"), null, null)));

		                    infoLabel.setText("Pogrešan unos!");
		                    msgLabel.setText("Molim Vas ponovite unos korisničkog imena i lozinke");
		                    Stage stage = new Stage();
		                    stage.setTitle("Greška!");
		                    stage.setScene(new Scene(root));
		                    stage.setResizable(false);
		                    stage.initModality(Modality.APPLICATION_MODAL);
		                    PauseTransition delay = new PauseTransition(Duration.seconds(3));
		                    delay.setOnFinished( event -> stage.close() );
		                    delay.play();
		                    stage.show();

		                });
		                timer.cancel();
		            }
		            }, 0);
		}
		
	}
	
    public void startStage() {
       launch();
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login_form.fxml"));
			loader.setController(this);
			Parent root = loader.load();
	        Scene scene = new Scene(root);
            //Parent root = FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
            //Scene scene = new Scene(root);
            primaryStage.setTitle("Login form");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setOnCloseRequest(e->{
                e.consume();
                System.out.println("Login stage is closing down");
                primaryStage.close();
            });
            primaryStage.show();
            loginStage = primaryStage;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}
