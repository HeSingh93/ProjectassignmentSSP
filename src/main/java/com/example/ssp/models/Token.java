package com.example.ssp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.security.SecureRandom;

@Entity
@Table(name = "public.token")
public class Token implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="token_id")
    private int tokenId;

    @Column(name="value")
    private String value;

    public Token(){
        // no-arg constructor
    }

    public Token(String value) {
        this.value = value;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId=" + tokenId +
                ", value='" + value + '\'' +
                '}';
    }
}
