package com.example.ssp.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HelperMethods {

    // Titles
    public static final String gameRockPaperScissorTitle = "Rock, paper, scissors";
    public static final String helpWindowTitle = "Help";
    public static final String recentGamesTitle = "Recent games";
    public static final String signUpTitle = "Sign Up";
    public static final String loginTitle = "Login";
    public static final String addFriendsTitle = "Add friend";
    public static final String friendsListTitle = "Friends";


    //FXML Paths
    public static final String gameRockPaperScissorFXML = "fxml/gameRockPaperScissorWindow.fxml";
    public static final String helpWindowFXML = "fxml/helpWindow.fxml";
    public static final String recentGamesFXML = "fxml/recentGamesWindow.fxml";
    public static final String signUpWindowFXML = "fxml/signUpWindow.fxml";
    public static final String versusPlayCpuWindowFXML = "fxml/versusPlayCpuWindow.fxml";
    public static final String splashScreenFXML = "fxml/splashScreen.fxml";
    public static final String loginFXML = "fxml/logInWindow.fxml";
    public static final String mainWindowFXML = "fxml/mainWindow.fxml";
    public static final String addFriendsFXML = "fxml/addFriendsWindow.fxml";
    public static final String friendsListFXML = "fxml/friendsListWindow.fxml";


    public static URL getRes(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResource(fileName);
    }

    public static InputStream getResAsStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    public static FXMLLoader getLoader(String fxmlPath) {
        return new FXMLLoader(getRes(fxmlPath));
    }

    static void replaceScene(String fxmlPath, String windowTitle, MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) ((Node) mouseEvent.getSource())
                .getScene()
                .getWindow();

        FXMLLoader loader = getLoader(fxmlPath);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle(windowTitle);
        stage.setScene(scene);
        stage.toFront();
        stage.show();


    }


}
