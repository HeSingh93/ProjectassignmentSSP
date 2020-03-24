package com.example.ssp.models;

import javax.persistence.*;
// More like, bruh
@Entity
@Table(name = "public.user")
public class SignUpUser {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
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