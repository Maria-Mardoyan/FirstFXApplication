package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginController {

    @FXML
    private VBox loginScreen;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private TextField loginPasswordField;
    @FXML
    private Label errorMessage;


    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        readAllUsers();
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();
        boolean isValid = UserService.validateUser(username, password);
        if (isValid) {
            UserService.setCurrentUserName(username);
            VBox loggedInMenu = FXMLLoader.load(getClass().getResource("loggedInMenu.fxml"));
            loginScreen.getChildren().setAll(loggedInMenu);
        } else {
            errorMessage.setText("Invalid credentials");
        }

    }


    private static void readAllUsers() {
        ArrayList<String> userStrings = FileService.readFromFile();
        for (String userString : userStrings) {
            User user = new User();
            String[] split = userString.trim().split(" ");
            user.setUsername(split[0]);
            user.setPassword(split[1]);
            ArrayList<String> posts = new ArrayList<>();

            for (int j = 2; j < split.length; j++) {
                posts.add(split[j]);
            }
            user.setPosts(posts);
            UserService.addUser(user);
        }
    }

    @FXML
    protected void onRegisterHyperlinkClick(ActionEvent event) throws IOException {
        VBox registerScreen = FXMLLoader.load(getClass().getResource("RegisterScreen.fxml"));
        loginScreen.getChildren().setAll(registerScreen);
    }
}

