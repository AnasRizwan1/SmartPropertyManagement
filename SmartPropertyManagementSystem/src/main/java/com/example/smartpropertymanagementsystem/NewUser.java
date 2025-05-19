package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class  NewUser implements sceneToDashboard {

    @FXML private JFXButton clearButton;
    @FXML private Label matchPasswordLabel;
    @FXML private PasswordField passWordTextField;
    @FXML private JFXButton previousButton;
    @FXML private PasswordField retypePasswordTextField;
    @FXML private JFXButton saveButton;
    @FXML private TextField userNameTextField;
    @FXML private Text successfulText;
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";

    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @FXML
    void clearInformation(ActionEvent event) {
        userNameTextField.clear();
        passWordTextField.clear();
        retypePasswordTextField.clear();
        matchPasswordLabel.setVisible(false);

    }

    @FXML
    void saveInformation(ActionEvent event) throws SQLException {
        if (passWordTextField.getText().equals(retypePasswordTextField.getText())) {
            Connection connection =  DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String query = "INSERT INTO LoginAccess(userName, passWord) VALUES ('" + userNameTextField.getText() + "','" + passWordTextField.getText() + "')";
            statement.executeQuery(query);
            clearInformation(event);
            successfulText.setVisible(true);
        } else {
            matchPasswordLabel.setVisible(true);
        }
    }
    public void initialize() {
        matchPasswordLabel.setVisible(false);

    }

}
