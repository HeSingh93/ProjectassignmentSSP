package com.example.ssp.controllers;

import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class AddFriendsWindow extends GenericController {


    public Button addFriendsBtn;
    public Label promptLabel;
    public TextField addFriendsUserNameTF;

    public void addFriendsBtnClicked(MouseEvent mouseEvent) {

        String userNameEntered = addFriendsUserNameTF.getText();

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(FriendsList.class)
                .addAnnotatedClass(Token.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            List<User> userList = session.createQuery("from User where user_name = '" + userNameEntered + "'").getResultList();
            System.out.println(userList);

            if (userList.size() > 0) {
                FriendsList friends = new FriendsList();
                User userAdd = new User();

            }

        } finally {
            session.close();
            factory.close();
        }


    }

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.friendsListFXML,
                mouseEvent,
                token
        );
    }


}
