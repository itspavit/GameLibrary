package com.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Utility {

    public static Image loadImage(String imagePath) {
        URL resourceUrl = Utility.class.getResource(imagePath);
        if (resourceUrl != null) {
            return new Image(resourceUrl.toExternalForm());
        } else {
            System.err.println("Image not found at: " + imagePath);
            URL fallbackUrl = Utility.class.getResource("/images/placeholder.png"); // Placeholder image
            if (fallbackUrl != null) {
                return new Image(fallbackUrl.toExternalForm());
            } else {
                System.err.println("Placeholder image not found at: /images/placeholder.png");
                return null;
            }
        }
    }

    public static void loadPage(String fxmlFileName, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Utility.class.getResource(fxmlFileName));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading " + fxmlFileName);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Page Load Error");
            alert.setContentText("Could not load page: " + fxmlFileName);
            alert.showAndWait();
        }
    }
}
