package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.DriverManager;

public class SplashScreen extends HelperMethods {

    @Override
    public void startUp() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("postgres://jxobeclzyreepf:4ce1d93228a8d0c016098c63cfa769751115a60b5c2fceed51e649029c873397@ec2-50-17-178-87.compute-1.amazonaws.com:5432/d3biv5j155is1v");
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
