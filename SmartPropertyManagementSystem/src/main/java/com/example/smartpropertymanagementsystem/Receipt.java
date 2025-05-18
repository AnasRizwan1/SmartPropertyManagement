package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Receipt implements sceneToDashboard, Initializable {
    private final JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
    private final ObservableList<String> allSuggestions = FXCollections.observableArrayList();
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String user = "admin";
    private static final String password = "d375c123";

    @FXML Text searchByText;
    @FXML TextField searchByTextField;
    @FXML ComboBox<String> searchByComboBox;
    @FXML JFXTreeTableView<ReceiptTable> treeTableView;
    @FXML TreeTableColumn<ReceiptTable,Integer> paymentIDColumn;
    @FXML TreeTableColumn<ReceiptTable,String> paymentTypeColumn;
    @FXML TreeTableColumn<ReceiptTable,Integer> amountColumn;
    @FXML TreeTableColumn<ReceiptTable,String> ownerNameColumn;
    @FXML TreeTableColumn<ReceiptTable,String> ownerCNICColumn;
    @FXML TreeTableColumn<ReceiptTable,Integer> installmentNoColumn;
    @FXML TreeTableColumn<ReceiptTable,Integer> plotNoColumn;

    public void initialize(URL location, ResourceBundle resources) {
        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "Select * From ReceiptView;";
            initializeTreeTable(statement, query);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        searchByComboBox.getItems().addAll(Arrays.asList("Payment ID", "Plot No", "Name"));
        searchByComboBox.getSelectionModel().selectFirst();
        // Show filtered results while typing
        searchByTextField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText == null || newText.isEmpty()) {
                autoCompletePopup.getSuggestions().setAll(allSuggestions);
                autoCompletePopup.hide();
            } else {
                filteredResults(newText);
            }
        });
        autoCompletePopup.setSelectionHandler(event -> searchByTextField.setText(event.getObject()));
        treeTableView.setVisible(false);
        treeTableView.setManaged(false);
    }

    private void filteredResults(String newText) {
        List<String> filtered = autoCompletePopup.getSuggestions().stream()
                .filter(item -> item.toLowerCase().contains(newText.toLowerCase()))
                .collect(Collectors.toList());
        if (!filtered.isEmpty()) {
            autoCompletePopup.getSuggestions().setAll(filtered);
            autoCompletePopup.show(searchByTextField);
        } else {
            autoCompletePopup.hide();
        }
    }

    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }
    public void ComboBoxSelection(ActionEvent event) throws SQLException {
        String selected = searchByComboBox.getValue();
        searchByText.setText(selected + ":");
        searchByTextField.clear();
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        autoCompletePopup.getSuggestions().clear();
        allSuggestions.clear();
        String query = switch (selected) {
            case "Payment ID" -> "SELECT DISTINCT paymentID FROM ReceiptView";
            case "Plot No" -> "SELECT DISTINCT plotNo FROM ReceiptView";
            case "Name" -> "SELECT DISTINCT ownerName FROM ReceiptView";
            default -> "";
        };
        if (!query.isEmpty()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String value = switch (selected) {
                    case "Payment ID" -> String.valueOf(resultSet.getInt("paymentID"));
                    case "Plot No" -> String.valueOf(resultSet.getInt("plotNo"));
                    case "Name" -> resultSet.getString("ownerName");
                    default -> "";
                };
                allSuggestions.add(value);
            }
            autoCompletePopup.getSuggestions().setAll(allSuggestions);
        }

        // ✅ Show popup manually if user already typed something
        String currentText = searchByTextField.getText();
        if (currentText != null && !currentText.isEmpty()) {
            filteredResults(currentText);
        }
    }


    public void search(ActionEvent event) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = switch(searchByText.getText()){
            case "Payment ID:" -> "SELECT * FROM ReceiptView WHERE paymentID = " + Integer.parseInt(searchByTextField.getText());
            case "Plot No:" -> "SELECT * FROM ReceiptView WHERE plotNo = " + Integer.parseInt(searchByTextField.getText());
            case "Name:" -> "SELECT * FROM ReceiptView WHERE ownerName = \"" + searchByTextField.getText() + "\"";
            default -> "";
        };
        initializeTreeTable(statement, query);
        treeTableView.setVisible(true);
        treeTableView.setManaged(true);
    }

    private void initializeTreeTable(Statement statement, String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        ObservableList<ReceiptTable> observableList = FXCollections.observableArrayList();
        while(resultSet.next()){
            observableList.add(new ReceiptTable(resultSet.getInt("paymentID"),
                    resultSet.getInt("plotNo"), resultSet.getString("ownerName"),
                    resultSet.getString("ownerCNIC"), resultSet.getString("paymentType"),
                    resultSet.getInt("installmentNo"), resultSet.getInt("amount")));
        }
        paymentIDColumn.setCellValueFactory(param -> param.getValue().getValue().paymentIDProperty().asObject());
        plotNoColumn.setCellValueFactory(param -> param.getValue().getValue().plotNoProperty().asObject());
        amountColumn.setCellValueFactory(param -> param.getValue().getValue().amountProperty().asObject());
        ownerNameColumn.setCellValueFactory(param -> param.getValue().getValue().ownerNameProperty());
        ownerCNICColumn.setCellValueFactory(param -> param.getValue().getValue().CNICProperty());
        paymentTypeColumn.setCellValueFactory(param -> param.getValue().getValue().paymentTypeProperty());
        installmentNoColumn.setCellValueFactory(param -> param.getValue().getValue().installmentNoProperty().asObject());
        TreeItem<ReceiptTable> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);
    }
}

class ReceiptTable extends RecursiveTreeObject<ReceiptTable> {
    private final IntegerProperty paymentID;
    private final IntegerProperty plotNo;
    private final StringProperty ownerName;
    private final StringProperty CNIC;
    private final StringProperty paymentType;
    private final IntegerProperty installmentNo;
    private final IntegerProperty amount;

    ReceiptTable(Integer paymentID, Integer plotNo, String ownerName, String CNIC, String paymentType, Integer installmentNo, Integer amount) {
        this.paymentID = new SimpleIntegerProperty(paymentID);
        this.plotNo = new SimpleIntegerProperty(plotNo);
        this.ownerName = new SimpleStringProperty(ownerName);
        this.CNIC = new SimpleStringProperty(CNIC);
        this.paymentType = new SimpleStringProperty(paymentType);
        this.installmentNo = new SimpleIntegerProperty(installmentNo);
        this.amount = new SimpleIntegerProperty(amount);
    }
    public IntegerProperty paymentIDProperty() {return paymentID;}
    public IntegerProperty plotNoProperty() {return plotNo;}
    public StringProperty ownerNameProperty() {return ownerName;}
    public StringProperty CNICProperty() {return CNIC;}
    public StringProperty paymentTypeProperty() {return paymentType;}
    public IntegerProperty installmentNoProperty() {return installmentNo;}
    public IntegerProperty amountProperty() {return amount;}
}
