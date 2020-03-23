package com.example.ssp.controllers;

import com.example.ssp.models.Token;
import com.example.ssp.models.User;

public abstract class GenericController {
    private String valueOfToken;
    User user;
    Token token;


    public void inheritSettings(GenericController controller, Token tokenBruh) {
        user = controller.user;
        token = controller.token;

    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getValueOfToken() {
        return valueOfToken;
    }

    public void setValueOfToken(String valueOfToken) {
        this.valueOfToken = valueOfToken;
    }

}