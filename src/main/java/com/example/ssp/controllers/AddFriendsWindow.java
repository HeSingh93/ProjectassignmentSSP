package com.example.ssp.controllers;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class AddFriendsWindow {

    public Button addFriendsBtn;

    public void addFriendsBtnClicked(MouseEvent mouseEvent) {
        addFriendsBtn.setText("Friend added!");
    }

    public void backButton(MouseEvent mouseEvent) {
    }


}
