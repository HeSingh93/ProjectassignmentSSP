package com.example.ssp.models;

import javax.persistence.*;

/**
 * This class is an entity of the table "user" in the database.
 * The values in this class is mapped to the corresponding values in the database.
 * Whenever we want to make an entry in the database, we create an object of this class, enter our values and then
 * pass the object to the database.
 *
 * We also have a foreign key to token in this entity.
 */

@Entity
@Table(name = "public.user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @OneToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "token_token_id")
    private Token token;

    public User(Token token, String userName, String password) {
        this.token = token;
        this.userName = userName;
        this.password = password;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public User(){
        // No-arg constructor, necessary
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
