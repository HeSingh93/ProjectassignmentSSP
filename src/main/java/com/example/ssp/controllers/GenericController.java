package com.example.ssp.controllers;

import com.example.ssp.models.Token;

public abstract class GenericController {
    Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

}