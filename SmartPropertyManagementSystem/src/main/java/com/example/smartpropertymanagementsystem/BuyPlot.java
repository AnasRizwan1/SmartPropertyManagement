package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BuyPlot implements sceneToDashboard{

    @FXML private TextField CNICTextField;
    @FXML private TextField advanceAmountTextField;
    @FXML private JFXButton clearButton;
    @FXML private DatePicker installmentStartDateTextField;
    @FXML private TextField plotNoTextField;
    @FXML private JFXButton previousButton;
    @FXML private JFXButton saveButton;
    @FXML private JFXRadioButton sixMonthRadioButton;
    @FXML private ToggleGroup status;
    @FXML private JFXRadioButton threeMonthRadioButton;
    @FXML private JFXRadioButton threeYearRadioButton;
    @FXML private JFXRadioButton twelveMonthRadioButton;

    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @FXML
    void clearInformation(ActionEvent event) {
        CNICTextField.clear();
        advanceAmountTextField.clear();
        plotNoTextField.clear();
        installmentStartDateTextField.setValue(null);
        threeMonthRadioButton.setSelected(false);
        threeYearRadioButton.setSelected(false);
        twelveMonthRadioButton.setSelected(false);
        sixMonthRadioButton.setSelected(false);
    }

    @FXML
    void saveInformation(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String query = "Call Procedure Whatever";
        statement.execute(query);
    }
}
