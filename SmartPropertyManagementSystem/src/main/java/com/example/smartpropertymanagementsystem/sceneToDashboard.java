package com.example.smartpropertymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public interface sceneToDashboard {
    default void switchToDashboard(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        Parent root = fxmlLoader.load();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("Dashboard");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("ui/dashboard.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}