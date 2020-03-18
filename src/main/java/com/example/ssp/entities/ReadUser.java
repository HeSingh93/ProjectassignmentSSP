package com.example.ssp.entities;

import com.example.ssp.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadUser {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();

        try {
            User tempUser = new User("Test", "testicle");

            //Start a transaction
            session.beginTransaction();

            //Save the object
            System.out.println("Saving user");
            System.out.println(tempUser);
            session.save(tempUser);

            // Commit transaction
            session.getTransaction().commit();

            //READING OBJECTS

            //Find out the users primary key
            System.out.println("Primary key that has been generated: " + tempUser.getUserId());

            //Get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //Retrieve user based on primary key
            System.out.println("\nGetting user with id: " + tempUser.getUserId());
            User myUser = session.get(User.class, tempUser.getUserId());

            System.out.println("Get complete: " + myUser);

            //Commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();
        }


    }
}
