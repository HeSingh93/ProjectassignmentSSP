package com.example.ssp.controllers;

import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FriendsListWindow extends GenericController {
    public Tab friendsTab;

    public void addFriendsToColumn() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(FriendsList.class)
                .addAnnotatedClass(Token.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            FriendsList friendsList = new FriendsList();

            List<User> myUser = session.createQuery("from User where token_token_id = '" + token.getTokenId() + "'").getResultList();

            int myId = myUser.get(0).getUserId();

            List<FriendsList> friendsListList = session.createQuery("from FriendsList where user_id = '" + myId + "'").getResultList();



        } finally {
            factory.close();
        }

    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.mainWindowFXML,
                mouseEvent,
                token
        );
    }

    public void addFriendBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.addFriendsFXML,
                mouseEvent,
                token
        );
    }

    public void refreshButtonClicked(MouseEvent mouseEvent) {
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

            // Gets the friends id and sets it into friendsId
            List<FriendsList> friendsListList = session.createQuery("from FriendsList where user_id = '" + myId + "'").getResultList();
            System.out.println(friendsListList);


            tr.commit();

        } catch (Exception e) {
        e.printStackTrace();
        }
        finally {
            factory.close();
            session.close();
        }
    }
}
