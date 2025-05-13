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

    public void initialize(){
        mainPane.setStyle("-fx-background-color: lightblue;");
        addNewPane.setStyle("-fx-background-color: red;");
        reportsPane.setStyle("-fx-background-color: #a54d2a;");
        paymentPane.setStyle("-fx-background-color: black;");
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
            slide(slidingPane, 200);
        } else
            activePane = null;
    }
    private void slide(Pane pane, int coordinate){
        TranslateTransition slideIn = new TranslateTransition(Duration.millis(250), pane);
        slideIn.setToX(coordinate);
        slideIn.play();
    }
    public void changeScene(ActionEvent event) throws IOException{
        String fxid = ((Node) event.getSource()).getId();
        switch(fxid){
            case "plotButton" -> {loader(event, "addPlot.fxml");}
            case "customerButton" -> {loader(event, "addCustomer.fxml");}
            case "headButton" -> {loader(event, "addHead.fxml");}
            case "incomeReportButton" -> {loader(event, "incomeReport.fxml");}
            case "expenseReportButton" -> {loader(event, "expenseReport.fxml");}
            case "customerLedgerButton" -> {loader(event, "customerLedger.fxml");}
            case "addAdvanceButton" -> {loader(event, "addAdvance.fxml");}
            case "addInstallmentButton" -> {loader(event, "addInstallment.fxml");}
            case "confirmationButton" -> {loader(event, "confirmation.fxml");}
            case "receiptButton" -> {loader(event, "receipt.fxml");}}
    }
    private void loader(ActionEvent event ,String fxmlFile) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        root = fxmlLoader.load();
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("ui/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
