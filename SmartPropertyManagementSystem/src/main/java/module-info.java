module com.example.smartpropertymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.sql;
    requires com.jfoenix;
    requires mysql.connector.j;
    requires com.google.gson;

    opens com.example.smartpropertymanagementsystem to javafx.fxml;
    exports com.example.smartpropertymanagementsystem;
}