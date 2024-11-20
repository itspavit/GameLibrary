package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
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
        Utility.loadPage("/com/example/Authentication/editprofile.fxml", (Stage) editProfileButton.getScene().getWindow());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}

