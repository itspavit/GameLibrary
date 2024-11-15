package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLibraryController {

    @FXML
    private GridPane allGamesGrid;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView profileIcon;

    private final Map<String, String> gameImages = new HashMap<>() {{
        put("Chess", "/images/chess.png");
        put("Checkers", "/images/checkers.png");
        put("TicTacToe", "/images/tictactoe.png");
        put("Game 1", "/images/placeholder.png");
    }};

    private final List<String> featuredGames = List.of("Chess", "Checkers", "TicTacToe");

    private final List<String> allGames = List.of(
            "Game 1", "Game 2", "Game 3", "Game 4", "Game 5",
            "Game 6", "Game 7", "Game 8", "Game 9", "Game 10",
            "Game 11", "Game 12", "Game 13", "Game 14", "Game 15",
            "Game 16", "Game 17", "Game 18", "Game 19", "Game 20",
            "Game 21", "Game 22", "Game 23", "Game 24", "Game 25",
            "Game 26", "Game 27", "Game 28", "Game 29", "Game 30"
    );

    public void initialize() {
        showAllGamesPage(0);
        profileIcon.setImage(loadImage("/images/profile.png"));
        profileIcon.setOnMouseClicked(event -> navigateToProfilePage());
        searchField.setOnAction(event -> handleSearch());
    }

    private Image loadImage(String imagePath) {
        URL resourceUrl = getClass().getResource(imagePath);
        if (resourceUrl != null) {
            return new Image(resourceUrl.toExternalForm());
        } else {
            System.err.println("Image not found at: " + imagePath);
            URL fallbackUrl = getClass().getResource("/images/placeholder.png"); //placeholder image
            if (fallbackUrl != null) {
                return new Image(fallbackUrl.toExternalForm());
            } else {
                System.err.println("Placeholder image not found at: /images/placeholder.png");
                return null;
            }
        }
    }
    private void loadPage(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();

            Stage stage = (Stage) searchField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading " + fxmlFileName);
        }
    }
    @FXML
    private void navigateToProfilePage() {
        loadPage("profilepage.fxml");
    }
    @FXML
    private void navigateToNotificationPage() {
        loadPage("notificationpage.fxml");
    }
    @FXML
    private void handleBack() {
        loadPage("homescreen.fxml");
    }

    private void showGameDetails(String gameName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameDetails.fxml"));
            Parent root = loader.load();

            GameDetailsController gameDetailsController = loader.getController();
            Image gameImage = loadImage(gameImages.getOrDefault(gameName, "/images/placeholder.png"));
            gameDetailsController.setGameInfo(gameName, "Description for " + gameName + ".", gameImage);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle(gameName);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showGameNotFoundPopup(String searchTerm) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Not Found");
        alert.setHeaderText(null);
        alert.setContentText("No game found with the name \"" + searchTerm + "\".");
        alert.showAndWait();
    }

    @FXML
    private void handleGameClick(MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();

        VBox parentBox = (VBox) clickedImageView.getParent();
        Label gameLabel = (Label) parentBox.getChildren().get(1);
        String gameName = gameLabel.getText();

        showGameDetails(gameName);
    }
    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText().trim().toLowerCase();
        boolean gameFound = false;

        for (String game : featuredGames) {
            if (game.toLowerCase().equals(searchTerm)) {
                showGameDetails(game);
                gameFound = true;
                break;
            }
        }
        if (!gameFound) {
            for (String game : allGames) {
                if (game.toLowerCase().equals(searchTerm)) {
                    showGameDetails(game);
                    gameFound = true;
                    break;
                }
            }
        }
        if (!gameFound) {
            showGameNotFoundPopup(searchTerm);
        }
    }

    private int allGamesPageIndex = 0;
    private final int gamesPerPage = 10;
    @FXML
    private void showPreviousAllGamesPage() {
        if (allGamesPageIndex > 0) {
            allGamesPageIndex--;
            showAllGamesPage(allGamesPageIndex);
        }
    }
    @FXML
    private void showNextAllGamesPage() {
        int maxPage = (allGames.size() - 1) / gamesPerPage;
        if (allGamesPageIndex < maxPage) {
            allGamesPageIndex++;
            showAllGamesPage(allGamesPageIndex);
        }
    }
    private void showAllGamesPage(int pageIndex) {
        // Clear current grid content
        allGamesGrid.getChildren().clear();

        // find start and end index for the current page
        int startIndex = pageIndex * gamesPerPage;
        int endIndex = Math.min(startIndex + gamesPerPage, allGames.size());

        // set the grid with games for the current page
        int row = 0, col = 0;
        for (int i = startIndex; i < endIndex; i++) {
            String gameName = allGames.get(i);
            VBox gameBox = createGameBox(gameName, 100, 80);
            allGamesGrid.add(gameBox, col, row);

            col++;
            if (col >= 5) {
                col = 0;
                row++;
            }
        }
    }

    private VBox createGameBox(String gameName, int width, int height) {
        VBox gameBox = new VBox(8);
        gameBox.setAlignment(Pos.CENTER);

        ImageView gameImageView = new ImageView(loadImage(gameImages.getOrDefault(gameName, "/images/placeholder.png")));
        gameImageView.setFitWidth(width);
        gameImageView.setFitHeight(height);
        gameImageView.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0.5, 1, 1); " +
                "-fx-background-radius: 10; " +
                "-fx-padding: 5;");
        gameImageView.setOnMouseClicked(e -> showGameDetails(gameName));

        Label gameTitle = new Label(gameName);
        gameTitle.setStyle("-fx-font-weight: normal; -fx-font-size: 14;");

        gameBox.getChildren().addAll(gameImageView, gameTitle);
        return gameBox;
    }
}

