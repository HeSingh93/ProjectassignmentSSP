package com.example.ssp;

/**
 * A rock, paper, scissor game that is connected to a cloud database (Heroku).
 * The game has three gamemodes, versus, cpu and play.
 * The versus mode is used to play against friends.
 * The cpu mode is used to play against the computer.
 * And the play mode is used to settle disputes between you and your friend.
 * The app uses Hibernate as a java persistence API.
 * @author Niclas Pettersson, Herman Singh
 * @version 1.0
 * @since 2020-03-01
 */

public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}
