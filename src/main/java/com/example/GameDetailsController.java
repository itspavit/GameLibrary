package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameDetailsController {

    @FXML
    private ImageView gameImageView;

    @FXML
    private Label gameTitle;

    @FXML
    private Label gameDescription;

    @FXML
    private Label gameRating;
    @FXML
    private Button leaderboardButton;
    private String gameName;

    public void setGameInfo(String title, String description, Image image, String gameName) {
        this.gameName = gameName;
        gameTitle.setText(title);
        gameDescription.setText(description);
        gameRating.setText("Rating: 5.0");  //placeholder

        if (image != null) {
            gameImageView.setImage(image);
        } else {
            gameImageView.setImage(new Image(getClass().getResource("/images/placeholder.png").toExternalForm()));
        }
    }

    @FXML
    private void toGameScreen() {
        if (gameName != null) {
            GameLauncher gameLauncher = new GameLauncher();
            gameLauncher.openGame(gameName, (Stage) gameImageView.getScene().getWindow());
        } else {
            System.out.println("Game name is not set. Cannot open game.");
        }
    }

    @FXML
    private void toLeaderboard() {
        Utility.loadPage("leaderboard.fxml", (Stage) leaderboardButton.getScene().getWindow());
    }
}

