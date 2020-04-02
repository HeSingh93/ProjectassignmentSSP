package com.example.ssp.controllers;

import com.example.ssp.models.SignUpUser;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class SignUpWindow extends GenericController {

    public TextField signUpPasswordTextField;
    public TextField signUpUserNameTextField;
    private String userName;
    private String password;

    /**
     * In this method we create a new account if a user does not have one. Two strings (userName, password) is used
     * to store the input that we get from the TextField. A new object SignUpUser is created that has a constructor
     * that takes two parameters, username and password. The input that has been put in to the strings are put in to
     * the newly created object and saving it in the end and replacing the scene with the login window.
     * @param mouseEvent
     * @throws IOException
     */
    public void createAccountClicked(MouseEvent mouseEvent) throws IOException {
        userName = signUpUserNameTextField.getText();
        password = signUpPasswordTextField.getText();
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(SignUpUser.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            SignUpUser newUser = new SignUpUser(userName, password);

            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }

        HelperMethods.replaceScene(
                HelperMethods.loginFXML,
                mouseEvent
        );
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceScene(
                HelperMethods.loginFXML,
                mouseEvent
        );
    }
}
