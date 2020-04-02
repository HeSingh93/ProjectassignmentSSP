package com.example.ssp.controllers;

import com.example.ssp.models.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class VersusResultWindow extends GenericController {

    public ImageView userImage, opponentImage;
    public Label friendName, userName, resultTextLabel;
    public String myName, yourName;

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(FriendsList.class)
            .addAnnotatedClass(Token.class)
            .addAnnotatedClass(Choice.class)
            .addAnnotatedClass(Results.class)
            .buildSessionFactory();

    Session session = factory.getCurrentSession();

    /**
     * The postInitialize method is used to show the winner between the user and the opponent. We use three different
     * HQL-queries. One query gets the users id, one query gets the opponents id and the third gets the opponents
     * selected choice. Getters are used to get the names of the user and the opponent and sets these as labels.
     * We also call two methods, setChoiceImage and GetWinner.
     */
    @Override
    public void postInitialize() {

        try {
            session.beginTransaction();

            User myId = (User) session.createQuery(
                    "from User where token_token_id = '" + token.getTokenId() + "'")
                    .getSingleResult();

            User yourId = session.get(User.class, choice.getFriendId());

            Choice opponentChoice = (Choice) session.createQuery(
                    "from Choice where user_id = '" + choice.getFriendId()
                            + "' and friend_id = '" + myId.getUserId() + "'")
                    .getSingleResult();

            myName = myId.getUserName();
            yourName = yourId.getUserName();

            friendName.setText(yourName);
            userName.setText(myName);

            setChoiceImage(choice, userImage);
            setChoiceImage(opponentChoice, opponentImage);
            getWinner(choice, opponentChoice, resultTextLabel);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

    /**
     * When the mainMenu button is clicked, we delete the saved choice-object, which contains the user id, friend id
     * and choice that has been made. If we don't delete the choice-object, we can't start a new game with the same
     * friend.
     * @param mouseEvent
     */
    public void mainMenuBtnClicked(MouseEvent mouseEvent) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(FriendsList.class)
                .addAnnotatedClass(Token.class)
                .addAnnotatedClass(Choice.class)
                .addAnnotatedClass(Results.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            session.delete(choice);

            HelperMethods.replaceSceneLoggedIn(
                    HelperMethods.mainWindowFXML,
                    mouseEvent,
                    token
            );

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

    /**
     * This method takes the input from GameRockPaperScissorVersus class. (1 - 3), if the user has not made a choice
     * the choice is set to 0. The input gets an assigned image that is set.
     * @param choice the object passed on from GameRockPaperScissorVersus class.
     * @param image the relevant image for the selected choice.
     */
    public void setChoiceImage(Choice choice, ImageView image) {

        switch (choice.getChoice()) {
            case 0:
                Image questionMark = new Image(HelperMethods.getResAsStream("images/questionmark.png"));
                image.setImage(questionMark);
                break;

            case 1:
                Image rock = new Image(HelperMethods.getResAsStream("images/rock.png"));
                image.setImage(rock);
                break;

            case 2:
                Image paper = new Image(HelperMethods.getResAsStream("images/paper.png"));
                image.setImage(paper);
                break;

            case 3:
                Image scissor = new Image(HelperMethods.getResAsStream("images/scissors.png"));
                image.setImage(scissor);
                break;
        }
    }

    /**
     * This method calculates the winner by comparing the two choices made.
     * @param myChoice the choice that has been made by the user.
     * @param yourChoice the choice that has made by the opponent.
     * @param resultLabel depending on the result, the resultLabel sets the text depending on the calculated winner.
     */
    public void getWinner(Choice myChoice, Choice yourChoice, Label resultLabel) {

        if (myChoice.getChoice() == 1 && yourChoice.getChoice() == 1) {
            resultLabel.setText("TIE!");
        } else if (choice.getChoice() == 1 && yourChoice.getChoice() == 2) {
            resultLabel.setText(yourName + " Wins!");
            updateLoss();

        } else if (choice.getChoice() == 1 && yourChoice.getChoice() == 3) {
            resultLabel.setText(myName + " Wins!");
            updateWins();

        } else if (choice.getChoice() == 2 && yourChoice.getChoice() == 1) {
            resultLabel.setText(myName + " Wins!");
            updateWins();

        } else if (choice.getChoice() == 2 && yourChoice.getChoice() == 2) {
            resultLabel.setText("TIE!");

        } else if (choice.getChoice() == 2 && yourChoice.getChoice() == 3) {
            resultLabel.setText(yourName + " Wins!");
            updateLoss();

        } else if (choice.getChoice() == 3 && yourChoice.getChoice() == 1) {
            resultLabel.setText(yourName + " Wins!");
            updateLoss();

        } else if (choice.getChoice() == 3 && yourChoice.getChoice() == 2) {
            resultLabel.setText(myName + " Wins!");
            updateWins();

        } else if (choice.getChoice() == 3 && yourChoice.getChoice() == 3) {
            resultLabel.setText("TIE!");

        }
    }

    /**
     * This method is used to update number of wins in the database,
     * the method creates a List that contains the active users results in the database.
     * it then checks if there is an existing entry in the databse.
     * If there is an existing entry it updates the wins and if it's empty it creates a new entry in the database.
     */
    public void updateWins() {

        List<Results> currentResults = session.createQuery(
                "from Results where user_id = '" + choice.getUserId() + "'")
                .getResultList();

        if (currentResults.size() == 0) {

            Results updateWins = new Results();
            updateWins.setUserId(choice.getUserId());
            updateWins.setWins(1);

            session.save(updateWins);
            session.getTransaction().commit();

        } else {

            Results updateResults = (Results) session.createQuery(
                    "from Results where user_id = '" + choice.getUserId() + "'").getSingleResult();

            int loser = currentResults.get(0).getLosses();
            int winner = currentResults.get(0).getWins();
            int finalWinner = winner + 1;

            updateResults.setUserId(choice.getUserId());
            updateResults.setWins(finalWinner);
            updateResults.setLosses(loser);

            session.save(updateResults);
            session.getTransaction().commit();
        }
    }

    /**
     * This method is used to update number of losses in the database,
     * the method creates a List that contains the active users results in the database.
     * it then checks if there is an existing entry in the databse.
     * If there is an existing entry it updates the losses and if it's empty it creates a new entry in the database.
     */

    public void updateLoss() {

        List<Results> currentResults = session.createQuery(
                "from Results where user_id = '" + choice.getUserId() + "'")
                .getResultList();

        if (currentResults.size() == 0) {

            Results updateLosses = new Results();
            updateLosses.setUserId(choice.getUserId());
            updateLosses.setLosses(1);

            session.save(updateLosses);
            session.getTransaction().commit();

        } else {

            Results updateResults = (Results) session.createQuery(
                    "from Results where user_id = '" + choice.getUserId() + "'").getSingleResult();

            int loser = currentResults.get(0).getLosses();
            int winner = currentResults.get(0).getWins();
            int finalLoser = loser + 1;

            updateResults.setUserId(choice.getUserId());
            updateResults.setLosses(finalLoser);
            updateResults.setWins(winner);

            session.save(updateResults);
            session.getTransaction().commit();
        }
    }
}
