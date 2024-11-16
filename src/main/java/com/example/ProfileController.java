package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ProfileController {
    @FXML
    private Button backButton;


    @FXML
    public void initialize (){

    }



    @FXML
    private void toHomePage() {
        Utility.loadPage("homescreen.fxml", (Stage) backButton.getScene().getWindow());
    }
}
