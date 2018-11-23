package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.interfaces.Updatable;
import com.mygdx.game.model.Color;
import com.mygdx.game.model.utils.Point;

public abstract class BaseObject implements Updatable {

    Point coordinates;
    Color color;
    boolean isDeleted;

    public BaseObject(Point coordinates) {
        this.coordinates = coordinates;
        this.color = Color.getRandomColor();
        this.isDeleted  = false;
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

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
        Gdx.app.log("call SetDeleted():" , "I'm deleted!" );
    }
}
