package com.example.smartpropertymanagementsystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Main extends Application {
    private static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        //Add here the first function that should be called the form
        showLoginForm();
    }

    public static void showLoginForm() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("ui/buttonHover.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Smart Property Management System");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

//    public void initialize(URL location, ResourceBundle resources) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        dateTimeLabel.setText(formatter.format(now));
//    }

}
