package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.TextField;
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
    public void toSignUpPage(MouseEvent event) throws IOException { // when the user clicks "signup"
        // Create a new FXMLLoader instance and load the signup FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Loading the scene
        // Get the stage from the event
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Signup");
        stage.setScene(scene);
        stage.show();
    }

}

