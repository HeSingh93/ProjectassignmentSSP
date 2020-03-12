package com.example.ssp.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.*;

public class LogInWindow extends HelperMethods {

    public PasswordField logInPasswordTextField;
    public TextField logInUsernameTextField;
    public Button logInBtn;
    public Label errorMsg;
    private String userName;
    private String password;

    Connection connection = null;
    Statement processSqlStatement = null;

    /* public void login(String userName, String password, MouseEvent mouseEvent) {
         PreparedStatement state = null;
         try {
             state = connection.prepareStatement("SELECT * FROM \"user\" where \"user_name\" = ? and \"password\" = ?;");
             state.setString(1, userName);
             state.setString(2, password);
             ResultSet validUser = state.executeQuery(); //Fråga Jon ang validUser
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
     }
 */
    public void logInBtn(MouseEvent mouseEvent) throws IOException, SQLException {
        //Kollar om användarnamn och pw finns i textrutor
        //Kontrollerar användare och lösenord mot databas
        //Finns konto skickar den vidare till Main menu
        //och skapar en token
        //Finns det inget konto som matchar promptar den "användare finns ej"
        //Finns ej användarnamn promptar den att "Fyll i användarnamn"
        //Finns ej lösenord promptar den att "Fyll i lösenord"

        logInUsernameTextField.getText().equals(userName);
        logInPasswordTextField.getText().equals(password);

        PreparedStatement state = null;
        try {
            state = connection.prepareStatement("SELECT * FROM \"user\" where \"user_name\" = ? and \"password\" = ?;");
            state.setString(1, userName);
            state.setString(2, password);
            ResultSet validUser = state.executeQuery(); //Fråga Jon ang validUser
            if (!validUser.next()) {
                errorMsg.setText("Invalid username or password!");
                errorMsg.setVisible(true);
            } else {
                SQLHelper.generateToken();
                SQLHelper.insertToken(SQLHelper.generateToken(), userName, password);
                HelperMethods.replaceScene(
                        HelperMethods.mainWindowFXML,
                        mouseEvent
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void signUpBtn(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.signUpWindowFXML,
                mouseEvent
        );
    }
}




