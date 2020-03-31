package com.example.ssp.controllers;

import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class FriendsListWindow extends GenericController {
    public VBox friendsListNameHolder;

    @Override
    public void postInitialize(){
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

            // Iterates through the friendslist, putting names into Labels and displaying them
            for (FriendsList friendsList : friendsListList) {
                Label friendsLabel = new Label(friendsList.getFriendsName());
                Font font = new Font("Arial Black", 20);

                friendsLabel.setFont(font);

                friendsListNameHolder.getChildren()
                        .addAll(friendsLabel);
            }

            tr.commit();

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

    public void addFriendBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.addFriendsFXML,
                mouseEvent,
                token
        );
    }

}
