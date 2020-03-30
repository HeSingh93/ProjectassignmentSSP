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
import org.hibernate.query.Query;

import java.io.IOException;
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

            Query<Choice> query = session.createQuery("from Choice where user_id = :userId and friend_id = :friendId");
            query.setParameter("userId", choice.getFriendId());

            Choice opponentChoice = (Choice)session.createQuery(
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


            //Methods to set images
            setChoiceImage(choice, userImage);


            Thread bruh = new Thread(() -> {

                while (opponentChoice.getChoice() == 0) {
                    Session session = factory.getCurrentSession();
                    session.beginTransaction();
                    session.refresh(opponentChoice);
                    setChoiceImage((Choice) opponentChoice, opponentImage);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    session.close();
                }
            });

            bruh.start();
            setChoiceImage((Choice) opponentChoice, opponentImage);


            if (choice.getChoice() == 1 && opponentChoice.getChoice() == 1) {
                resultTextLabel.setText("TIE!");

            } else if (choice.getChoice() == 1 && opponentChoice.getChoice() == 2) {
                resultTextLabel.setText(yourName + " Wins!");
                updateLoss();

            } else if (choice.getChoice() == 1 && opponentChoice.getChoice() == 3) {
                resultTextLabel.setText(myName + " Wins!");
                updateWins();

            } else if (choice.getChoice() == 2 && opponentChoice.getChoice() == 1) {
                resultTextLabel.setText(myName + " Wins!");
                updateWins();

            } else if (choice.getChoice() == 2 && opponentChoice.getChoice() == 2) {
                resultTextLabel.setText("TIE!");

            } else if (choice.getChoice() == 2 && opponentChoice.getChoice() == 3) {
                resultTextLabel.setText(yourName + " Wins!");
                updateLoss();

            } else if (choice.getChoice() == 3 && opponentChoice.getChoice() == 1) {
                resultTextLabel.setText(yourName + " Wins!");
                updateLoss();

            } else if (choice.getChoice() == 3 && opponentChoice.getChoice() == 2) {
                resultTextLabel.setText(myName + " Wins!");
                updateWins();

            } else if (choice.getChoice() == 3 && opponentChoice.getChoice() == 3) {
                resultTextLabel.setText("TIE!");

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }


    public void mainMenuBtnClicked(MouseEvent mouseEvent) throws IOException {

        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.mainWindowFXML,
                mouseEvent,
                token
        );
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

    public void updateWins() {

        Results currentResults = (Results) session.createQuery(
                "from Results where user_id = '" + choice.getUserId() + "'")
                .getSingleResult();


        if (currentResults == null) {

            Results updateWins = new Results();
            updateWins.setUserId(choice.getUserId());
            updateWins.setWins(1);

            session.save(updateWins);

        } else {

            int winner = currentResults.getWins();
            int finalWinner = winner + 1;


            currentResults.setUserId(choice.getUserId());
            currentResults.setWins(finalWinner);
            session.update(currentResults);

        }

        session.getTransaction().commit();
    }

    public void updateLoss() {

        Results currentResults = (Results) session.createQuery(
                "from Results where user_id = '" + choice.getUserId() + "'").getResultList();

        if (currentResults == null) {

            Results updateLosses = new Results();
            updateLosses.setLosses(1);
            updateLosses.setUserId(choice.getUserId());

            session.save(updateLosses);

        } else {

            int loser = (currentResults.getLosses());
            int finalLoser = loser + 1;

            currentResults.setUserId(choice.getUserId());
            currentResults.setLosses(finalLoser);
            session.save(currentResults);
        }


        session.getTransaction().commit();
    }
}
