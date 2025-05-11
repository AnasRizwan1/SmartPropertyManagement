package com.example.smartpropertymanagementsystem;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import java.io.IOException;

public class Dashboard {
    private static Pane activePane;

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
    public void slider(ActionEvent event) throws IOException{
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
}
