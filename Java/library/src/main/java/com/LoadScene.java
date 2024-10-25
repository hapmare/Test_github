package com;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadScene implements Initializable {
    public static LoadScene instance;
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        instance = this; 
    }
}
