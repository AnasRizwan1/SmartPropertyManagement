package com.example.smartpropertymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.*;
import java.io.IOException;
import java.sql.Connection;

public class AddInstallment implements sceneToDashboard, clearInfo, saveInfo {
    private static final String url = "jdbc:mysql://localhost:3306/temporary";
    private static final String username = "root";
    private static final String password = "GravityFalls1708";
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
