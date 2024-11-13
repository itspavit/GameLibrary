package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField; //linked to corresponding components

    public void userSignIn() { // called when user clicks "sign in"
        //stuff will happen

    }
    public void userSignUp(MouseEvent event) throws IOException { // when the user clicks "signup"
        //loading signup fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // loading the scene


    }
}