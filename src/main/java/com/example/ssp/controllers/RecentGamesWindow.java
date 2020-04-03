package com.example.ssp.controllers;

import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Results;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class RecentGamesWindow extends GenericController {

    public VBox box1;
    public Label wins;
    public Label loss;
    public Label wl;

    /**
     * In this scene we create a query that asks the database to retrieve the users wins and losses. We create an
     * object (User), to get the current users id with our token. After that a Results object is created. With this
     * object, a getter method is used to retrieve wins and losses, printing them out in the scene.
     */
    @Override
    public void postInitialize() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(FriendsList.class)
                .addAnnotatedClass(Token.class)
                .addAnnotatedClass(Results.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            Transaction tr = session.beginTransaction();

            List<User> myUser = session.createQuery("from User where token_token_id = '" + token.getTokenId() + "'").getResultList();
            int myId = myUser.get(0).getUserId();

            Results results = (Results) session.createQuery("from Results where user_id = '" + myId + "'").getSingleResult();

            wins.setText("Total wins: " + results.getWins());
            loss.setText("Total losses: " + results.getLosses());
            double wlRatio = (double) (results.getWins() / results.getLosses());
            wl.setText("Win/Loss ratio: " + wlRatio);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            session.close();
        }
    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.mainWindowFXML,
                mouseEvent,
                token
        );
    }
}
