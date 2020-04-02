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

    /**
     * This method generates a string of random characters and numbers.
     * @return a generated string.
     */
    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

    /**
     * When logging in, the user is prompted to enter a username. A HQL-query is created to check if the user exists
     * in the database. If the login is successful, the user gets a generated token which is
     * inserted in to the database and the scene is replaced, passing the token along with it.
     * If the user does not exist or the username/password is incorrect, the program
     * prompts that the username or password is invalid.
     * @param mouseEvent
     */
    public void logInBtn(MouseEvent mouseEvent) {
        logInUserName = logInUsernameTextField.getText();
        logInPassword = logInPasswordTextField.getText();

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Token.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<User> theUsers = session.createQuery("from User where user_name = '" + logInUserName + "'").getResultList();

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
            errorMsg.setText("Invalid username or password");

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




