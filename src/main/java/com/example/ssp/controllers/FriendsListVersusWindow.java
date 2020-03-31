package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class FriendsListVersusWindow extends GenericController {
    public TextField enteredUserName;
    public Label errorMessage;

    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent,
                token
        );
    }

    public void confirmBtnClicked(MouseEvent mouseEvent) throws IOException {

        String queriedUserName = enteredUserName.getText();

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Token.class)
                .addAnnotatedClass(FriendsList.class)
                .addAnnotatedClass(Choice.class)
                .buildSessionFactory();

        //Create session
        Session session = factory.getCurrentSession();

        try {

            Transaction tr = session.beginTransaction();

            List<FriendsList> searchedFriend = session.createQuery(
                    "from FriendsList where friends_name = '" + queriedUserName + "'").getResultList();

            List<User> myUser = session.createQuery(
                    "from User where token_token_id = '" + token.getTokenId() + "'").getResultList();

            List<FriendsList> comparedFriend = session.createQuery(
                    "from FriendsList where friends_name = '" + queriedUserName
                            + "'  and user_id = " + myUser.get(0).getUserId()).getResultList();

            if (comparedFriend.get(0).getFriendsName().equals(queriedUserName)) {

                List<Choice> matchExists = session.createQuery(
                        "from Choice where user_id = '" + myUser.get(0).getUserId()
                                + "' and friend_id = '" + comparedFriend.get(0).getFriendId() + "'").getResultList();

                if (matchExists.size() < 1) {

                    Choice choice = new Choice();

                    choice.setUserId(myUser.get(0).getUserId());
                    choice.setFriendId(searchedFriend.get(0).getFriendId());

                    session.save(choice);

                    HelperMethods.replaceSceneVersusPlayer(
                            HelperMethods.gameRockPaperScissorVersusFXML,
                            mouseEvent,
                            token,
                            choice
                    );

                } else {
                    Font font = new Font("Arial Black", 10);
                    errorMessage.setVisible(true);
                    errorMessage.setFont(font);
                    errorMessage.setText("YOU HAVE ALREADY STARTED A MATCH WITH THIS USER!");

                }
            }

            tr.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            session.close();
        }

    }
}
