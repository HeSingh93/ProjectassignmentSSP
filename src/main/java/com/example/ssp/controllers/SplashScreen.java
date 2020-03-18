package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SplashScreen extends HelperMethods {

    public void splashButtonClicked(MouseEvent mouseEvent) throws IOException {
        startUp();
        HelperMethods.replaceScene(
                HelperMethods.loginFXML,
                mouseEvent
        );
    }
}
