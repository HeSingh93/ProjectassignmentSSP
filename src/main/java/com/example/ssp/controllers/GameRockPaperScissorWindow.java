package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class GameRockPaperScissorWindow extends GenericController {

    int rock = 1;
    int paper = 2;
    int scissors = 3;

    public void rockClicked(MouseEvent mouseEvent) throws IOException {
        int rand = 1 + (int) (Math.random() * 4);

        if (rand == paper) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.loserPaperWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (rand == scissors) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.winnerRockWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (rand == rock) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.tieWindowFXML,
                    mouseEvent,
                    token
            );
        }
    }

    public void paperClicked(MouseEvent mouseEvent) throws IOException {
        int rand = 1 + (int) (Math.random() * 4);

        if (rand == rock) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.winnerPaperWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (rand == scissors) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.loserScissorWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (rand == paper) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.tieWindowFXML,
                    mouseEvent,
                    token
            );
        }
    }

    public void scissorsClicked(MouseEvent mouseEvent) throws IOException {
        int rand = 1 + (int) (Math.random() * 4);

        if (rand == rock) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.loserRockWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (rand == paper) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.winnerScissorWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (rand == scissors) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.tieWindowFXML,
                    mouseEvent,
                    token
            );
        }
    }
}
