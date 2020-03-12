package com.example.ssp.controllers;

import com.example.ssp.models.Game;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class playRandomWindow extends Game {

    public void randomClicked(MouseEvent mouseEvent) throws IOException {

        int random = (int) (Math.random() * 4);

        if (random == 1) {
            HelperMethods.replaceScene(
                    rockViewFXML,
                    mouseEvent
            );
        }
        if (random == 2) {
            HelperMethods.replaceScene(
                    paperViewFXML,
                    mouseEvent
            );
        }
        if (random == 3) {
            HelperMethods.replaceScene(
                    scissorViewFXML,
                    mouseEvent
            );
        }
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent

        );
    }
}
