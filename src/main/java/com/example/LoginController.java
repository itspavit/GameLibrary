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
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ToggleButton showToggleButton;
    @FXML
    private Button signInButton;
    @FXML
    private Label signUpLabel;
    @FXML
    private Label forgotPasswordLabel;



    @FXML
    public void toRecoverAccountPage(){
        Utility.loadPage("/com/example/Authentication/recoveraccount.fxml",
                (Stage) forgotPasswordLabel.getScene().getWindow());

    }

    @FXML
    public void handleToggleButtonClick(MouseEvent event){
        if(showToggleButton.isSelected()){

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
    private void toHomePage() {

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        if (username.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Both username and password fields must be filled out!");
            alert.showAndWait();

        }else{
            Utility.loadPage("homescreen.fxml",
                    (Stage) signInButton.getScene().getWindow());

        }
    }

    @FXML
    private void toSignUpPage() {
        Utility.loadPage("/com/example/Authentication/signup.fxml",
                (Stage) signUpLabel.getScene().getWindow());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        passwordTextField.setVisible(false); // Start with the hidden password
        usernameField.setFocusTraversable(false);

    }
}


