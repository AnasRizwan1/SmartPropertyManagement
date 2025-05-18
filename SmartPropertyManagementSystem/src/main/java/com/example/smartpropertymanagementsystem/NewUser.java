package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class  NewUser implements sceneToDashboard {

    @FXML
    private JFXButton clearButton;

    @FXML
    private Label matchPasswordLabel;

    @FXML
    private PasswordField passWordTextField;

    @FXML
    private JFXButton previousButton;

    @FXML
    private PasswordField retypePasswordTextField;

    @FXML
    private JFXButton saveButton;

    @FXML
    private TextField userNameTextField;

    @FXML
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @FXML
    void clearInformation(ActionEvent event) {
        userNameTextField.clear();
        passWordTextField.clear();
        retypePasswordTextField.clear();
        matchPasswordLabel.setVisible(false);

    }

    @FXML
    void saveInformation(ActionEvent event) {
        if (passWordTextField.getText().equals(retypePasswordTextField.getText())) {
            matchPasswordLabel.setVisible(false);
        } else {
            matchPasswordLabel.setVisible(true);
        }
    }
    public void initialize() {
        matchPasswordLabel.setVisible(false);

    }

}
