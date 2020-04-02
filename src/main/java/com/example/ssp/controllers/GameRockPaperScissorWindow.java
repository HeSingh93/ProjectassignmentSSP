package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import com.example.ssp.models.Token;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class GameRockPaperScissorWindow extends GenericController {

    int rock = 1;
    int paper = 2;
    int scissors = 3;
    int cpuChoice;
    int playerChoice;

    /**
     * These are the players choices. Rock, paper and scissors
     * @param mouseEvent
     * @throws IOException
     */

    public void rockClicked(MouseEvent mouseEvent) throws IOException {
        playerChoice = rock;
        calculate();
        replaceSceneVersusCpu(
                HelperMethods.playCpuResultFXML,
                mouseEvent,
                token,
                playerChoice,
                cpuChoice
        );
    }

    public void paperClicked(MouseEvent mouseEvent) throws IOException {
        playerChoice = paper;
        calculate();
        replaceSceneVersusCpu(
                HelperMethods.playCpuResultFXML,
                mouseEvent,
                token,
                playerChoice,
                cpuChoice
        );
    }

    public void scissorsClicked(MouseEvent mouseEvent) throws IOException {
        playerChoice = scissors;
        calculate();
        replaceSceneVersusCpu(
                HelperMethods.playCpuResultFXML,
                mouseEvent,
                token,
                playerChoice,
                cpuChoice
        );
    }

    /**
     * This method is used to calculate the Cpu's choice.
     */

    public void calculate() {
        Random rand = new Random();
        int n = rand.nextInt(3) + 1;

        if (n == 1) {
            cpuChoice = rock;
        } else if (n == 2) {
            cpuChoice = paper;
        } else if (n == 3) {
            cpuChoice = scissors;
        }

    }

    /**
     * A Changed version of HelperMethods.replaceScene() used to pass 2 ints as well as path, MouseEvent and Token.
     * @param fxmlPath
     * @param mouseEvent
     * @param token
     * @param playerChoice players choice, int
     * @param cpuChoice cpu's randomized choice, int
     * @throws IOException
     */

    static void replaceSceneVersusCpu(String fxmlPath, MouseEvent mouseEvent, Token token, int playerChoice, int cpuChoice) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource())
                .getScene()
                .getWindow();

        FXMLLoader loader = HelperMethods.getLoader(fxmlPath);
        Parent root = loader.load();
        GenericController controller = loader.getController();
        controller.setToken(token);
        controller.setPlayerChoice(playerChoice);
        controller.setCpuChoice(cpuChoice);
        controller.postInitialize();
        Scene scene = new Scene(root);
        stage.setTitle(HelperMethods.gameRockPaperScissorTitle);
        stage.setScene(scene);
        stage.toFront();
        stage.show();
    }
}
