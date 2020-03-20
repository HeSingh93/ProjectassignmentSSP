package com.example.ssp.entities;

import com.example.ssp.models.Token;
import com.example.ssp.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.security.SecureRandom;
import java.util.Base64;


public class testUserToken {

    public static final SecureRandom secureRandom = new SecureRandom();
    public static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    public static String token;

    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }


    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Token.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            token = generateToken();

            Token tempToken = new Token(token);

            User user = new User(tempToken, "hello", "boiiii");

            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

}
