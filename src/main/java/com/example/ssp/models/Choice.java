package com.example.ssp.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "public.sspchoice")
public class Choice implements Serializable {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Id
    @Column(name = "friend_id")
    private int friendId;

    @Column(name = "choice")
    private int choice;

    public Choice() {
        // No-arg constructor
    }

    public Choice(int userId, int friendId, int choice) {
        this.userId = userId;
        this.friendId = friendId;
        this.choice = choice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "userId=" + userId +
                ", friendId=" + friendId +
                ", choice=" + choice +
                '}';
    }
}
