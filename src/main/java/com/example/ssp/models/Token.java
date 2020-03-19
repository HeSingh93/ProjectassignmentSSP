package com.example.ssp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.security.SecureRandom;

@Entity
@Table(name = "\"Ssp\".Token")
public class Token implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="token_id")
    private int tokenId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="user_id")
    private int userId;

    @Column(name="value")
    private String value;


    public Token(){
        // no-arg constructor
    }

    public static void generateNewToken(){
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        String token = bytes.toString();
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
                ", userId=" + userId +
                ", value='" + value + '\'' +
                '}';
    }
}
