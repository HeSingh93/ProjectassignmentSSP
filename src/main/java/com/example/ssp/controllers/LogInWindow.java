package com.example.ssp.controllers;

import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class LogInWindow extends HelperMethods {

    public PasswordField logInPasswordTextField;
    public TextField logInUsernameTextField;
    public Button logInBtn;
    public Label errorMsg;
    private String logInUserName;
    private String logInPassword;

    Connection connection;
    Statement processSqlStatement;

    public void logInBtn(MouseEvent mouseEvent) {
        //Kollar om användarnamn och pw finns i textrutor
        //Kontrollerar användare och lösenord mot databas
        //Finns konto skickar den vidare till Main menu
        //och skapar en token
        //Finns det inget konto som matchar promptar den "användare finns ej"
        //Finns ej användarnamn promptar den att "Fyll i användarnamn"
        //Finns ej lösenord promptar den att "Fyll i lösenord"

        logInUserName = logInUsernameTextField.getText();
        logInPassword = logInPasswordTextField.getText();

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        //Create session
        Session session = factory.getCurrentSession();

        try {
            //Start transaction
            session.beginTransaction();

            //Query users
            List<User> theUsers = session.createQuery("from User where user_name = '" + logInUserName +"'").getResultList();
            System.out.println(theUsers);

            // Display users
            for (User tempUser : theUsers) {
                System.out.println(tempUser);

                if (tempUser.getUserName().equals(logInUserName) && tempUser.getPassword().equals(logInPassword)) {
                    System.out.println("We got into if loop");

                    HelperMethods.replaceScene(
                            mainWindowFXML,
                            mouseEvent
                    );

                    //Commit transaction
                    session.getTransaction().commit();

                }
            }

            errorMsg.setVisible(true);
            errorMsg.setText("Invalid username");
            System.out.println("We got into else part");

            //Commit transaction
            session.getTransaction().commit();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            factory.close();
            session.close();
        }

      /*  PreparedStatement state = null;

        try {
            System.out.println("conn");
            System.out.println(connection);
            state = connection.prepareStatement("SELECT * FROM \"user\" where \"user_name\" = ? and \"password\" = ?;");
            state.setString(1, userName);
            state.setString(2, password);
            System.out.println("username");
            System.out.println("password");
            System.out.println(userName);
            System.out.println(password);
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
            e.printStackTrace();
        }

       */
    }

    public void signUpBtn(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.signUpWindowFXML,
                mouseEvent
        );
    }
}




