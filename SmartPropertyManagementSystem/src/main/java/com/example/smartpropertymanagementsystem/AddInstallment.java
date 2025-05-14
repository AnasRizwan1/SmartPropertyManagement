package com.example.smartpropertymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddInstallment implements sceneToDashboard, clearInfo, saveInfo {
    @FXML TextField plotNoTextField;
    @FXML TextField receivingDateTextField;
    @FXML TextField amountTextField;
    @FXML TextField installmentNoTextField;
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @Override
    public void clearInformation(ActionEvent event) {
        plotNoTextField.clear();
        receivingDateTextField.clear();
        amountTextField.clear();
        installmentNoTextField.clear();
    }
    @Override
    public void saveInformation(ActionEvent event) {
        //Aoun
    }
}
