package com.mygdx.game.model.utils;

import java.util.Random;

public class Point {

    private int x, y;

    public Point() {

    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static Point getRandomPoint(int maxX, int maxY){
        Random random = new Random();
        Point point = new Point(random.nextInt(maxX),random.nextInt(maxY));
        return point;
    }

    @Override
    public String toString() {
        return new String("Coordinates:   X: " + getX() + "Y: " + getY());
    }
}
