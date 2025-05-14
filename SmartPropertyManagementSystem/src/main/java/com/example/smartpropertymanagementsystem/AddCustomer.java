package com.example.smartpropertymanagementsystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddCustomer implements sceneToDashboard, clearInfo, saveInfo {
    @FXML TextField CNICTextField;
    @FXML TextField fNameTextField;
    @FXML TextField lNameTextField;
    @FXML TextField registrationDateTextField;
    @FXML TextField addressTextField;
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }
    @Override
    public void clearInformation(ActionEvent event) {
        CNICTextField.clear();
        fNameTextField.clear();
        lNameTextField.clear();
        registrationDateTextField.clear();
        addressTextField.clear();
    }
    @Override
    public void saveInformation(ActionEvent event) {

    }
}
