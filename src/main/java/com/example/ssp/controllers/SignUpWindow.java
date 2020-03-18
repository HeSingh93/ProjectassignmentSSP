package com.example.ssp.controllers;

import com.example.ssp.models.User;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class SignUpWindow {

    public TextField signUpPasswordTextField;
    public TextField signUpUserNameTextField;
    private String userName;
    private String password;

    public void createAccountClicked(MouseEvent mouseEvent) throws IOException {
        userName = signUpUserNameTextField.getText();
        password = signUpPasswordTextField.getText();
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            User newUser = new User(userName, password);
            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();
        }finally {
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
        //SQL-kommando
    }
}
