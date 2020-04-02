package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class GameRockPaperScissorVersus extends GenericController {

    public ImageView rockView;
    public ImageView paperView;
    public ImageView scissorView;
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

    /**
     * When rock is clicked, we call the function choiceMade();
     * it sets myChoice to 1.
     * @param mouseEvent
     * @throws IOException
     */
    public void rockClicked(MouseEvent mouseEvent) throws IOException {

        choiceMade(mouseEvent, rock);

    }
    /**
     * When paper is clicked, we call the function choiceMade();
     * it sets myChoice to 2.
     * @param mouseEvent
     * @throws IOException
     */
    public void paperClicked(MouseEvent mouseEvent) {

        choiceMade(mouseEvent, paper);

    }
    /**
     * When scissors is clicked, we call the function choiceMade();
     * it sets myChoice to 3.
     * @param mouseEvent
     * @throws IOException
     */
    public void scissorsClicked(MouseEvent mouseEvent) {

        choiceMade(mouseEvent, scissors);

    }

    /**
     * This method is used to set the choice when the user selects rock, paper or scissors.
     * It replaces the scene with a loadScreen and passes the users token and the choice made.
     * The choice is saved to the database that later on will be used to calculate the winner.
     *
     * @param mouseEvent
     * @param myChoice an integer with mapped values (rock = 1, paper = 2, scissors = 3)
     */
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

    /**
     * When the mainMenuButton is clicked, we replace the scene with the mainWindow
     * as well as passing our token-object to the next scene.
     * @param mouseEvent
     * @throws IOException
     */
    public void mainMenuBtnClicked(MouseEvent mouseEvent) throws IOException {
        HelperMethods.replaceSceneLoggedIn(
                HelperMethods.mainWindowFXML,
                mouseEvent,
                token
        );
    }

    /**
     * This method is used to pass our token and choice object to the next scene.
     * It is similar to the normal replace scene method that we use from the HelperMethods class.
     * But controller.postInitialize(); is set to run later.
     * @param fxmlPath loads the relevant scene.
     * @param mouseEvent button clicked.
     * @param token the object that is passed to the next screen.
     * @param choice the object that is passed to the next screen.
     * @throws IOException
     */
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
