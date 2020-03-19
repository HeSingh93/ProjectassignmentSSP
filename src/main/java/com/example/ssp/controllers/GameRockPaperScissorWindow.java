package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class GameRockPaperScissorWindow {


    int rock = 1;
    int paper = 2;
    int scissors = 3;

    public void rockClicked(MouseEvent mouseEvent) throws IOException {
        int x = 1 + (int) (Math.random() * 3);

        if (x == paper) {
            HelperMethods.replaceScene(
                    HelperMethods.loserPaperWindowFXML,
                    mouseEvent
            );
        }
        if (x == scissors) {
            HelperMethods.replaceScene(
                    HelperMethods.winnerRockWindowFXML,
                    mouseEvent
            );
        }
        if (x == rock) {
            HelperMethods.replaceScene(
                    HelperMethods.tieWindowFXML,
                    mouseEvent
            );
        }
    }

    public void paperClicked(MouseEvent mouseEvent) throws IOException {
        int x = 1 + (int) (Math.random() * 3);

        if (x == rock) {
            HelperMethods.replaceScene(
                    HelperMethods.winnerPaperWindowFXML,
                    mouseEvent
            );
        }
        if (x == scissors) {
            HelperMethods.replaceScene(
                    HelperMethods.loserScissorWindowFXML,
                    mouseEvent
            );
        }
        if (x == paper) {
            HelperMethods.replaceScene(
                    HelperMethods.tieWindowFXML,
                    mouseEvent
            );
        }

    }

    public void scissorsClicked(MouseEvent mouseEvent) throws IOException {
        int x = 1 + (int) (Math.random() * 3);

        if (x == rock) {
            HelperMethods.replaceScene(
                    HelperMethods.loserRockWindowFXML,
                    mouseEvent
            );
        }
        if (x == paper) {
            HelperMethods.replaceScene(
                    HelperMethods.winnerScissorWindowFXML,
                    mouseEvent
            );
        }
        if (x == scissors) {
            HelperMethods.replaceScene(
                    HelperMethods.tieWindowFXML,
                    mouseEvent
            );
        }
    }
}
