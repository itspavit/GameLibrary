package com.example;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
    private ImageView displayImageView;
    @FXML
    private ImageView changeAvatarImageView;
    @FXML
    private Button changeAvatarButton;
    @FXML
    private Button updateProfileButton;

    /**
     * The method allows user to change their avatar in their edit profile page.
     */

    public void changeAvatarButtonClicked(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",
                "*.png", "*.jpg", "*.jpeg")); // only allowing image files to be used
        File filePath = fileChooser.showOpenDialog(stage);

        // If a file was selected, load it and display in the ImageView
        if (filePath != null) {
            Image newAvatarImage = new Image(filePath.toURI().toString());  // Load image from file
            changeAvatarImageView.setImage(newAvatarImage); //Changing both display avatars
            displayImageView.setImage(newAvatarImage);

        }
    }


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
