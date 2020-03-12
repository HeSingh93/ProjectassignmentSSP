package com.example.ssp.controllers;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Base64;

public class SQLHelper {

    public static String userName;
    public static String password;
    public static final SecureRandom secureRandom = new SecureRandom();
    public static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    Connection connection = null;
    Statement processSqlStatement = null;


    public SQLHelper(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        SQLHelper.userName = userName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SQLHelper.password = password;
    }

    public void startUp() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SSP",
                    "postgres",
                    "TheLoveForMyHomies");
            connection.setAutoCommit(false);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);

    }

    public void login(String userName, String password) {
        PreparedStatement state = null;
        try {
            state = connection.prepareStatement("SELECT * FROM \"user\" where \"user_name\" = ? and \"password\" = ?;");
            state.setString(1, userName);
            state.setString(2, password);
            ResultSet validUser = state.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertToken(String token, String userName, String password) {
        String input = String.format("INSERT INTO \"token\" (\"token_id\") VALUES (" + token + " ) " +
                "INNER JOIN user WHERE \"user_name\"='%s' and \"password\"='%s'", userName, password);
    }

    public void closeConnection() {
        try {
            processSqlStatement.close();
            connection.commit();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
    }
}

