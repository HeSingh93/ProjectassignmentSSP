package com.example.ssp.controllers;

import javafx.application.Platform;
import javafx.scene.input.MouseEvent;


import java.io.IOException;

public class MainWindow extends GenericController {


    public void newGameBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent,
                token
        );
    }

    public void recentBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.recentGamesFXML,
                mouseEvent,
                token
        );
    }

    public void helpBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.helpWindowFXML,
                mouseEvent,
                token
        );
    }

    public void friendsBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.friendsListFXML,
                mouseEvent,
                token
        );
    }

    public void exitBtnClicked() {
        Platform.exit();
    }
}

