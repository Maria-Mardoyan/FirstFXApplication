package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddPostController {

    @FXML
    private VBox addPost;
    @FXML
    private TextArea postTextArea;

    @FXML
    protected void onAddPostClick(ActionEvent event) throws IOException {
        String post = postTextArea.getText();
        boolean isAdded = UserService.addPostToUser(UserService.getCurrentUserName(), post);
        writeAllUsers();
        VBox loggedInMenu = FXMLLoader.load(getClass().getResource("loggedInMenu.fxml"));
        addPost.getChildren().setAll(loggedInMenu);
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
