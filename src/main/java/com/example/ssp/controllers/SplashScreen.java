package com.example.ssp.controllers;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class SplashScreen {

    public void splashScreenClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.loginFXML,
                HelperMethods.loginTitle
        );
    }
}
