package com.example.ssp;

public class GameEngine {
    private int rock = 1;
    private int paper = 2;
    private int scissors = 3;
    private int random;

    public GameEngine(int rock, int paper, int scissors, int random) {
        this.rock = rock;
        this.paper = paper;
        this.scissors = scissors;
        this.random = random;
    }

    public int getRock() {
        return rock;
    }

    public void setRock(int rock) {
        this.rock = rock;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    public int getScissors() {
        return scissors;
    }

    public void setScissors(int scissors) {
        this.scissors = scissors;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public void generateRandom(){
        int x = 1 + (int) (Math.random() * 3);
        x = random;
    }



}
