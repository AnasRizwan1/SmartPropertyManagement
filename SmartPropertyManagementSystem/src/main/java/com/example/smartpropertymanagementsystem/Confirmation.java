package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Confirmation implements sceneToDashboard, Initializable {
    @FXML TreeTableView<ConfirmTable> treeTableView;
    @FXML TreeTableColumn<ConfirmTable, Integer> paymentIDColumn;
    @FXML TreeTableColumn<ConfirmTable, Integer> plotNoColumn;
    @FXML TreeTableColumn<ConfirmTable, String> typeColumn;
    @FXML TreeTableColumn<ConfirmTable, Integer> amountColumn;
    @FXML TreeTableColumn<ConfirmTable, Integer> paidAmountColumn;
    @FXML TreeTableColumn<ConfirmTable, Void> action;
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @Override
    public void initialize(URL urrl, ResourceBundle resourceBundle) {
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM ConfirmationView";
            ResultSet resultSet = statement.executeQuery(query);
            ObservableList<ConfirmTable> observableList = FXCollections.observableArrayList();
            while (resultSet.next())
                observableList.add(new ConfirmTable(resultSet.getInt("paymentID"),
                resultSet.getInt("plotNo"), resultSet.getString("type"),
                resultSet.getInt("amount"), resultSet.getInt("paidAmount")));
            System.out.println("Rows fetched: "  + observableList.size());
            paymentIDColumn.setCellValueFactory(param -> param.getValue().getValue().paymentIDProperty().asObject());
            plotNoColumn.setCellValueFactory(param -> param.getValue().getValue().plotNoProperty().asObject());
            typeColumn.setCellValueFactory(param -> param.getValue().getValue().typeProperty());
            amountColumn.setCellValueFactory(param -> param.getValue().getValue().amountProperty().asObject());
            paidAmountColumn.setCellValueFactory(param -> param.getValue().getValue().paidAmountProperty().asObject());
            // Add button column
            action.setCellFactory(param -> new javafx.scene.control.TreeTableCell<>() {
                private final JFXButton btn = new JFXButton("Confirm");{
                    btn.getStyleClass().add("ui/style.css");
                    btn.setStyle("-fx-background-color: #28965a;");
                    btn.setOnAction(event -> {
                        TreeTableRow<ConfirmTable> row = getTreeTableRow();
                        ConfirmTable data = row.getItem();
                        if (data != null)
                            System.out.println("clicked plotNo: " + data.paymentIDProperty().getValue());
                        String query2 = "Update Payment SET confirmed = 1 WHERE paymentID = " + data.paymentIDProperty().getValue();
                        try {
                            statement.executeUpdate(query2);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        TreeItem<ConfirmTable> itemToRemove = row.getTreeItem();
                        if (itemToRemove != null && itemToRemove.getParent() != null) {
                            itemToRemove.getParent().getChildren().remove(itemToRemove);
                        }
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || getTreeTableRow().getItem() == null)
                        setGraphic(null);
                     else
                        setGraphic(btn);
                }
            });
            TreeItem<ConfirmTable> root = new RecursiveTreeItem<ConfirmTable>(observableList, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
            treeTableView.setShowRoot(false);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
class ConfirmTable extends RecursiveTreeObject<ConfirmTable> {
    private final IntegerProperty paymentID;
    private final IntegerProperty plotNo;
    private final StringProperty type;
    private final IntegerProperty amount;
    private final IntegerProperty paidAmount;
    ConfirmTable(Integer paymentID, Integer plotNo, String type, int amount, int paidAmount) {
        this.paymentID = new SimpleIntegerProperty(paymentID);
        this.plotNo = new SimpleIntegerProperty(plotNo);
        this.type = new SimpleStringProperty(type);
        this.amount = new SimpleIntegerProperty(amount);
        this.paidAmount = new SimpleIntegerProperty(paidAmount);
    }
    public IntegerProperty paymentIDProperty() {
        return paymentID;
    }
    public IntegerProperty plotNoProperty() {
        return plotNo;
    }
    public StringProperty typeProperty() {
        return type;
    }
    public IntegerProperty amountProperty() {
        return amount;
    }
    public IntegerProperty paidAmountProperty() {
        return paidAmount;
    }
}