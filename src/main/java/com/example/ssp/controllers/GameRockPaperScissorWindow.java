package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Random;

public class GameRockPaperScissorWindow extends GenericController {

    int rock = 1;
    int paper = 2;
    int scissors = 3;
    int cpuChoice;
    int playerChoice;

    public void rockClicked(MouseEvent mouseEvent) throws IOException {
        playerChoice = rock;
        calculate(mouseEvent);
    }

    public void paperClicked(MouseEvent mouseEvent) throws IOException {
        playerChoice = paper;
        calculate(mouseEvent);
    }

    public void scissorsClicked(MouseEvent mouseEvent) throws IOException {
        playerChoice = scissors;
        calculate(mouseEvent);
    }

    public void calculate(MouseEvent mouseEvent) throws IOException {
        Random rand = new Random();
        int n = rand.nextInt(4);

        if (n == 1) {
            cpuChoice = rock;
        } else if (n == 2) {
            cpuChoice = paper;
        } else if (n == 3) {
            cpuChoice = scissors;
        }

        if (playerChoice == rock && cpuChoice == rock) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.tieWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (playerChoice == rock && cpuChoice == paper) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.loserPaperWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (playerChoice == rock && cpuChoice == scissors) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.winnerRockWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (playerChoice == paper && cpuChoice == rock) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.winnerPaperWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (playerChoice == paper && cpuChoice == paper) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.tieWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (playerChoice == paper && cpuChoice == scissors) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.winnerPaperWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (playerChoice == scissors && cpuChoice == rock) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.loserRockWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (playerChoice == scissors && cpuChoice == paper) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.winnerScissorWindowFXML,
                    mouseEvent,
                    token
            );
        }
        if (playerChoice == scissors && cpuChoice == scissors) {
            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.tieWindowFXML,
                    mouseEvent,
                    token
            );
        }
    }
}
