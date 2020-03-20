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
import java.security.SecureRandom;

import java.util.Base64;
import java.util.List;

public class LogInWindow extends HelperMethods {

    public PasswordField logInPasswordTextField;
    public TextField logInUsernameTextField;
    public Button logInBtn;
    public Label errorMsg;
    private String logInUserName;
    private String logInPassword;
    private String token;
    public static final SecureRandom secureRandom = new SecureRandom();
    public static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

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
                .addAnnotatedClass(Token.class)
                .buildSessionFactory();

        //Create session
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //Query users
            List<User> theUsers = session.createQuery("from User where user_name = '" + logInUserName + "'").getResultList();
            System.out.println(theUsers);

            // Display users
            for (User tempUser : theUsers) {
                System.out.println(tempUser);

                if (tempUser.getUserName().equals(logInUserName) && tempUser.getPassword().equals(logInPassword)) {
                    System.out.println("We got into if loop");

                    List<Token> tempToken = session.createQuery("from Token where token_id = " + tempUser.getUserId() + "").getResultList();
                    System.out.println(tempToken);

                    //Token token = new Token(generateToken());

                    //tempUser.setToken(token);

                    HelperMethods.replaceScene(
                            mainWindowFXML,
                            mouseEvent
                    );

                    session.save(tempUser);

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
    }

    public void signUpBtn(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.signUpWindowFXML,
                mouseEvent
        );
    }
}




