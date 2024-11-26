package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LeaderboardController {
    @FXML
    private Button backButton;

    @FXML
    private void toGameLibrary() {
        Utility.loadPage("gamedetails.fxml", (Stage) backButton.getScene().getWindow());
    }
}
