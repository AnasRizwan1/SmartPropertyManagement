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
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";
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
        String type = "";
        if(incomeRadioButton.isSelected())
            type = "income";
        else if(expenseRadioButton.isSelected())
            type = "expense";
        String query = "Call AddHead('" +  headTextField.getText() + "', '" + type + "')";
        System.out.println(query);
        statement.executeQuery(query);
    }
}
