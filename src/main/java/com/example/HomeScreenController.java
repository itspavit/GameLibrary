package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeScreenController {
    @FXML
    private Button gameLibraryButton;

    @FXML
    private Button profileButton;
    @FXML
    private Button friendsButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private void toGameLibrary() {
        Utility.loadPage("gameLibrary.fxml", (Stage) gameLibraryButton.getScene().getWindow());
    }
    @FXML
    //logout button is a placeholder in homescreen to test navigation.
    private void toLoginPage() {
        Utility.loadPage("/com/example/Authentication/login.fxml", (Stage) logoutButton.getScene().getWindow());
    }
    @FXML
    private void toFriendsPage() {
        Utility.loadPage("friends.fxml", (Stage) friendsButton.getScene().getWindow());
    }
    @FXML
    private void toProfilePage() {
        Utility.loadPage("profilepage.fxml", (Stage) profileButton.getScene().getWindow());
    }
    @FXML
    private void toSettingsPage() {
        Utility.loadPage("settings.fxml", (Stage) settingsButton.getScene().getWindow());
    }


}
