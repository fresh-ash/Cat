package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.interfaces.Movable;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Color;
import com.mygdx.game.model.utils.Point;

public class Block extends BaseObject {

    boolean isFree;
    Block relativeBlock;
    String textureName;

    public Block(Point coordinates, boolean isFree) {
        super(coordinates);
        this.textureName = defineTexture();
        this.isFree = isFree;
    }

    @Override
    public void update(Board board) {
        Gdx.app.log("Coordinates before update", getCoordinates().toString());
        if(isFree && (board.isInFreeElementsList(this))){
            Point nextCell = getNextPoint();
            if(board.isCellEmpty(nextCell)){
                this.setCoordinates(nextCell);
            }
            else {
                board.setBoardElement(this);
                board.deleteFreeElement(this);
            }


        }
    }

    Point getNextPoint(){
        return new Point(this.coordinates.getX(), this.coordinates.getY() - 1);
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree() {
        isFree = true;
    }

    public void setFree(Board board){
        isFree = true;
        board.addFreeElement(this);
    }

    @Override
    public void setDeleted(boolean deleted) {
        super.setDeleted(deleted);
        this.relativeBlock.setFree();
        Gdx.app.log("Info:" , "Set relative block free!");
    }

    public String getTexture() {
        return textureName;
    }

    public void setTexture(String textureName) {
        this.textureName = textureName;
    }

    public String defineTexture(){
        if(color == Color.BLUE){
            return "blueBlock.png";
        }
        else if (color == Color.RED){
            return "redBlock.png";
        }
        else {
            return "yellowBlock.png";
        }
    }

    public Block getRelativeBlock() {
        return relativeBlock;
    }

    public void setRelativeBlock(Block relativeBlock) {
        this.relativeBlock = relativeBlock;
    }
}
