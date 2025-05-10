
package com.example.smartpropertymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginPage {

    @FXML
    private Text invalidPasswordText;
//    Set this to true if password is correct use inavalidPasswordText.setVisible(true)


    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passWordField;

    @FXML
    private TextField userNameField;

    public void loginButtonClick(ActionEvent event){
        String username = userNameField.getText();
        String password = passWordField.getText();
        if (username.equals("admin") && password.equals("admin")) {
            try {
                Main.showDashboard();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            invalidPasswordText.setVisible(true);
        }
    }


}

