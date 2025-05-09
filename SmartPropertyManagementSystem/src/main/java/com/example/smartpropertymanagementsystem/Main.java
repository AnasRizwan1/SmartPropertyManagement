package com.example.smartpropertymanagementsystem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class Main extends Application {
    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        //Add here the first function that should be called the form
    }

    public static void showLoginForm() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("LoginPage.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Login Form");
        primaryStage.show();
    }





    //This function is solely for changing the scene using the controller classes. If functionality
    //is needed, it should be added in the controller classes. Else it should not be used.
    public void ChangeScene(String fxml, String title ,  int width , int height) throws IOException {
        FXMLLoader root = new FXMLLoader(Main.class.getResource(fxml));
        Scene scene = new Scene(root.load(), width, height);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
    }





}
