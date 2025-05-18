package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class BuyPlot implements sceneToDashboard{

    @FXML
    private TextField CNICTextField;

    @FXML
    private TextField advanceAmountTextField;

    @FXML
    private JFXButton clearButton;

    @FXML
    private DatePicker installmentStartDateTextField;

    @FXML
    private TextField plotNoTextField;

    @FXML
    private JFXButton previousButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXRadioButton sixMonthRadioButton;

    @FXML
    private ToggleGroup status;

    @FXML
    private ToggleGroup status1;

    @FXML
    private JFXRadioButton threeMonthRadioButton;

    @FXML
    private JFXRadioButton threeYearRadioButton;

    @FXML
    private JFXRadioButton twelveMonthRadioButton;

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
