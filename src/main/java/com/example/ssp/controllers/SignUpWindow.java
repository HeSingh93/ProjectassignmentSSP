package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SignUpWindow {
    public void createAccountClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.loginFXML,
                mouseEvent
        );
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.loginFXML,
                mouseEvent
        );
        //SQL-kommando
    }
}
