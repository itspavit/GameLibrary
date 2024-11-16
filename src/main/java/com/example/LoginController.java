package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


import java.io.IOException;

public class LoginController {

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
    void toggleButton(ActionEvent event) { //visibility of password
        // Check the state of the toggle button
    }


    @FXML
    void userSignIn(ActionEvent event) { // called when user clicks "sign in"
        // Stuff will happen
    }

    @FXML
    void forgotPassword(MouseEvent event) {
        // Handle forgot password action here
    }

    @FXML
    private void toSignUpPage() {
        Utility.loadPage("/com/example/Authentication/signup.fxml", (Stage) signUpLabel.getScene().getWindow());
    }


    @FXML
    private void toHomePage() {
        Utility.loadPage("homescreen.fxml", (Stage) signInButton.getScene().getWindow());
    }

}


