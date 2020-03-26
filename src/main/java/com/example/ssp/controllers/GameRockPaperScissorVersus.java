package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.IOException;

public class GameRockPaperScissorVersus extends GenericController {

    public ImageView rockView;
    public ImageView paperView;
    public ImageView scissorView;
    public VBox midBox;
    public Label pickMove;
    public VBox replacementBox;
    int rock = 1;
    int paper = 2;
    int scissors = 3;

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(FriendsList.class)
            .addAnnotatedClass(Token.class)
            .addAnnotatedClass(Choice.class)
            .buildSessionFactory();

    Session session = factory.getCurrentSession();

    public void rockClicked(MouseEvent mouseEvent) throws IOException {
        session.beginTransaction();
        try {
            System.out.println(choice.getFriendId());
            System.out.println(choice.getUserId());
            choice.setChoice(rock);
            session.update(choice);
            session.getTransaction().commit();
            
            midBox.setVisible(false);

   //         rockView.setVisible(false);
     //       paperView.setVisible(false);
       //     scissorView.setVisible(false);


            Button mainMenuBtn = new Button ("MAIN MENU");

            Label rockChosen = new Label ("You have chosen, rock");

            pickMove.setText("VS");

            rockView.setFitHeight(250);
            rockView.setFitWidth(250);
            rockView.setLayoutX(20);

            replacementBox.setVisible(true);
            replacementBox.getChildren().addAll(
                    pickMove,
                    rockChosen,
                    rockView,
                    mainMenuBtn
            );

         //   midBox.getChildren().addAll(rockChosen, rockView, mainMenuBtn);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

    public void paperClicked(MouseEvent mouseEvent) {
        session.beginTransaction();
        try {
            System.out.println(choice.getFriendId());
            System.out.println(choice.getUserId());
            choice.setChoice(paper);
            session.update(choice);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

    public void scissorsClicked(MouseEvent mouseEvent) {
        session.beginTransaction();
        try {
            System.out.println(choice.getFriendId());
            System.out.println(choice.getUserId());
            choice.setChoice(scissors);
            session.update(choice);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }

    }

    public void mainMenuBtnClicked(MouseEvent mouseEvent) {
    }
}
