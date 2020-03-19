package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Random;

public class GameRockPaperScissorWindow {

    int rock = 1;
    int paper = 2;
    int scissors = 3;


    public void rockClicked(MouseEvent mouseEvent, Random random) throws IOException {

        int computerChoice = random.nextInt(3) + 1;

        if (computerChoice == 2) {
            HelperMethods.replaceScene(
                    HelperMethods.loserPaperWindowFXML,
                    mouseEvent
            );
        }



    }

    public void paperClicked(MouseEvent mouseEvent) {
        String userWordChoice = "paper";
    }

    public void scissorsClicked(MouseEvent mouseEvent) {
        String userWordChoice = "scissors";
    }
}
