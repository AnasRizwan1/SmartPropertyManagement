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
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";
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
