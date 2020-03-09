package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainWindow {

    public void newGameBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.versusPlayCpuWindowFXML,
                HelperMethods.gameRockPaperScissorTitle,
                mouseEvent
        );

    }

    public void recentBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.recentGamesFXML,
                HelperMethods.gameRockPaperScissorTitle,
                mouseEvent
        );
    }

    public void helpBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.helpWindowFXML,
                HelperMethods.gameRockPaperScissorTitle,
                mouseEvent
        );

    }

    public void friendsBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.friendsListFXML,
                HelperMethods.gameRockPaperScissorTitle,
                mouseEvent
        );
    }
}
