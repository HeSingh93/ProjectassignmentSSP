package com.example.ssp.controllers;

import com.example.ssp.models.Choice;
import com.example.ssp.models.Token;

/**
 * This controller is used to pass around two objects, Token and Choice.
 */
public abstract class GenericController {
    Token token;
    Choice choice;
    int cpuChoice, playerChoice;

    public int getCpuChoice() {
        return cpuChoice;
    }

    public void setCpuChoice(int cpuChoice) {
        this.cpuChoice = cpuChoice;
    }

    public int getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice(int playerChoice) {
        this.playerChoice = playerChoice;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    public void postInitialize() {
        // Does nothing by default
    }


}