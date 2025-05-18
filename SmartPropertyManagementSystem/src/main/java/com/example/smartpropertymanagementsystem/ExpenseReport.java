package com.example.smartpropertymanagementsystem;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ExpenseReport implements sceneToDashboard, Initializable {
    @FXML TreeTableView<IncomeTable> treeTableView;
    @FXML TreeTableColumn<IncomeTable, Integer> expenseIDColumn;
    @FXML TreeTableColumn<IncomeTable, String> headColumn;
    @FXML TreeTableColumn<IncomeTable, Integer> amountColumn;
    @FXML TreeTableColumn<IncomeTable, String> dateColumn;
    @FXML DatePicker fromDatePicker;
    @FXML DatePicker toDatePicker;
    private static final String url =  "jdbc:mysql://smartpropertymanagementsystem.cdsoew0qk2pc.ap-south-1.rds.amazonaws.com:3306/spms";
    private static final String username = "admin";
    private static final String password = "d375c123";
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }
    public void initialize(URL location, ResourceBundle resources){
        try{
            Connection connection =  DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            String query = "Select * FROM ExpenseReportView";
            InitializeTreeTable(statement, query);
        } catch (SQLException e){
            e.printStackTrace();
        }
        treeTableView.setVisible(false);
        treeTableView.setManaged(false);
    }
    public void search(ActionEvent event) throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM ExpenseReportView WHERE date BETWEEN STR_TO_DATE('" + fromDatePicker.getValue() + "', '%Y-%m-%d') AND STR_TO_DATE('" + toDatePicker.getValue() + "', '%Y-%m-%d')";
        System.out.println(query);
        try{
            InitializeTreeTable(statement, query);
        } catch (SQLException e){
            e.printStackTrace();
        }
        treeTableView.setVisible(true);
        treeTableView.setManaged(true);
    }

    private void InitializeTreeTable(Statement statement, String query) throws SQLException {
        ResultSet resultset = statement.executeQuery(query);
        ObservableList<IncomeTable> observableList = FXCollections.observableArrayList();
        while(resultset.next())
            observableList.add(new IncomeTable(resultset.getInt("expenseID"),
            resultset.getString("head"),resultset.getInt("amount"),
            resultset.getString("date")));
        expenseIDColumn.setCellValueFactory(param -> param.getValue().getValue().incomeIDProperty().asObject());
        headColumn.setCellValueFactory(param -> param.getValue().getValue().headProperty());
        amountColumn.setCellValueFactory(param -> param.getValue().getValue().amountProperty().asObject());
        dateColumn.setCellValueFactory(param -> param.getValue().getValue().dateProperty());
        TreeItem<IncomeTable> root = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);
    }
}

class ExpenseTable extends RecursiveTreeObject<IncomeTable> {
    private final IntegerProperty expenseID;
    private final StringProperty head;
    private final IntegerProperty amount;
    private final StringProperty date;
    ExpenseTable(Integer expenseID, String head, Integer amount, String date) {
        this.expenseID = new SimpleIntegerProperty(expenseID);
        this.head = new SimpleStringProperty(head);
        this.amount = new SimpleIntegerProperty(amount);
        this.date = new SimpleStringProperty(date);
    }
    public IntegerProperty expenseIDProperty() {return expenseID;}
    public StringProperty headProperty() {return head;}
    public IntegerProperty amountProperty() {return amount;}
    public StringProperty dateProperty() {return date;}
}
