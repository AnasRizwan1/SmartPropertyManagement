package com.example.smartpropertymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class LoginPage {
    private Stage primaryStage;
    private Scene scene;
    private Parent root;

    @FXML private TextField userNameField;
    @FXML private PasswordField passwordField;
    @FXML private Text invalidPasswordText;
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";

    public void login(ActionEvent event) throws IOException, SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM LoginAccess";
        ResultSet resultSet = statement.executeQuery(query);
        boolean flag = false;
        while (resultSet.next())
            if (resultSet.getString("userName").equals(userNameField.getText()) && resultSet.getString("passWord").equals(passwordField.getText()))
                flag = true;
        if(flag) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = fxmlLoader.load();
            Dashboard controller = fxmlLoader.getController();
            controller.setUser(userNameField.getText());
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Dashboard");
            primaryStage.centerOnScreen();
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ui/dashboard.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
            primaryStage.show();
        } else
            invalidPasswordText.setVisible(true);
    }
}