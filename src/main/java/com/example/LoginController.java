package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private Button signInButton;
    @FXML
    private Label signUpLabel;

    @FXML
    public void handleToggleButtonClick(ActionEvent event){
        if(toggleButton.isSelected()){
            passwordTextField.setText(passwordField.getText());
            passwordTextField.setVisible(true);
            passwordField.setVisible(false);
        } else {

            passwordField.setText(passwordTextField.getText());
            passwordField.setVisible(true);
            passwordTextField.setVisible(false);
        }
    }


    @FXML
        private void toSignUpPage () {
            Utility.loadPage("/com/example/Authentication/signup.fxml", (Stage) signUpLabel.getScene().getWindow());
        }

        @FXML
        private void toHomePage () {
            Utility.loadPage("homescreen.fxml", (Stage) signInButton.getScene().getWindow());
        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        passwordTextField.setVisible(false); // Start with the hidden password

    }
}


