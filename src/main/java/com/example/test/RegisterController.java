package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {

    public Label errorMessage;
    @FXML
    private VBox registerScreen;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField registerUsernameField;
    @FXML
    private TextField registerPasswordField;


    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        String username = registerUsernameField.getText();
        String password = registerPasswordField.getText();
        User user = new User(username, password);
        boolean isAdded = UserService.addUser(user);
        UserService.setCurrentUserName(username);
//        writeAllUsers();
        VBox loggedInMenu = FXMLLoader.load(getClass().getResource("loggedInMenu.fxml"));
        registerScreen.getChildren().setAll(loggedInMenu);
    }

    @FXML
    protected void onLoginHyperlinkClick(ActionEvent event) throws IOException {
        VBox loginScreen = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        registerScreen.getChildren().setAll(loginScreen);
    }

    private static void writeAllUsers() {
        List<String> userStrings = new ArrayList<>();
        for (User user : UserService.getAllUsers()) {
            StringBuilder userPost = new StringBuilder();
            for (String userPosts : user.getPosts()) {
                userPost.append(userPosts);
                userPost.append(" ");
            }

            String userString = user.getUsername() +
                    " " +
                    user.getPassword() +
                    " " +
                    userPost;
            userStrings.add(userString);
        }
        FileService.writeToFile(userStrings);
    }
}

