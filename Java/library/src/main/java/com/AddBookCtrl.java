package com;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddBookCtrl implements Initializable{
    DatabaseHandler databaseHandler;
    @FXML
    private TextField Author;

    @FXML
    private TextField BookID;

    @FXML
    private TextField BookTitle;

    @FXML
    private Button CancleAddBook;

    @FXML
    private TextField Publisher;

    @FXML
    private Button SaveBook;

    @FXML
    void CancleAddBook(ActionEvent event) {
        
    }
    @FXML
    void SaveBook(ActionEvent event) {
        String bookID = BookID.getText();
        String bookAuthor = Author.getText();
        String bookTitle = BookTitle.getText();
        String bookPublisher = Publisher.getText();
        if(bookID.isEmpty() || bookAuthor.isEmpty() || bookTitle.isEmpty() || bookPublisher.isEmpty())
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(null);
            alert.setHeaderText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }
        String quCheck = "SELECT * FROM BOOK WHERE id = '" + bookID + "'"; // Truy vấn kiểm tra xem ID có chưa
        String qu = "INSERT INTO Book (id, title, author, publisher, isAvail) VALUES (" + 
            "'" + bookID + "'," +
            "'" + bookTitle + "'," +
            "'" + bookAuthor + "'," +
            "'" + bookPublisher + "'," +
            "true" +
            ")";
        ResultSet Rs = databaseHandler.SelectQuery(quCheck);
        try {
            if(Rs.next()){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText("Đã có ID sách");
                alert.showAndWait();
            } else if(databaseHandler.Insert(qu)){
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle(null);
                    alert.setHeaderText("Sucess");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle(null);
                    alert.setHeaderText("Failed");
                    alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        databaseHandler = new DatabaseHandler();
    }

}
