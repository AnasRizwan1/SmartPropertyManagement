package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddHead implements sceneToDashboard, saveInfo, clearInfo {
    @FXML TextField headTextField;
    @FXML JFXRadioButton incomeRadioButton;
    @FXML JFXRadioButton expenseRadioButton;
    private static final String url = "jdbc:mysql://localhost:3306/temporary";
    private static final String username = "root";
    private static final String password = "GravityFalls1708";
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @Override
    public void clearInformation(ActionEvent event) {
        headTextField.clear();
        incomeRadioButton.setSelected(false);
        expenseRadioButton.setSelected(false);
    }

    @Override
    public void saveInformation(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String query = "Call Procedure whatever";
        statement.execute(query);
    }
}
