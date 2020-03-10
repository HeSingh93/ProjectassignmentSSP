package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LogInWindow {

    public void logInBtn(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.mainWindowFXML,
                mouseEvent
        );

    }

    public void signUpBtn(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.signUpWindowFXML,
                mouseEvent
        );
    }
}




