package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProfileController {
    @FXML
    private Button backButton;
    @FXML
    private Button editProfileButton;

    @FXML
    private void toHomePage() {
        Utility.loadPage("homescreen.fxml", (Stage) backButton.getScene().getWindow());
    }

    @FXML
    private void toEditProfilePage() {
        Utility.loadPage("editprofile.fxml", (Stage) editProfileButton.getScene().getWindow());
    }


}
