package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddHead implements sceneToDashboard, saveInfo, clearInfo {
    @FXML TextField headTextField;
    @FXML JFXRadioButton incomeRadioButton;
    @FXML JFXRadioButton expenseRadioButton;
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
    public void saveInformation(ActionEvent event) {
        //Aoun
    }
}
