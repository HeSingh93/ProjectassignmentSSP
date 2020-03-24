package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class FriendsListWindow extends GenericController {
    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.mainWindowFXML,
                mouseEvent,
                token
        );
    }

    public void addFriendBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.addFriendsFXML,
                mouseEvent,
                token
        );
    }
}
