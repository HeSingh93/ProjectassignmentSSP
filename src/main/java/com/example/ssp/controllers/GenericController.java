package com.example.ssp.controllers;

import com.example.ssp.models.User;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenericController {
    private String valueOfToken;
    User user;
    Stage stage;


    public void inheritSettings(GenericController controller, Scene scene) {
        user = controller.user;
        stage = controller.stage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stage getStage() {
        return stage;
    }

    public String getValueOfToken() {
        return valueOfToken;
    }

    public void setValueOfToken(String valueOfToken) {
        this.valueOfToken = valueOfToken;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}