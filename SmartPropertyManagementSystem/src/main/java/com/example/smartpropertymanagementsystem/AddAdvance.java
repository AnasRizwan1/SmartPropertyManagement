package com.example.smartpropertymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.*;
import java.io.IOException;

public class AddAdvance implements sceneToDashboard, clearInfo, saveInfo {
    @FXML TextField plotNoTextField;
    @FXML TextField amountTextField;
    @FXML TextField receivingDateTextField;
    @FXML TextField paidAmountTextField;
    @FXML Button saveButton;
    @FXML Button clearButton;

    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @Override
    public void clearInformation(ActionEvent event) {
        plotNoTextField.clear();
        amountTextField.clear();
        receivingDateTextField.clear();
        paidAmountTextField.clear();
    }

    @Override
    public void saveInformation(ActionEvent event) {

    }
}