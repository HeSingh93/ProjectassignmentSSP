package com.example.ssp.models;

import com.example.ssp.controllers.GenericController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCurrentUser extends GenericController {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(FriendsList.class)
                .addAnnotatedClass(Token.class)
                .buildSessionFactory();


        try {

            String valueOfToken = "OaTeEwH-6psYjo2YqSLwbb3P7f1eLPDC";

            Session session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Getting user with current token: " + valueOfToken);

            User user = session.get(User.class, valueOfToken);

            System.out.println(user);

            session.getTransaction().commit();

            session.save(user);

            factory.close();
            session.close();

        } finally {
            factory.close();

        }
    }
}
