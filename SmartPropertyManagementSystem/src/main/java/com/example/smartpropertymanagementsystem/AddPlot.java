package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPlot implements sceneToDashboard,clearInfo, saveInfo {
    @FXML TextField plotNoTextField;
    @FXML TextField areaTextField;
    @FXML TextField priceTextField;
    @FXML TextField addressTextField;
    @FXML TextField CNICTextField;
    @FXML JFXRadioButton ownedRadioButton;
    @FXML JFXRadioButton unOwnedRadioButton;
    @FXML JFXRadioButton inProgressRadioButton;
    @FXML JFXCheckBox cornerCheckBox;
    @FXML JFXCheckBox mainRoadCheckBox;
    @FXML Text successfulText;
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";
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
        plotNoTextField.clear();
    }

    @Override
    public void saveInformation(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String type = "";
        if(cornerCheckBox.isSelected() && mainRoadCheckBox.isSelected())
            type = "Corner and Main road";
        else if(cornerCheckBox.isSelected())
            type = "Corner";
        else if(mainRoadCheckBox.isSelected())
            type = "Main road";
        String status = "";
        if(ownedRadioButton.isSelected())
            status = "owned";
        else if(unOwnedRadioButton.isSelected())
            status = "unowned";
        else if(inProgressRadioButton.isSelected())
            status = "incomplete";
        String query = "Call AddPlot(" + Integer.parseInt(plotNoTextField.getText()) + "," +
                Integer.parseInt(areaTextField.getText()) + "," + Integer.parseInt(priceTextField.getText())
                + ",'" + type + "','" + status + "','" + addressTextField.getText() + "')";
        System.out.println(query);
        statement.executeQuery(query);
        successfulText.setVisible(true);
        clearInformation(event);
    }
}