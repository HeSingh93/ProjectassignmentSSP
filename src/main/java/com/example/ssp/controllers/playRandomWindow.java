package com.example.ssp.controllers;

import com.example.ssp.models.Game;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class playRandomWindow extends Game{

    public void randomClicked(MouseEvent mouseEvent) {
        Game.randomlySelect();
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent

        );
    }
}
