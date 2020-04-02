package com.example.ssp.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LoadScreenWindow extends GenericController {

    public Button showResultBtn;
    public Label loadLabel;

    /**
     * Creates a new thread that sets a delay (7 seconds). Used to create an illusion of calculating the winner.
     * After 7 seconds, a button appears that sends the user to the next scene.
     */
    @Override
    public void postInitialize() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(7000);
                        showResultBtn.setVisible(true);
                        loadLabel.setVisible(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }

    public void showResultBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneVersusPlayer(
                HelperMethods.versusResultWindowFXML,
                mouseEvent,
                token,
                choice
        );
    }
}
