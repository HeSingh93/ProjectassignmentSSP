package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.DriverManager;

public class SplashScreen extends HelperMethods {

    @Override
    public void startUp() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SSP",
                    "postgres",
                    "TheLoveForMyHomies");
            connection.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void splashButtonClicked(MouseEvent mouseEvent) throws IOException {
        startUp();
        HelperMethods.replaceScene(
                HelperMethods.loginFXML,
                mouseEvent
        );
    }
}
