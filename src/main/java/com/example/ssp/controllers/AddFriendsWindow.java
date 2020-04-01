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

    /**
     * This class adds a friend to the users friendslist.
     * Creates a SessionFactory that loads an xml file (hibernate.cfg).
     * The hibernate.cfg.xml file has information about the connection to the database
     * and contains the postgres driver.
     * .addAnnotatedClass contains the classes that are mapped to our database.
     * We start off with session.beginTransaction(), which opens up the connection to the database
     * allowing us to use it. After that we query the database by creating a query and passing
     * our HQL-statement. In this case we use our token to retrieve our user_id and set it
     * to our object Friendslist friends.
     * Our next query takes the input of the textinput from the TextField and queries a User with our given input.
     * We retrieve a List with the user_id of the queried User. We then use the id to set it to our object FriendsList
     * friends.
     * After that we save the session by using session.save(friends) and session.getTransaction.commit(),
     * we save the object friends in the database.
     * If the user does not exist in the database, the program prompts that the given user could not be found.
     */

    public void addFriendsBtnClicked() {

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

            List<User> myUser = session.createQuery("from User where token_token_id = '" + token.getTokenId() + "'").getResultList();

            List<User> queriedUser = session.createQuery("from User where user_name = '" + userNameEntered + "'").getResultList();

            if (queriedUser.size() > 0) {

                FriendsList friends = new FriendsList();

                friends.setFriendId(queriedUser.get(0).getUserId());
                friends.setUserId(myUser.get(0).getUserId());
                friends.setFriendsName(queriedUser.get(0).getUserName());

                session.save(friends);

                session.getTransaction().commit();

                promptLabel.setText("FRIEND ADDED!");

            } else {

                promptLabel.setText("That user could not be found! Please try again.");
                promptLabel.setVisible(true);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

    /**
     * When the backButton is clicked, we replace the scene with the friendsList view
     * as well as passing our token-object to the next scene.
     * @param mouseEvent
     * @throws IOException
     */
    public void backButtonClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.friendsListFXML,
                mouseEvent,
                token
        );
    }
}
