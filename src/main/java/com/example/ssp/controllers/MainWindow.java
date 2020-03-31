package com.example.ssp.controllers;

import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.IOException;

public class MainWindow extends GenericController {


    public void newGameBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent,
                token
        );

    }

    public void recentBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.recentGamesFXML,
                mouseEvent,
                token
        );
    }

    public void helpBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.helpWindowFXML,
                mouseEvent,
                token
        );
    }

    public void friendsBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.friendsListFXML,
                mouseEvent,
                token
        );
    }

    public void exitBtnClicked(MouseEvent mouseEvent) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Token.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            Query query = session.createQuery("delete Token where token_id = '" + token.getTokenId() + "'");
            query.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }

      //  Platform.exit();


    }

}
