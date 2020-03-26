package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import com.example.ssp.models.FriendsList;
import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import javafx.scene.control.Label;
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
    public Label friendName;
    public Label userName;

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(User.class)
            .addAnnotatedClass(FriendsList.class)
            .addAnnotatedClass(Token.class)
            .addAnnotatedClass(Choice.class)
            .buildSessionFactory();

    Session session = factory.getCurrentSession();



     /*   try{
            session.beginTransaction();

            List<User> currentUser = session.createQuery("from User where token_token_id = '" + token.getTokenId() + "'").getResultList();

            System.out.println(currentUser.get(0).getUserName() + currentUser.get(0).getUserId());

            List<Choice> currentFriend = session.createQuery("from Choice where user_id = '" + currentUser.get(0).getUserId() + "' and friend_id = " + choice.getFriendId()).getResultList();

            System.out.println(currentFriend.get(0).getFriendId());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
            factory.close();
        }
    }
*/
    public void mainMenuBtnClicked(MouseEvent mouseEvent) {

    }


}
