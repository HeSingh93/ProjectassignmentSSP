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
            HelperMethods.replaceScene(
                    HelperMethods.loserPaperWindowFXML,
                    mouseEvent
            );
        }
        if (rand == scissors) {
            HelperMethods.replaceScene(
                    HelperMethods.winnerRockWindowFXML,
                    mouseEvent
            );
        }
        if (rand == rock) {
            HelperMethods.replaceScene(
                    HelperMethods.tieWindowFXML,
                    mouseEvent
            );
        }
    }

    public void paperClicked(MouseEvent mouseEvent) throws IOException {
        int rand = 1 + (int) (Math.random() * 4);

        if (rand == rock) {
            HelperMethods.replaceScene(
                    HelperMethods.winnerPaperWindowFXML,
                    mouseEvent
            );
        }
        if (rand == scissors) {
            HelperMethods.replaceScene(
                    HelperMethods.loserScissorWindowFXML,
                    mouseEvent
            );
        }
        if (rand == paper) {
            HelperMethods.replaceScene(
                    HelperMethods.tieWindowFXML,
                    mouseEvent
            );
        }

    }

    public void scissorsClicked(MouseEvent mouseEvent) throws IOException {
        int rand = 1 + (int) (Math.random() * 4);

        if (rand == rock) {
            HelperMethods.replaceScene(
                    HelperMethods.loserRockWindowFXML,
                    mouseEvent
            );
        }
        if (rand == paper) {
            HelperMethods.replaceScene(
                    HelperMethods.winnerScissorWindowFXML,
                    mouseEvent
            );
        }
        if (rand == scissors) {
            HelperMethods.replaceScene(
                    HelperMethods.tieWindowFXML,
                    mouseEvent
            );
        }
    }
}
