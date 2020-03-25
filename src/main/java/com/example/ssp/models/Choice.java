package com.example.ssp.models;

import javax.persistence.*;

@Entity
@Table(name = "choice")
public class Choice {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "choice")
    private int choice;


    public Choice() {
        // No-arg constructor
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", choice=" + choice +
                '}';
    }

}
