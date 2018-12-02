package com.mygdx.game.model;

import java.util.Random;

public enum Color {
    red,
    blue,
    yellow;

    private static final Color[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random r = new Random();

    public static Color getRandomColor(){
        return VALUES[r.nextInt(SIZE)];
    }
}
