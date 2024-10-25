package com;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SignUpCtrl implements Initializable {
    DatabaseHandler databaseHandler;
    @FXML
    private TextField PassWord;
    @FXML
    private Button SignUpButton;
    @FXML
    private Button SignInButton;
    @FXML
    private TextField UserName;
    public void initialize(URL arg0, ResourceBundle arg1) {
        databaseHandler = new DatabaseHandler();
        SignUpButton.setDisable(true);
        UserName.textProperty().addListener((observe,oldValue,newValue)->{
            SignUpButton.setDisable(UserName.getText().trim().isEmpty() || PassWord.getText().trim().isEmpty());
        });
        PassWord.textProperty().addListener((observe,oldValue,newValue)->{
            SignUpButton.setDisable(UserName.getText().trim().isEmpty() || PassWord.getText().trim().isEmpty());
        });
    }
    @FXML
    void SignInButton(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignInScene.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)SignInButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void SignUpButton(ActionEvent event) {
        String tendangnhap = UserName.getText();
        String matkhau = PassWord.getText();
        String checkQu = "Select * From AppUser Where userName = '" + tendangnhap + "' and password = '" + matkhau+ "'";
        ResultSet RsUserName = databaseHandler.SelectQuery(checkQu);
        try {
            if(RsUserName.next() )
            {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText("vao di");
                alert.setHeaderText("header");
                alert.setTitle("title");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setContentText("Sai ten dang nhap hoac mat khau");
                alert.setHeaderText("header");
                alert.setTitle("title");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
