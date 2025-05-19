package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddIncome implements sceneToDashboard {

    @FXML private TextField amountTextField;
    @FXML private JFXButton clearButton;
    @FXML private DatePicker dateTextField;
    @FXML private Text headName;
    @FXML private TextField headNameTextField;
    @FXML private JFXButton previousButton;
    @FXML private JFXButton saveButton;
    @FXML private Text successfulText;



    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";

    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @FXML
    void clearInformation(ActionEvent event) {
        headNameTextField.clear();
        amountTextField.clear();
        dateTextField.setValue(null);
    }

    @FXML
    void saveInformation(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String query = "Call AddIncome(STR_TO_DATE('" + dateTextField.getValue().toString()
                + "', '%Y-%m-%d'), '" + headNameTextField.getText() + "',"  + Integer.parseInt(amountTextField.getText()) + ")";
        System.out.println(query);
        statement.executeQuery(query);
        successfulText.setVisible(true);
        clearInformation(event);
    }

}
