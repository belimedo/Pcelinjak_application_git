package util;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PopUpWindow {
	
	public static void showMessage(String title, String infoText, String mesageText) {
		
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

	                    infoLabel.setText(infoText);
	                    msgLabel.setText(mesageText);
	                    Stage stage = new Stage();
	                    stage.setTitle(title);
	                    stage.setScene(new Scene(root));
	                    stage.setResizable(false);
	                    stage.setAlwaysOnTop(true);
	                    stage.initModality(Modality.APPLICATION_MODAL);
	                    PauseTransition delay = new PauseTransition(Duration.seconds(2));
	                    delay.setOnFinished( event -> stage.close() );
	                    delay.play();
	                    stage.show();

	                });
	                timer.cancel();
	            }
	            }, 0);
	}


}
