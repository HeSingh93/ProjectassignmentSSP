package com.example.ssp.controllers;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class LogInWindow {


    public void logInBtn(MouseEvent mouseEvent) throws IOException {
        //Kollar om användarnamn och pw finns i textrutor
        //Kontrollerar användare och lösenord mot databas
        //Finns konto skickar den vidare till Main menu
        //och skapar en token
        //Finns det inget konto som matchar promptar den "användare finns ej"
        //Finns ej användarnamn promptar den att "Fyll i användarnamn"
        //Finns ej lösenord promptar den att "Fyll i lösenord"

        HelperMethods.replaceScene(
                HelperMethods.mainWindowFXML,
                mouseEvent
        );

    }

    public void signUpBtn(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.signUpWindowFXML,
                mouseEvent
        );
    }
}




