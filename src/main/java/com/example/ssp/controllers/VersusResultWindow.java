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

import java.io.IOException;
import java.util.List;

public class VersusResultWindow extends GenericController {
    public HBox middleBox;
    public VBox botBox;
    public HBox topBox;
    public ImageView userImage;
    public ImageView opponentImage;
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
            User yourId = (User) session.createQuery(
                    "from User where user_id = '" + choice.getFriendId() + "'")
                    .getSingleResult();
            Choice opponentChoice = (Choice) session.createQuery(
                    "from Choice where user_id = '" + choice.getFriendId()
                            + "' and friend_id = '" + myId.getUserId() + "'")
                    .getSingleResult();

            myName = myId.getUserName();
            yourName = yourId.getUserName();

            friendName.setText(yourName);
            userName.setText(myName);
            System.out.println(yourName);
            System.out.println(myName);


            //Methods to set images
            setChoiceImage(choice, userImage);

            //Thread 1
            //sleep(1000)
            setChoiceImage(opponentChoice, opponentImage);


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

        List<Results> currentResults = session.createQuery(
                "from Results where user_id = '" + choice.getUserId() + "'").getResultList();

        Results updateLosses = new Results();

        if (currentResults.size() == 0) {
            updateLosses.setLosses(1);
            updateLosses.setUserId(choice.getUserId());
        } else {

            int loser = (currentResults.get(0).getLosses());
            int finalLoser = loser + 1;

            updateLosses.setUserId(choice.getUserId());
            updateLosses.setLosses(finalLoser);
        }

        session.save(updateLosses);
        session.getTransaction().commit();
    }
}
