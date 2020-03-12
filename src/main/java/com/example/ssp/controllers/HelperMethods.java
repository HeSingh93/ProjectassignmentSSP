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

    // Title
    public static final String gameRockPaperScissorTitle = "Rock, paper, scissors";

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
    public static final String playRandomFXML = "fxml/playRandomWindow.fxml";
    public static final String playCpuFXML = "fxml/playCpu.fxml";
    public static final String winnerWindowFXML = "fxml/winnerWindow.fxml";
    public static final String playFriendsListVersusWindow = "fxml/friendsListVersusWindow.fxml";
    public static final String rockViewFXML = "fxml/rockView";
    public static final String paperViewFXML = "fxml/paperView";
    public static final String scissorViewFXML = "fxml/scissorView";

    public static URL getRes(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResource(fileName);
    }

    public static InputStream getResAsStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    public static FXMLLoader getLoader(String fxmlPath) {
        return new FXMLLoader(getRes(fxmlPath));
    }

    static void replaceScene(String fxmlPath, MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) ((Node) mouseEvent.getSource())
                .getScene()
                .getWindow();

        FXMLLoader loader = getLoader(fxmlPath);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle(HelperMethods.gameRockPaperScissorTitle);
        stage.setScene(scene);
        stage.toFront();
        stage.show();
    }
}
