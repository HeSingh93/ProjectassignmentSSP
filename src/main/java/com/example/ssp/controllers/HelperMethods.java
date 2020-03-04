package com.example.ssp.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HelperMethods {

    // Titles
    public static final String splashScreenTitle = "Rock, paper, scissors";
    public static final String loginTitle = "Login";
    public static final String signUpTitle = "Sign Up";
    public static final String mainWindowTitle = "Rock, paper, scissors";

    //FXML Paths
    public static final String splashScreenFXML = "fxml/splashScreen.fxml";
    public static final String loginFXML = "fxml/logInWindow.fxml";
    public static final String mainWindowFXML = "fxml/mainWindow.fxml";

    public static URL getRes(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResource(fileName);
    }

    public static InputStream getResAsStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    public static FXMLLoader getLoader(String fxmlPath) {
        return new FXMLLoader(getRes(fxmlPath));
    }

    static void replaceScene(String fxmlPath, String windowTitle) throws IOException {

        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        FXMLLoader loader = getLoader(fxmlPath);
        Parent root = loader.load();
        Scene scene = new Scene(root);


    }


}
