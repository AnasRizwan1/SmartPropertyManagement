package com.example.smartpropertymanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Confirmation implements sceneToDashboard, Initializable {
    @FXML TreeTableView<tempClass> treeTableView;
    @FXML TreeTableColumn<tempClass, Integer> plotNo;
    @FXML TreeTableColumn<tempClass, Integer> area;
    @FXML TreeTableColumn<tempClass, Void> action;
    private static final String url = "jdbc:mysql://localhost:3306/temporary";
    private static final String username = "root";
    private static final String password = "GravityFalls1708";
    public void Dashboard(ActionEvent event) throws IOException {
        switchToDashboard(event);
    }

    @Override
    public void initialize(URL urrl, ResourceBundle resourceBundle) {
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String query = "select * from plot";
            ResultSet resultSet = statement.executeQuery(query);
            ObservableList<tempClass> observableList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                observableList.add(new tempClass(resultSet.getInt("plotNo"), resultSet.getInt("area")));
            }
            System.out.println("Rows fetched: "  + observableList.size());
            plotNo.setCellValueFactory(param -> param.getValue().getValue().plotNoProperty().asObject());
            area.setCellValueFactory(param -> param.getValue().getValue().areaProperty().asObject());
            // Add button column
            action.setCellFactory(param -> new javafx.scene.control.TreeTableCell<>() {
                private final javafx.scene.control.Button btn = new javafx.scene.control.Button("Click Me");{
                    btn.setOnAction(event -> {
                        tempClass data = getTreeTableRow().getItem();
                        if (data != null)
                            System.out.println("clicked plotNo: " + data.plotNoProperty().get());
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

            TreeItem<tempClass> root = new RecursiveTreeItem<tempClass>(observableList, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
            treeTableView.setShowRoot(false);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}
class tempClass extends RecursiveTreeObject<tempClass> {
    private final IntegerProperty plotNo;
    private final IntegerProperty area;

    public tempClass(Integer plotNo, Integer area) {
        this.plotNo = new SimpleIntegerProperty(plotNo);
        this.area = new SimpleIntegerProperty(area);
    }

    public IntegerProperty plotNoProperty() {
        return plotNo;
    }

    public IntegerProperty areaProperty() {
        return area;
    }
}
/*@Override
    public void initialize(URL urrl, ResourceBundle resourceBundle) {
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String query = "select * from plot";
            ResultSet resultSet = statement.executeQuery(query);
            ObservableList<tempClass> observableList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                observableList.add(new tempClass(resultSet.getInt("plotNo"), resultSet.getInt("area")));
            }
            System.out.println("Rows fetched: "  + observableList.size());
            plotNo.setCellValueFactory(param -> param.getValue().getValue().plotNoProperty().asObject());
            area.setCellValueFactory(param -> param.getValue().getValue().areaProperty().asObject());
            TreeItem<tempClass> root = new RecursiveTreeItem<tempClass>(observableList, RecursiveTreeObject::getChildren);
            treeTableView.setRoot(root);
            treeTableView.setShowRoot(false);
        }catch (SQLException e){
            e.getMessage();
        }
    }
}

class tempClass extends RecursiveTreeObject<tempClass> {
    private final IntegerProperty plotNo;
    private final IntegerProperty area;

    public tempClass(Integer plotNo, Integer area) {
        this.plotNo = new SimpleIntegerProperty(plotNo);
        this.area = new SimpleIntegerProperty(area);
    }

    public IntegerProperty plotNoProperty() {
        return plotNo;
    }

    public IntegerProperty areaProperty() {
        return area;
    }
}
*/