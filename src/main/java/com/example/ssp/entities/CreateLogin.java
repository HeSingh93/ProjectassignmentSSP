package com.example.ssp.entities;

import com.example.ssp.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateLogin {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            User storeUser = new User();

            session.beginTransaction();

            session.save(storeUser);

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }


}
