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

    /**
     * When the backButton is clicked, we replace the scene with the versusPlayCpuWindow
     * as well as passing our token-object to the next scene.
     * @param mouseEvent
     * @throws IOException
     */
    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.versusPlayCpuWindowFXML,
                mouseEvent,
                token
        );
    }

    /**
     * In this method we type the name of a user that we would like to play a game with. The method checks if
     * the user entered is in the users friend list.
     * We start off by querying our friend list with the given input. After that we create a query for retrieving
     * our user id with our token.
     * The third query checks if the friend exists in our friend list.
     * If it exists, we create an object (Choice) that contains our match request. We use set methods to set the value
     * of user id and friend id.
     * The object (choice) is then passed on to the next scene, along with our token.
     * If you have a ongoing match with the entered user, the program prompts that a match is already active.
     *
     * @param mouseEvent
     * @throws IOException
     */
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

            if (comparedFriend.size() > 0 ) {

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
            } else {
                Font font = new Font("Arial Black", 10);
                errorMessage.setVisible(true);
                errorMessage.setFont(font);
                errorMessage.setText("YOU HAVE NOT ADDED THIS FRIEND YET");
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
