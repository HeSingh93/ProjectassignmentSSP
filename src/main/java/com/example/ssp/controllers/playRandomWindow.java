package com.example.ssp.controllers;

import com.example.ssp.models.Token;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import static com.example.ssp.controllers.HelperMethods.*;

public class playRandomWindow extends GenericController {

    /**
     * In this scene we use Math.random to generate a number between 0 - 3. If the random number is either 1, 2 or 3
     * we change the scene to the relevant view. (Rock, paper, or scissors).
     * @param mouseEvent
     * @throws IOException
     */
    public void randomClicked(MouseEvent mouseEvent) throws IOException {

        Random rand = new Random();
        int n = rand.nextInt(3) + 1;

        if (n == 1) {
            playerChoice = n;
            replaceSceneRandom(
                    randomChoiceFXML,
                    mouseEvent,
                    token,
                    playerChoice
            );
        }
        if (n == 2) {
            playerChoice = n;
            replaceSceneRandom(
                    randomChoiceFXML,
                    mouseEvent,
                    token,
                    playerChoice
            );
        }
        if (n == 3) {
            playerChoice = n;
            replaceSceneRandom(
                    randomChoiceFXML,
                    mouseEvent,
                    token,
                    playerChoice
            );
        }
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent,
                token

        );
    }

    static void replaceSceneRandom(String fxmlPath, MouseEvent mouseEvent, Token token, int randomChoice) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource())
                .getScene()
                .getWindow();

        FXMLLoader loader = HelperMethods.getLoader(fxmlPath);
        Parent root = loader.load();
        GenericController controller = loader.getController();
        controller.setToken(token);
        controller.setPlayerChoice(randomChoice);
        controller.postInitialize();
        Scene scene = new Scene(root);
        stage.setTitle(HelperMethods.gameRockPaperScissorTitle);
        stage.setScene(scene);
        stage.toFront();
        stage.show();
    }
}
