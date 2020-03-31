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

import static com.example.ssp.controllers.HelperMethods.mainWindowFXML;

public class LogInWindow extends GenericController {

    public PasswordField logInPasswordTextField;
    public TextField logInUsernameTextField;
    public Button logInBtn;
    public Label errorMsg;
    private String logInUserName;
    private String logInPassword;
    public static final SecureRandom secureRandom = new SecureRandom();
    public static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    public void logInBtn(MouseEvent mouseEvent) {
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

            // Display users
            for (User tempUser : theUsers) {

                if (tempUser.getUserName().equals(logInUserName) && tempUser.getPassword().equals(logInPassword)) {

                    Token token = new Token(generateToken());

                    tempUser.setToken(token);
                    session.save(token);
                    session.save(tempUser);

                    HelperMethods.replaceSceneLoggedIn(
                            mainWindowFXML,
                            mouseEvent,
                            token
                    );
                }
            }

            errorMsg.setVisible(true);
            errorMsg.setText("Invalid username");

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




