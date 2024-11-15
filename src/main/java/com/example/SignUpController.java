package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class SignUpController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField; // maybe

    @FXML
    private Button signUpButton; // Linked to the sign-up Button in FXML


    @FXML
    void toLoginScreen(MouseEvent event) {
        try {
            // Load the FXML for the login screen
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/Authentication/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Create a new scene for the login page

            // Get the current stage from the event
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the current stage (navigate to the login screen)
            currentStage.setTitle("Login");
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            // Handle the IOException
        }
    }
}
