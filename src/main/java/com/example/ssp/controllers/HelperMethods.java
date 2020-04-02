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

/**
 * This class is used to replace the current scene in the program.
 */

public class HelperMethods {

    // Title
    public static final String gameRockPaperScissorTitle = "Rock, paper, scissors";

    //FXML Paths
    public static final String gameRockPaperScissorFXML = "fxml/gameRockPaperScissorWindow.fxml";
    public static final String helpWindowFXML = "fxml/helpWindow.fxml";
    public static final String recentGamesFXML = "fxml/recentGamesWindow.fxml";
    public static final String signUpWindowFXML = "fxml/signUpWindow.fxml";
    public static final String versusPlayCpuWindowFXML = "fxml/versusPlayCpuWindow.fxml";
    public static final String loginFXML = "fxml/logInWindow.fxml";
    public static final String mainWindowFXML = "fxml/mainWindow.fxml";
    public static final String addFriendsFXML = "fxml/addFriendsWindow.fxml";
    public static final String friendsListFXML = "fxml/friendsListWindow.fxml";
    public static final String playRandomFXML = "fxml/playRandomWindow.fxml";
    public static final String playCpuFXML = "fxml/playCpu.fxml";
    public static final String playFriendsListVersusWindow = "fxml/friendsListVersusWindow.fxml";
    public static final String randomChoiceFXML = "fxml/randomChoiceWindow.fxml";
    public static final String paperViewFXML = "fxml/paperView.fxml";
    public static final String scissorViewFXML = "fxml/scissorView.fxml";
    public static final String gameRockPaperScissorVersusFXML = "fxml/gameRockPaperScissorVersus.fxml";
    public static final String versusResultWindowFXML = "fxml/versusResultWindow.fxml";
    public static final String loadScreenWindowFXML = "fxml/loadScreenWindow.fxml";
    public static final String playCpuResultFXML = "fxml/playCpuResult.fxml";

    /**
     * Used to load FXML files.
     * @param fileName the name of the file that we want to load.
     * @return the given image.
     */
    public static URL getRes(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResource(fileName);
    }
    /**
     * Used to load images from our resource folder.
     * @param fileName the name of the file that we want to load.
     * @return the given image.
     */
    public static InputStream getResAsStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    /**
     *
     * @param fxmlPath Path to the FXML that we want to load.
     * @return The FXMLLoader.
     */
    public static FXMLLoader getLoader(String fxmlPath) {
        return new FXMLLoader(getRes(fxmlPath));
    }

    /**
     *
     * @param fxmlPath Path to the FXML we want to load.
     * @param mouseEvent
     * @throws IOException
     */
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

    /**
     * Modified replaceScene, but does also pass token to the next scene.
     * @param fxmlPath Path to the FXML we want to load.
     * @param mouseEvent
     * @param token The object that we want to pass.
     * @throws IOException
     */
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

    /**
     * Also a modified replaceScene, but with a token and choice object to pass to the next scene.
     * @param fxmlPath Path to the FXML we want to load.
     * @param mouseEvent
     * @param token The object that we want to pass.
     * @param choice The object that we want to pass.
     * @throws IOException
     */
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
