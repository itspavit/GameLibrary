package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RecoverAccountController {


    @FXML
    private Button backButton;

    @FXML
    private Button signUpButton;

    @FXML
    private void toLoginPage(ActionEvent event) {
        Utility.loadPage("/com/example/Authentication/login.fxml",
                (Stage) backButton.getScene().getWindow());
    }

    @FXML
    private void toSignUpPage(ActionEvent event) {
        Utility.loadPage("/com/example/Authentication/signup.fxml",
                (Stage) signUpButton.getScene().getWindow());
    }


}
