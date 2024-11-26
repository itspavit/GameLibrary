package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Button editProfileButton;
    @FXML
    private Label friendCountLabel;
    @FXML
    private ImageView profileAvatarImageView;
    @FXML
    private ListView<String> gameHistoryListView;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem logoutMenuItem;

    private final String[] gameHistoryData = {
            "Checkers: You Won!",
            "Tic Tac Toe: You Lost!",
            "Chess: It Was A Tie!"
    };

    @FXML
    private void toHomePage() {
        Utility.loadPage("homescreen.fxml", (Stage) backButton.getScene().getWindow());
    }

    @FXML
    private void toEditProfilePage() {
        Utility.loadPage("/com/example/Authentication/editprofile.fxml", (Stage) editProfileButton.getScene().getWindow());
    }

    @FXML
    private void toLoginPage(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure you want to log out?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Authentication/login.fxml"));
                    Parent root = loader.load();
                    // Get the current stage from the event
                    Stage stage = (Stage) menuBar.getScene().getWindow();
                    Scene loginScene = new Scene(root);
                    stage.setScene(loginScene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameHistoryListView.getItems().addAll(gameHistoryData);


    }
}

