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

public class LoginPage {
    private Stage primaryStage;
    private Scene scene;
    private Parent root;

    @FXML private TextField userNameField;
    @FXML private PasswordField passWordField;
    @FXML private Text invalidPasswordText;

    public void login(ActionEvent event) throws IOException {
        if(userNameField.getText().equals("admin") && passWordField.getText().equals("admin")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            root = fxmlLoader.load();
            primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            primaryStage.setTitle("Dashboard");
            scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ui/dashboard.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } else
            invalidPasswordText.setVisible(true);
    }
}