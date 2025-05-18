package com.example.smartpropertymanagementsystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCustomer implements sceneToDashboard, clearInfo, saveInfo {
    @FXML TextField CNICTextField;
    @FXML TextField fNameTextField;
    @FXML TextField lNameTextField;
    @FXML DatePicker registrationDatePicker;
    @FXML TextField addressTextField;
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }
    @Override
    public void clearInformation(ActionEvent event) {
        CNICTextField.clear();
        fNameTextField.clear();
        lNameTextField.clear();
        registrationDatePicker.setValue(null);
        addressTextField.clear();
    }
    @Override
    public void saveInformation(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String query = "Call AddCustomer('" + CNICTextField.getText() + "','" + fNameTextField.getText() + "','" + lNameTextField.getText()
                + "','" + addressTextField.getText() + "', STR_TO_DATE('" + registrationDatePicker.getValue().toString() + "', '%Y-%m-%d') ,'03264014407',null)";
        System.out.println(query);
        statement.executeQuery(query);
    }
}
