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

    public static void randomlySelect() {
        addtoArray();

        int random = new Random().nextInt(ssp.length);

        if (ssp[random] == rock) {
            HelperMethods.getLoader(HelperMethods.rockViewFXML);
            return;
        }

        if (ssp[random] == paper) {
            HelperMethods.getLoader(HelperMethods.paperViewFXML);
            return;
        }

        if (ssp[random] == scissors) {
            HelperMethods.getLoader(HelperMethods.scissorViewFXML);
        }
    }


    }
