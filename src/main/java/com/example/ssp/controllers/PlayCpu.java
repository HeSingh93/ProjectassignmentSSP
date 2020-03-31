package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PlayCpu extends GenericController {
    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent,
                token
        );
    }

    public void playerBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.gameRockPaperScissorFXML,
                mouseEvent,
                token
        );
    }
}
