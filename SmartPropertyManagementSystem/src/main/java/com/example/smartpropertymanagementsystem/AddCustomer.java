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
    private static final String url = "jdbc:mysql://localhost:3306/temporary";
    private static final String username = "root";
    private static final String password = "GravityFalls1708";
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
        String query = "Call Procedure whatever";
        statement.execute(query);
    }
}
