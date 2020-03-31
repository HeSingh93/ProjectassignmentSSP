package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

        choiceMade(mouseEvent, rock);

    }

    public void paperClicked(MouseEvent mouseEvent) {

        choiceMade(mouseEvent, paper);

    }

    public void scissorsClicked(MouseEvent mouseEvent) {

        choiceMade(mouseEvent, scissors);

    }

    public void choiceMade(MouseEvent mouseEvent, int myChoice) {

        try {
            session.beginTransaction();
            choice.setChoice(myChoice);
            session.update(choice);
            session.getTransaction().commit();

            replaceSceneToLoadingScreen(
                    HelperMethods.loadScreenWindowFXML,
                    mouseEvent,
                    token,
                    choice
            );

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

    static void replaceSceneToLoadingScreen(String fxmlPath, MouseEvent mouseEvent, Token token, Choice choice) throws IOException {
        Stage stage = (Stage) ((Node) mouseEvent.getSource())
                .getScene()
                .getWindow();

        FXMLLoader loader = HelperMethods.getLoader(fxmlPath);
        Parent root = loader.load();
        GenericController controller = loader.getController();
        controller.setToken(token);
        controller.setChoice(choice);
        Scene scene = new Scene(root);
        stage.setTitle(HelperMethods.gameRockPaperScissorTitle);
        stage.setScene(scene);
        stage.toFront();
        stage.show();
        controller.postInitialize();
    }
}
