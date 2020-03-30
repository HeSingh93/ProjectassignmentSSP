package com.example.ssp.controllers;


import com.example.ssp.models.Choice;
import com.example.ssp.models.Token;
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
    public static final String rockViewFXML = "fxml/rockView.fxml";
    public static final String paperViewFXML = "fxml/paperView.fxml";
    public static final String scissorViewFXML = "fxml/scissorView.fxml";
    public static final String loserPaperWindowFXML = "fxml/loserPaperWindow.fxml";
    public static final String loserScissorWindowFXML = "fxml/loserScissorWindow.fxml";
    public static final String loserRockWindowFXML = "fxml/loserRockWindow.fxml";
    public static final String winnerPaperWindowFXML = "fxml/winnerPaperWIndow.fxml";
    public static final String winnerScissorWindowFXML = "fxml/winnerScissorWindow.fxml";
    public static final String winnerRockWindowFXML = "fxml/winnerRockWindow.fxml";
    public static final String tieWindowFXML = "fxml/tieWindow.fxml";
    public static final String gameRockPaperScissorVersusFXML = "fxml/gameRockPaperScissorVersus.fxml";
    public static final String versusResultWindowFXML = "fxml/versusResultWindow.fxml";
    public static final String loadScreenWindowFXML = "fxml/loadScreenWindow.fxml";

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

    static void replaceSceneLoggedIn(String fxmlPath, MouseEvent mouseEvent, Token token) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource())
                .getScene()
                .getWindow();

        FXMLLoader loader = getLoader(fxmlPath);
        Parent root = loader.load();
        GenericController controller = loader.getController();
        controller.setToken(token);
        controller.postInitialize();
        Scene scene = new Scene(root);
        stage.setTitle(HelperMethods.gameRockPaperScissorTitle);
        stage.setScene(scene);
        stage.toFront();
        stage.show();
    }

    static void replaceSceneVersusPlayer(String fxmlPath, MouseEvent mouseEvent, Token token, Choice choice) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource())
                .getScene()
                .getWindow();

        FXMLLoader loader = getLoader(fxmlPath);
        Parent root = loader.load();
        GenericController controller = loader.getController();
        controller.setToken(token);
        controller.setChoice(choice);
        controller.postInitialize();
        Scene scene = new Scene(root);
        stage.setTitle(HelperMethods.gameRockPaperScissorTitle);
        stage.setScene(scene);
        stage.toFront();
        stage.show();
    }
}
