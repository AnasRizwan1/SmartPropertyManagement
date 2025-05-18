package com.example.smartpropertymanagementsystem;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;

import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.*;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import  javafx.geometry.*;

import javax.swing.text.LabelView;
import java.awt.*;

import java.io.IOException;

public class Dashboard {
    private static Pane activePane;
    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    @FXML
    private JFXTextArea userPrompt;
    @FXML
    private ImageView sendButton;
    @FXML private ScrollPane chatBotScrollPane;
    @FXML private VBox chatDisplayContainer;
    @FXML private Pane mainPane;
    @FXML private Pane addNewPane;
    @FXML private Pane reportsPane;
    @FXML private Pane paymentPane;
    @FXML
    private Button chatBotButton;

    @FXML
    private SplitPane chatBotPane;
    @FXML private Label dateTimeLabel;
    boolean check = false;
    @FXML
    private Button addExpense;

    @FXML
    private Button addIncome;
    public void initialize(){
            mainPane.setStyle("-fx-background-color: #011b3c");


//        addNewPane.setStyle("-fx-background-color: red;");
//        reportsPane.setStyle("-fx-background-color: #a54d2a;");
//        paymentPane.setStyle("-fx-background-color: black;");
    }

    public void tapTriggerChatBot(ActionEvent event){
        if (check ){
            chatBotPlaneSlide(chatBotPane, 0);
            check = false;

        } else {
            chatBotPlaneSlide(chatBotPane, -585);
            check = true;
        }
    }
    public void slider(ActionEvent event){
        String fxid = ((Node) event.getSource()).getId();
        Pane slidingPane = switch (fxid) {
            case "addNewButton" -> addNewPane;
            case "reportsButton" -> reportsPane;
            case "paymentButton" -> paymentPane;
            default -> null;
        };
        if(activePane != null && slidingPane != null)
            slide(activePane, 0);
        if(slidingPane != activePane){
            activePane = slidingPane;
            slide(slidingPane, 205);
        } else
            activePane = null;
    }
    private void slide(Pane pane, int coordinate){
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(250), pane);
        slideIn.setToX(coordinate);
        slideIn.play();
    }
    private void chatBotPlaneSlide(SplitPane pane, int coordinate ){
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(500), pane);
        slideIn.setToY(coordinate);
        slideIn.play();
    }
    @FXML
    public void sendMessage(MouseEvent mouseEvent) {
        String message = userPrompt.getText().trim();
        if(!message.isEmpty())
        {
            System.out.println("User: " + message);
            displayUserPrompt(message);
            userPrompt.clear();

            String geminiResponse = Gemini.callGemini(message);
            displayGeminiPrompt(geminiResponse);




        }
    }

    private void displayUserPrompt(String message) {
        Label label = new Label(message);
        label.setStyle("-fx-background-color: #0057b7; -fx-text-fill: white; -fx-padding: 10 15; -fx-background-radius: 15 0 15 15;");
        label.setWrapText(true);
        label.setMaxWidth(300);

        HBox messageBox = new HBox(label);
        messageBox.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(messageBox, new Insets(10, 0, 10, 0));// Right side
        chatDisplayContainer.getChildren().add(messageBox);
    }
    private void displayGeminiPrompt(String message) {
        Label label = new Label(message);
        label.setStyle("-fx-background-color: #e0e0e0; -fx-text-fill: black; -fx-padding: 10 15; -fx-background-radius: 0 15 15 15;");
        label.setWrapText(true);
        label.setMaxWidth(300);

        HBox messageBox = new HBox(label);
        messageBox.setAlignment(Pos.CENTER_LEFT); // Left side
        VBox.setMargin(messageBox, new Insets(10, 0, 10, 0)); // Left side
        chatDisplayContainer.getChildren().add(messageBox);
    }


    String dashBoardCss = "ui/dashboard.css";
    String formStyling= "ui/formStyling.css";
    String styleCss = "ui/style.css";
    public void changeScene(ActionEvent event) throws IOException{
        String fxid = ((Node) event.getSource()).getId();
        switch(fxid){
            case "plotButton" -> {loader(event, "addPlot.fxml" , styleCss );}
            case "customerButton" -> {loader(event, "addCustomer.fxml" ,styleCss );}
            case "headButton" -> {loader(event, "addHead.fxml", styleCss);}
            case "incomeReportButton" -> {loader(event, "incomeReport.fxml",styleCss);}
            case "expenseReportButton" -> {loader(event, "expenseReport.fxml",styleCss);}
            case "customerLedgerButton" -> {loader(event, "customerLedger.fxml",styleCss);}
            case "addAdvanceButton" -> {loader(event, "addAdvance.fxml",styleCss);}
            case "addInstallmentButton" -> {loader(event, "addInstallment.fxml",styleCss);}
            case "confirmationButton" -> {loader(event, "confirmation.fxml", styleCss);}
            case "receiptButton" -> {loader(event, "receipt.fxml" , styleCss);}
            case "addExpense" -> {loader(event, "addExpense.fxml" , styleCss);}
            case "addIncome" -> {loader(event, "addIncome.fxml" , styleCss);}
            case "buyPlot" -> {loader(event, "buyPlot.fxml" , styleCss);}
            case "newUser" -> {loader(event, "newUser.fxml" , styleCss);}
        }
    }
    private void loader(ActionEvent event ,String fxmlFile , String cssFile) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        root = fxmlLoader.load();
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void logout(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        root = fxmlLoader.load();
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("ui/loginForm.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
