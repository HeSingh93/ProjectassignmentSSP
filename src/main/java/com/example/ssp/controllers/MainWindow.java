package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainWindow extends HelperMethods{

    public void newGameBtnClicked(MouseEvent mouseEvent) throws IOException {
        System.out.println(loggedInUser);
        HelperMethods.replaceScene(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent
        );

    }

    public void recentBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.recentGamesFXML,
                mouseEvent
        );
    }

    public void helpBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.helpWindowFXML,
                mouseEvent
        );

    }

    public void friendsBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.friendsListFXML,
                mouseEvent
        );
    }
}
