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



    public FriendsList() {
        //No arg constructor
    }

    public FriendsList(int userId, int friendId) {
        userId = userId;
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



    @Override
    public String toString() {
        return "FriendsList{" +
                "userId=" + userId +
                ", FriendId=" + FriendId +
                +
                '}';
    }
}
