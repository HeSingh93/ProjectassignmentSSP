package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
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
    public ImageView userChoice;
    public ImageView opponentChoice;
    public Label friendName, userName, resultTextLabel;
    public String myName, yourName;

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(FriendsList.class)
            .addAnnotatedClass(Token.class)
            .addAnnotatedClass(Choice.class)
            .buildSessionFactory();

    Session session = factory.getCurrentSession();


    @Override
    public void postInitialize() {

        session.beginTransaction();

        List<User> myId = session.createQuery(
                "from User where token_token_id = '" + token.getTokenId() + "'").getResultList();
        List<User> yourId = session.createQuery(
                "from User where user_id = '" + choice.getFriendId() + "'").getResultList();
         List<Choice> friendChoice = session.createQuery(
                "from Choice where user_id = '" + choice.getFriendId() +
                        "' and friend_id = '" + myId.get(0).getUserId() + "'").getResultList();


        myName = myId.get(0).getUserName();
        yourName = yourId.get(0).getUserName();

        friendName.setText(yourName);
        userName.setText(myName);
        System.out.println(yourName);
        System.out.println(myName);

        switch (choice.getChoice()) {
            case 1:
                Image rock = new Image(HelperMethods.getResAsStream("images/rock.png"));
                userChoice.setImage(rock);
                break;

            case 2:
                Image paper = new Image(HelperMethods.getResAsStream("images/paper.png"));
                userChoice.setImage(paper);
                break;

            case 3:
                Image scissor = new Image(HelperMethods.getResAsStream("images/scissors.png"));
                userChoice.setImage(scissor);
                break;
        }

        //noinspection InfiniteLoopStatement
        while (friendChoice.isEmpty()) {
            List<Choice> friendFinalChoice = session.createQuery(
                    "from Choice where user_id = '" + choice.getFriendId() +
                            "' and friend_id = '" + myId.get(0).getUserId() + "'").getResultList();

            switch (friendFinalChoice.get(0).getChoice()) {
                case 1:
                    Image rock = new Image(HelperMethods.getResAsStream("images/rock.png"));
                    opponentChoice.setImage(rock);
                    break;

                case 2:
                    Image paper = new Image(HelperMethods.getResAsStream("images/paper.png"));
                    opponentChoice.setImage(paper);
                    break;

                case 3:
                    Image scissor = new Image(HelperMethods.getResAsStream("images/scissors.png"));
                    opponentChoice.setImage(scissor);
                    break;
            }
        }

        if (choice.getChoice() == 1 && friendChoice.get(0).getChoice() == 2) {
            resultTextLabel.setText(yourName + " Wins!");
            //friend wins
        } else if (choice.getChoice() == 1 && friendChoice.get(0).getChoice() == 3) {
            resultTextLabel.setText(myName + " Wins!");
            //user wins
        } else if (choice.getChoice() == 2 && friendChoice.get(0).getChoice() == 1) {
            resultTextLabel.setText(myName + " Wins!");
            //user wins
        } else if (choice.getChoice() == 2 && friendChoice.get(0).getChoice() == 3) {
            resultTextLabel.setText(yourName + " Wins!");
            //friend wins
        } else if (choice.getChoice() == 3 && friendChoice.get(0).getChoice() == 1) {
            resultTextLabel.setText(yourName + " Wins!");
            //friend wins
        } else if (choice.getChoice() == 3 && friendChoice.get(0).getChoice() == 2) {
            resultTextLabel.setText(myName + " Wins!");
            //user wins
        } else {
            resultTextLabel.setText("TIE!");
        }

    }

    public void mainMenuBtnClicked(MouseEvent mouseEvent) {

    }


}
