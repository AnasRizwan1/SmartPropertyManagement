package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddPlot implements sceneToDashboard,clearInfo, saveInfo {
    @FXML TextField areaTextField;
    @FXML TextField priceTextField;
    @FXML TextField addressTextField;
    @FXML TextField CNICTextField;
    @FXML JFXRadioButton ownedRadioButton;
    @FXML JFXRadioButton unOwnedRadioButton;
    @FXML JFXRadioButton inProgressRadioButton;
    @FXML JFXCheckBox cornerCheckBox;
    @FXML JFXCheckBox mainRoadCheckBox;
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @Override
    public void clearInformation(ActionEvent event) {
        areaTextField.clear();
        priceTextField.clear();
        addressTextField.clear();
        CNICTextField.clear();
        ownedRadioButton.setSelected(false);
        unOwnedRadioButton.setSelected(false);
        inProgressRadioButton.setSelected(false);
        cornerCheckBox.setSelected(false);
        mainRoadCheckBox.setSelected(false);
    }

    @Override
    public void saveInformation(ActionEvent event) {

    }
}
