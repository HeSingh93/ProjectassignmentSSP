package com.example.ssp.models;

import javax.persistence.*;

/**
 * This class is an entity of the table "token" in the database.
 * The values in this class is mapped to the corresponding values in the database.
 * Whenever we want to make an entry in the database, we create an object of this class, enter our values and then
 * pass the object to the database.
 */

@Entity
@Table(name = "public.token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
