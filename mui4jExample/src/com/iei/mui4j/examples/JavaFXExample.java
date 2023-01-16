package com.iei.mui4j.examples;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFXExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/com/iei/mui4j/examples/FXExample.fxml"));
		loader.setController(new FXController());
		BorderPane pane = loader.load();
		
		Scene deafult = new Scene(pane);
		primaryStage.setScene(deafult);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
