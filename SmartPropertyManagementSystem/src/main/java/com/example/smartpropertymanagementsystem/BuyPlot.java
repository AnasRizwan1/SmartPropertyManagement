package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

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
    @FXML private Text successfulText;

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
        int plan  = 0;
        if(threeMonthRadioButton.isSelected())
            plan = 3;
        else if(threeYearRadioButton.isSelected())
            plan = 36;
        else if(twelveMonthRadioButton.isSelected())
            plan = 12;
        else if(sixMonthRadioButton.isSelected())
            plan = 6;
        String query = "Call BuyPlot('" + CNICTextField.getText() + "'," + Integer.parseInt(plotNoTextField.getText())
                + "," + plan + ", STR_TO_DATE('" + installmentStartDateTextField.getValue().toString() + "', '%Y-%m-%d'))";
        System.out.println(query);
        statement.executeQuery(query);
        successfulText.setVisible(true);
        clearInformation(event);
    }
}
