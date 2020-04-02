package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PlayCpuResult extends GenericController {

    public Label winnerLabel;
    public ImageView playerImage;
    public ImageView opponentImage;

    @Override
    public void postInitialize() {
        setChoiceImage(playerChoice, playerImage);
        setChoiceImage(cpuChoice, opponentImage);
        calculateWinner(playerChoice, cpuChoice);
    }

    /**
     * Method used to set the winner label
     * @param playerChoice int
     * @param cpuChoice int
     */

    public void calculateWinner(int playerChoice, int cpuChoice) {
        if (playerChoice == 1 && cpuChoice == 1) {
            winnerLabel.setText("TIE!");
        } else if (playerChoice == 1 && cpuChoice == 2) {
            winnerLabel.setText("CPU WINS!");

        } else if (playerChoice == 1 && cpuChoice == 3) {
            winnerLabel.setText("YOU WIN!");

        } else if (playerChoice == 2 && cpuChoice == 1) {
            winnerLabel.setText("YOU WIN!");

        } else if (playerChoice == 2 && cpuChoice == 2) {
            winnerLabel.setText("TIE!");

        } else if (playerChoice == 2 && cpuChoice == 3) {
            winnerLabel.setText("CPU WINS!");

        } else if (playerChoice == 3 && cpuChoice == 1) {
            winnerLabel.setText("CPU WINS!");

        } else if (playerChoice == 3 && cpuChoice == 2) {
            winnerLabel.setText("YOU WIN!");

        } else if (playerChoice == 3 && cpuChoice == 3) {
            winnerLabel.setText("TIE!");

        }
    }

    /**
     * This method takes the input from GameRockPaperScissorWindow class. (1 - 3)
     * The input gets an assigned image that is set.
     * @param choice the object passed on from GameRockPaperScissorVersus class.
     * @param image the relevant image for the selected choice.
     */

    public void setChoiceImage(int choice, ImageView image) {
        switch (choice) {
            case 1:
                Image rock = new Image(HelperMethods.getResAsStream("images/rock.png"));
                image.setImage(rock);
                break;

            case 2:
                Image paper = new Image(HelperMethods.getResAsStream("images/paper.png"));
                image.setImage(paper);
                break;

            case 3:
                Image scissor = new Image(HelperMethods.getResAsStream("images/scissors.png"));
                image.setImage(scissor);
                break;
        }
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent,
                token
        );
    }

    public void playAgainBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.gameRockPaperScissorFXML,
                mouseEvent,
                token
        );
    }
}
