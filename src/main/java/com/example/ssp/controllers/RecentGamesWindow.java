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

    @Override
    public void postInitialize() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(FriendsList.class)
                .addAnnotatedClass(Token.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            Transaction tr = session.beginTransaction();

            // Gets the users id and sets it into myId
            List<User> myUser = session.createQuery("from User where token_token_id = '" + token.getTokenId() + "'").getResultList();
            int myId = myUser.get(0).getUserId();

            Results results = (Results) session.createQuery("from Results where user_id = '" + myId + "'").getSingleResult();

            wins.setText("Total wins: " + results.getWins());
            loss.setText("Total losses: " + results.getLosses());

            box1.getChildren().addAll(wins, loss);

        } catch(Exception e) {
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
