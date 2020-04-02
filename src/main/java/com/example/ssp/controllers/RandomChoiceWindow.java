package com.example.ssp.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class RandomChoiceWindow extends GenericController {

    public ImageView randomImage;

    @Override
    public void postInitialize() {

        setChoiceImage(playerChoice, randomImage);

    }

    public void setChoiceImage(int choice, ImageView image) {
        switch (choice) {
            case 1:
                Image rock = new Image(HelperMethods.getResAsStream("images/rock.png"));
                image.setImage(rock);
                break;

            case 2:
                Image paper = new Image(HelperMethods.getResAsStream("images/paper.png"));
                image.setImage(paper);
                break;

            case 3:
                Image scissor = new Image(HelperMethods.getResAsStream("images/scissors.png"));
                image.setImage(scissor);
                break;
        }
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.playRandomFXML,
                mouseEvent,
                token
        );
    }
}
