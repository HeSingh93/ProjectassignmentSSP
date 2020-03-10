package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class VersusPlayCpuWindow {
    public void versusBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent
        );
    }

    public void cpuBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.playCpuFXML,
                mouseEvent
        );
    }

    public void playBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.playRandomFXML,
                mouseEvent
        );
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.mainWindowFXML,
                mouseEvent
        );
    }
}
