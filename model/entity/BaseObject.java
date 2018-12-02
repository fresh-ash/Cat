package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.interfaces.Rendered;
import com.mygdx.game.interfaces.Updatable;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Color;
import com.mygdx.game.model.utils.Point;

public abstract class BaseObject implements Updatable, Rendered {

    Point coordinates;
    Color color;
    Board board;
    boolean isDeleted;
    String baseImageName;

    public BaseObject(Point coordinates, Board board) {
        this.coordinates = coordinates;
        this.color = Color.getRandomColor();
        this.board = board;
        this.isDeleted  = false;
        this.baseImageName = this.getImageName();
    }

    public String getImageName() {
        return this.color.toString();
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public boolean isDeleted() {
        return isDeleted;
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setImageName(String baseImageName) {
        this.baseImageName = baseImageName;
    }

    public abstract String getFullImageName();
    public abstract void setFullImageName(String s);


    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
        Gdx.app.log("call SetDeleted():" , "I'm deleted!" );
    }


}
