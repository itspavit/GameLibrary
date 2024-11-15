package com.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameLauncher {

    public void openGame(String gameName, Stage stage) {
        String fxmlFile;

        switch (gameName) {
            case "Chess":
                fxmlFile = "/com/example/Games/chess.fxml";
                break;
            case "Checkers":
                fxmlFile = "/com/example/Games/checkers.fxml";
                break;
            case "TicTacToe":
                fxmlFile = "/com/example/Games/tictactoe.fxml";
                break;
            default:
                fxmlFile = "/com/example/Games/gamePlaceholder.fxml";
                break;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle(gameName);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading game screen for " + gameName);
        }
    }
}
