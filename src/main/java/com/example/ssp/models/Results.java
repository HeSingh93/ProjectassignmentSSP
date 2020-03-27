package com.example.ssp.models;
import javax.persistence.*;

@Entity
@Table(name = "public.results")
public class Results {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "wins")
    private int wins;

    @Column(name = "losses")
    private int losses;

    public Results(int userId, int wins, int losses) {
        this.userId = userId;
        this.wins = wins;
        this.losses = losses;
    }

    public Results() {
        //No-arg constructor
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    @Override
    public String toString() {
        return "Results{" +
                "userId=" + userId +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}
