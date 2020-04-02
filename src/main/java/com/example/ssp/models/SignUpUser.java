package com.example.ssp.models;

import javax.persistence.*;

/**
 * This class is an entity of the table "user" in the database.
 * The values in this class is mapped to the corresponding values in the database.
 * Whenever we want to make an entry in the database, we create an object of this class, enter our values and then
 * pass the object to the database.
 */

@Entity
@Table(name = "public.user")
public class SignUpUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    public SignUpUser(){
        // No-arg constructor, necessary
    }

    public SignUpUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getSignUpUserId() {
        return userId;
    }

    public void setSignUpUserId(int userId) {
        this.userId = userId;
    }

    public String getSignUpUserName() {
        return userName;
    }

    public void setSignUpUserName(String userName) {
        this.userName = userName;
    }

    public String getSignUpPassword() {
        return password;
    }

    public void setSignUpPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}