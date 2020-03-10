package com.example.ssp.controllers;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AddFriendsWindow {

    public Button addFriendsBtn;

    public void addFriendsBtnClicked(MouseEvent mouseEvent) {
        addFriendsBtn.setText("Friend added!");
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.friendsListFXML,
                mouseEvent
        );
    }


}
