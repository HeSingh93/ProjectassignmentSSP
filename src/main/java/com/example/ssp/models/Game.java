package com.example.ssp.models;

import com.example.ssp.controllers.HelperMethods;

import java.util.Random;

public class Game extends HelperMethods {

    public static final int rock = 0;
    public static final int paper = 1;
    public static final int scissors = 2;

    public static final Integer[] ssp = new Integer[3];

    public static void addtoArray() {
        ssp[0] = rock;
        ssp[1] = paper;
        ssp[2] = scissors;
    }

    public static String randomlySelect() {

        addtoArray();

        int random = new Random().nextInt(ssp.length);

        if (ssp[random] == rock) {
           return rockViewFXML;
        }

        if (ssp[random] == paper) {
            return paperViewFXML;
        }

        if (ssp[random] == scissors) {
            return scissorViewFXML;
        }
        return randomlySelect();
    }
}
