package com.example.test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application extends javafx.application.Application {
    public static String currentUserName;
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("RegisterScreen.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setTitle("Login or Sign-Up Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        readAllUsers();

    }

    private static void readAllUsers() {
        ArrayList<String> userStrings = FileService.readFromFile();
        for (String userString : userStrings) {
            User user = new User();
            List<String> split = Arrays.asList(userString.trim().split(" "));
            user.setUsername(split.get(0));
            user.setPassword(split.get(1));

            split.remove(0);
            split.remove(0);

            user.setPosts(split);
            UserService.addUser(user);
        }
    }

}