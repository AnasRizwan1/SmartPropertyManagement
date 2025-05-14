package com.example.smartpropertymanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;

import javax.swing.text.LabelView;
import java.awt.*;
import java.io.IOException;

public class Dashboard {
    private static Pane activePane;
    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML private Pane mainPane;
    @FXML private Pane addNewPane;
    @FXML private Pane reportsPane;
    @FXML private Pane paymentPane;

    @FXML
    private Label dateTimeLabel;

    public void initialize(){
            mainPane.setStyle("-fx-background-color: #011b3c");

//        addNewPane.setStyle("-fx-background-color: red;");
//        reportsPane.setStyle("-fx-background-color: #a54d2a;");
//        paymentPane.setStyle("-fx-background-color: black;");
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
            case "receiptButton" -> {loader(event, "receipt.fxml" , styleCss);}}
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
}
