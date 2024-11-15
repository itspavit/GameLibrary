package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingController {
    @FXML
    private Button backButton;

    @FXML
    private void toHomePage() {
        Utility.loadPage("homescreen.fxml", (Stage) backButton.getScene().getWindow());
    }
}
