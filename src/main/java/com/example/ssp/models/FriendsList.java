package com.example.ssp.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class is an entity of the table "friendslist" in the database.
 * The values in this class is mapped to the corresponding values in the database.
 * Whenever we want to make an entry in the database, we create an object of this class, enter our values and then
 * pass the object to the database.
 */

@Entity
@Table(name = "public.friendslist")
public class FriendsList implements Serializable {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "friend_id")
    private int FriendId;

    @Column(name = "friends_name")
    private String friendsName;



    public FriendsList() {
        //No arg constructor
    }

    public FriendsList(int userId, int friendId, String friendsName) {
        userId = userId;
        FriendId = friendId;
        friendsName = friendsName;
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

    public String getFriendsName() { return friendsName; }

    public void setFriendsName(String friendsName) { this.friendsName = friendsName; }



    @Override
    public String toString() {
        return "FriendsList{" +
                "userId=" + userId +
                ", FriendId=" + FriendId +
                ", FriendsName=" + friendsName +
                '}';
    }
}
