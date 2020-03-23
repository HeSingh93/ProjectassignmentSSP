package com.example.ssp.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "public.friendslist")
public class FriendsList implements Serializable {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "friend_id")
    private int FriendId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "token_token_id")
    private Token token;

    public FriendsList() {
        //No arg constructor
    }

    public FriendsList(int userId, int friendId) {
        this.userId = userId;
        FriendId = friendId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return FriendId;
    }

    public void setFriendId(int friendId) {
        FriendId = friendId;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "FriendsList{" +
                "userId=" + userId +
                ", FriendId=" + FriendId +
                ", token=" + token +
                '}';
    }
}
