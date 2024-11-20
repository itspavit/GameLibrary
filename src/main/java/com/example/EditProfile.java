package com.example;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditProfile implements Initializable {

    @FXML
    private Button viewProfileButton;

    @FXML
    private Label logoutLabel;

    @FXML
    private ComboBox<String> displayStatusComboBox;


    @FXML
    private void toProfilePage() {
        Utility.loadPage("profilepage.fxml",
                (Stage) viewProfileButton.getScene().getWindow());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayStatusComboBox.setItems(FXCollections.observableArrayList("Public", "Private"));
    }
}
