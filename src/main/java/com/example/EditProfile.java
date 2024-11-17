package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EditProfile {

    @FXML
    private Button backButton;


    @FXML
    private void toProfilePage() {
        Utility.loadPage("profilepage.fxml",
                (Stage) backButton.getScene().getWindow());
    }

}
