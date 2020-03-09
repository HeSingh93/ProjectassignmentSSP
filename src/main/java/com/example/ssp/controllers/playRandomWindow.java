package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class playRandomWindow {

    public void randomClicked(MouseEvent mouseEvent) {
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.versusPlayCpuWindowFXML,
                HelperMethods.gameRockPaperScissorTitle,
                mouseEvent

        );
    }
}
