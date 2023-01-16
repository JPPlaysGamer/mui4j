package com.iei.mui4j.examples;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.iei.mui4j.MUIApp;
import com.iei.mui4j.Updater.WrapperGetter;
import com.iei.mui4j.components.ComponentWrapper;
import com.iei.mui4j.components.ComponentWrapper.ComponentType;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FXController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem miAbout;

    @FXML
    private MenuItem miClose;

    @FXML
    private MenuItem mienus;

    @FXML
    private MenuItem mieses;

    @FXML
    private MenuItem miptbr;

    @FXML
    private Menu mnHelp;

    @FXML
    private Menu mnLang;

    @FXML
    private Menu mnwin;

    private Alert alert;
    
    @FXML
    void about(ActionEvent event) {
    	alert.showAndWait();
    }

    @FXML
    void exit(ActionEvent event) {
    	Platform.exit();
    }

    void change(Locale loc) {
    	try {
			MUIApp.getInstance().setNewLang(loc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void initialize() throws Exception {
        assert miAbout != null : "fx:id=\"miAbout\" was not injected: check your FXML file 'FXExample.fxml'.";
        assert miClose != null : "fx:id=\"miClose\" was not injected: check your FXML file 'FXExample.fxml'.";
        assert mienus != null : "fx:id=\"mienus\" was not injected: check your FXML file 'FXExample.fxml'.";
        assert mieses != null : "fx:id=\"mieses\" was not injected: check your FXML file 'FXExample.fxml'.";
        assert miptbr != null : "fx:id=\"miptbr\" was not injected: check your FXML file 'FXExample.fxml'.";
        assert mnHelp != null : "fx:id=\"mnHelp\" was not injected: check your FXML file 'FXExample.fxml'.";
        assert mnLang != null : "fx:id=\"mnLang\" was not injected: check your FXML file 'FXExample.fxml'.";
        assert mnwin != null : "fx:id=\"mnwin\" was not injected: check your FXML file 'FXExample.fxml'.";
        
        mienus.setOnAction(event -> change(new Locale("en", "US")));
        mieses.setOnAction(event -> change(new Locale("es", "ES")));
        miptbr.setOnAction(event -> change(new Locale("pt", "BR")));
        
        alert = new Alert(AlertType.INFORMATION);
        
        WrapperGetter getter = new WrapperGetter() {
			
			@Override
			public List<ComponentWrapper> getWrappers() {
				// TODO Auto-generated method stub
				return Arrays.asList(
					new ComponentWrapper("mnLang", ComponentType.JAVAFX, mnLang),
					new ComponentWrapper("miptbr", ComponentType.JAVAFX, miptbr),
					new ComponentWrapper("mienus", ComponentType.JAVAFX, mienus),
					new ComponentWrapper("mieses", ComponentType.JAVAFX, mieses),
					new ComponentWrapper("mnHelp", ComponentType.JAVAFX, mnHelp),
					new ComponentWrapper("miAbout", ComponentType.JAVAFX, miAbout),
					new ComponentWrapper("mnwin", ComponentType.JAVAFX, mnwin),
					new ComponentWrapper("miClose", ComponentType.JAVAFX, miClose),
					new ComponentWrapper("alert", ComponentType.JAVAFX, alert)
				);
			}
		};
		
		MUIApp.registerAndUpdate("JavaFx Example", FXExampleConfig.class, getter);
        
    }

}
