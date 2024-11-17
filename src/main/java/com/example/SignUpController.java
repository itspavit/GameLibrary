package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class SignUpController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ToggleButton showToggleButton1;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField confirmPasswordTextField;
    @FXML
    private ToggleButton showToggleButton2;
    @FXML
    private Button signUpButton;
    @FXML
    private Label loginLabel;


    @FXML
    public void handleToggleButtonClick(MouseEvent event) {
        // Determine which toggle button was clicked and call the helper method
        if (event.getSource() == showToggleButton1) {
            togglePasswordVisibility(passwordField, passwordTextField, showToggleButton1);
        } else if (event.getSource() == showToggleButton2) {
            togglePasswordVisibility(confirmPasswordField, confirmPasswordTextField, showToggleButton2);
        }
    }

    // Helper method for toggle logic
    private void togglePasswordVisibility(PasswordField passwordField, TextField passwordTextField, ToggleButton toggleButton) {
        if (toggleButton.isSelected()){

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
    private void toHomePage(){
        Utility.loadPage("homescreen.fxml", (Stage) signUpButton.getScene().getWindow());
    }

    @FXML
    private void toLoginPage() {
        Utility.loadPage("/com/example/Authentication/login.fxml", (Stage) loginLabel.getScene().getWindow());
    }
}

