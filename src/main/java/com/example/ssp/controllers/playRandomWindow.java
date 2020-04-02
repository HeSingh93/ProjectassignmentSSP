package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static com.example.ssp.controllers.HelperMethods.*;

public class playRandomWindow extends GenericController {

    /**
     * In this scene we use Math.random to generate a number between 0 - 3. If the random number is either 1, 2 or 3
     * we change the scene to the relevant view. (Rock, paper, or scissors).
     * @param mouseEvent
     * @throws IOException
     */
    public void randomClicked(MouseEvent mouseEvent) throws IOException {

        int random = (int) (Math.random() * 4);

        if (random == 1) {
            HelperMethods.replaceSceneLoggedIn(
                    rockViewFXML,
                    mouseEvent,
                    token
            );
        }
        if (random == 2) {
            HelperMethods.replaceSceneLoggedIn(
                    paperViewFXML,
                    mouseEvent,
                    token
            );
        }
        if (random == 3) {
            HelperMethods.replaceSceneLoggedIn(
                    scissorViewFXML,
                    mouseEvent,
                    token
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
}
