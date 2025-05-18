package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class AddExpense implements  sceneToDashboard{

    @FXML
    private TextField amountTextField;

    @FXML
    private JFXButton clearButton;

    @FXML
    private DatePicker dateTextField;

    @FXML
    private Text headName;

    @FXML
    private TextField headNameTextField;

    @FXML
    private JFXButton previousButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @FXML
    void clearInformation(ActionEvent event) {

    }

    @FXML
    void saveInformation(ActionEvent event) {

    }

}