package com;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class SignInCtrl implements Initializable {
    DatabaseHandler databaseHandler;
    @FXML
    private DatePicker BirthDay;

    @FXML
    private Button CancleSignInButton;

    @FXML
    private Button ConfirmSignInButton;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField Number;

    @FXML
    private TextField PassWord;

    @FXML
    private TextField RePassWord;

    @FXML
    private ComboBox<String> SexSelection;

    @FXML
    private TextField UserName;

    @FXML
    void CancleSignIn(ActionEvent event) {

    }
    @FXML
    void ConfirmSignIn(ActionEvent event) {
        
        String tendangnhap = UserName.getText();
        String matkhau = PassWord.getText();
        String quCheck = "SELECT * FROM AppUser WHERE userName = '" + tendangnhap + "'"; // Truy vấn kiểm tra xem ID có chưa
        String qu = "INSERT INTO APPUSER (UserName,PassWord) VALUES (" + 
            "'" + tendangnhap + "'," +
            "'" + matkhau + "'" +
            ")";
        ResultSet Rs = databaseHandler.SelectQuery(quCheck);
        try {
            if(Rs.next()){ // nếu userName tồn tại thì thông báo
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText("Đã có username");
                alert.showAndWait();
            }else 
            {
                if(!PassWord.getText().equals(RePassWord.getText())){ // Kiểm tra repassword
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setHeaderText("2 mat khau ko trung nhau");
                    alert.showAndWait();
                }else{
                    if(databaseHandler.Insert(qu))
                    {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Vietdeptrai");
                        alert.setHeaderText("Tạo tài khoản thành công rực rỡ và chuyển sang màn hình đăng nhập nhưng chưa làm đén đây");
                        ButtonType ButtonTypeConinue = new ButtonType("Tạo tiếp tài khoản");
                        ButtonType ButtonTypeSignUp = new ButtonType("Đăng nhập", ButtonBar.ButtonData.CANCEL_CLOSE);
                        alert.getButtonTypes().setAll(ButtonTypeConinue,ButtonTypeSignUp);
                        alert.showAndWait().ifPresent(response ->{
                            if(response == ButtonTypeConinue)
                            {

                            }else{
                                
                            }
                        });
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        SexSelection.setItems(FXCollections.observableArrayList("Man","Woman"));
        databaseHandler = new DatabaseHandler();
    }
}

