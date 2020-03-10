package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class FriendsListWindow {
    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.mainWindowFXML,
                mouseEvent
        );
    }

    public void addFriendBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.addFriendsFXML,
                mouseEvent
        );
    }

    private void applyNewtext() {

    }
}
