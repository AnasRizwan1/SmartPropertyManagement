package com.example.smartpropertymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.*;
import java.io.IOException;

public class AddAdvance implements sceneToDashboard, clearInfo, saveInfo {
    @FXML TextField plotNoTextField;
    @FXML DatePicker receivingDatePicker;
    @FXML TextField paidAmountTextField;
    private static final String url = "jdbc:mysql://localhost:3306/temporary";
    private static final String username = "root";
    private static final String password = "password";
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @Override
    public void clearInformation(ActionEvent event) {
        plotNoTextField.clear();
        paidAmountTextField.clear();
        receivingDatePicker.setValue(null);
    }

    @Override
    public void saveInformation(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String query = "Call Procedure whatever";
        statement.execute(query);
    }
}
