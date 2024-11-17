package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class GameLauncher {


    public TextArea TTTGameBox;
    public Button TTTMoveLeft;
    public Button TTTMoveUp;
    public Button TTTMoveRight;
    public Button TTTMoveDown;
    public Button TTTPlacePiece;
    public Button TTTMessageSent;
    public TextArea CHGameBox;
    public Button CHMoveLeft;
    public Button CHMoveUp;
    public Button CHMoveRight;
    public Button CHMoveDown;
    public Button CHPlacePiece;
    public Button CHMessageSent;
    public Button CMoveLeft;
    public Button CMoveUp;
    public Button CMoveRight;
    public Button CMoveDown;
    public Button CPlacePiece;
    public Button CMessageSent;

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
