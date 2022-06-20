package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoggedInMenuController {

    @FXML
    private VBox loggedInMenu;

    @FXML
    protected void onAddPostClick(ActionEvent event) throws IOException {
        VBox addPost = FXMLLoader.load(getClass().getResource("addPost.fxml"));
        loggedInMenu.getChildren().setAll(addPost);
    }

    @FXML
    protected void onSeePostsClick(ActionEvent event) throws IOException {
        VBox seePosts = FXMLLoader.load(getClass().getResource("seeAllPosts.fxml"));
        loggedInMenu.getChildren().setAll(seePosts);
    }

    @FXML
    protected void onLogoutClick(ActionEvent event) throws IOException {
        UserService.setCurrentUserName("");
        VBox registerScreen = FXMLLoader.load(getClass().getResource("RegisterScreen.fxml"));
        loggedInMenu.getChildren().setAll(registerScreen);
    }

}
