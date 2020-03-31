package com.example.ssp.controllers;

import com.example.ssp.models.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class VersusResultWindow extends GenericController {
    public HBox middleBox;
    public VBox botBox;
    public HBox topBox;
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

            System.out.println(opponentChoice);

            myName = myId.getUserName();
            yourName = yourId.getUserName();

            friendName.setText(yourName);
            userName.setText(myName);
            System.out.println(yourName);
            System.out.println(myName);

            //Methods to set images and text
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
