package com.example.ssp.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class FriendsListVersusWindow extends GenericController {
    public TextField enteredUserName;
    public Button confirmBtn;

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent,
                token
        );
    }

    public void confirmBtnClicked(MouseEvent mouseEvent) throws IOException {

    }
}
