package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SplashScreen {

    public void splashButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.loginFXML,
                HelperMethods.loginTitle,
                mouseEvent
        );
    }
}
