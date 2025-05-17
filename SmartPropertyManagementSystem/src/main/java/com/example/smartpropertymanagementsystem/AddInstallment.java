package com.example.smartpropertymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.*;
import java.io.IOException;
import java.sql.Connection;

public class AddInstallment implements sceneToDashboard, clearInfo, saveInfo {
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";
    @FXML TextField plotNoTextField;
    @FXML TextField paidAmountTextField;
    @FXML TextField installmentNoTextField;
    @FXML DatePicker receivingDatePicker;
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @Override
    public void clearInformation(ActionEvent event) {
        plotNoTextField.clear();
        paidAmountTextField.clear();
        installmentNoTextField.clear();
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
