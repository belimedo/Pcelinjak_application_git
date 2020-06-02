package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.dao.VlasnikDao;
import model.dao.ZaposleniDao;

public class ZaposleniController extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/ZaposleniForm.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("App");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e->{
            e.consume();
            System.out.println("Application is ending, please wait for few moments...");
            primaryStage.close();
        });
        primaryStage.show();
	}
		
	public void setData(ZaposleniDao zd ) {
		
	}

}
