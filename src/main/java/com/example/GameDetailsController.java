package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameDetailsController {

    @FXML
    private ImageView gameImageView;

    @FXML
    private Label gameTitle;

    @FXML
    private Label gameDescription;

    @FXML
    private Label gameRating;

    public void setGameInfo(String title, String description, Image image) {
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
    private void handlePlayGame() {
        System.out.println("Starting game..."); //redirect to game screen
    }

    @FXML
    private void handleViewLeaderboard() {
        System.out.println("Viewing leaderboard..."); //redirect to leaderboard screen
    }
}

