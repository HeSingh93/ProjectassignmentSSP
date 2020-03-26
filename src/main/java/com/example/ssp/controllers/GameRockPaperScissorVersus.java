package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GameRockPaperScissorVersus extends GenericController {

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

    public void rockClicked(MouseEvent mouseEvent) {
        session.beginTransaction();
        try {
            System.out.println(choice.getFriendId());
            System.out.println(choice.getUserId());
            choice.setChoice(rock);
            session.update(choice);
            session.getTransaction().commit();

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
}
