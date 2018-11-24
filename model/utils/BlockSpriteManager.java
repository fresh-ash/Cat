package com.mygdx.game.model.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.Color;
import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Block;

public class BlockSpriteManager extends Sprite {

    Sprite red, blue, yellow, redVirus, blueVirus, yellowVirus;

    public BlockSpriteManager(Sprite red, Sprite blue, Sprite yellow, Sprite redVirus, Sprite blueVirus, Sprite yellowVirus) {
        this.red = red;
        this.blue = blue;
        this.yellow = yellow;
        this.redVirus = redVirus;
        this.blueVirus = blueVirus;
        this.yellowVirus = yellowVirus;
    }

    public Sprite update(BaseObject object){
        int x = object.getCoordinates().getX();
        int y = object.getCoordinates().getY();
        boolean flug = object instanceof Block;

        if (object.getColor() == Color.BLUE){
            if (flug) {
                blue.setBounds(x, y, 1 , 1);
                return blue;
            }
            else {
                blueVirus.setBounds(x,y,1,1);
                return blueVirus;
            }
        }
        else if (object.getColor() == Color.RED){
            if (flug){
                red.setBounds(x, y, 1 , 1);
                return red;
            }
            else {
                redVirus.setBounds(x,y,1,1);
                return redVirus;
            }
        }
        else {
            if(flug){
                yellow.setBounds(x, y, 1 , 1);
                return yellow;
            }
            else {
                yellowVirus.setBounds(x,y,1,1);
                return yellowVirus;
            }
        }
    }


    public Sprite getRed() {
        return red;
    }

    public void setRed(Sprite red) {
        this.red = red;
    }

    public Sprite getBlue() {
        return blue;
    }

    public void setBlue(Sprite blue) {
        this.blue = blue;
    }

    public Sprite getYellow() {
        return yellow;
    }

    public void setYellow(Sprite yellow) {
        this.yellow = yellow;
    }
}
