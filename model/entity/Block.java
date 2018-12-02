package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.interfaces.Movable;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Color;
import com.mygdx.game.model.utils.Point;
import com.mygdx.game.model.utils.block_states.BaseBlockState;
import com.mygdx.game.model.utils.block_states.RelativeBlockState;

public class Block extends BaseObject {

    boolean isFree;
    Block relativeBlock;
    String fullImageName;
    BaseBlockState state;


    public Block(Point coordinates, boolean isFree, Board board) {
        super(coordinates, board);
        this.isFree = isFree;
        this.fullImageName = this.getImageName() + "Block";
        this.state = new RelativeBlockState(this);
    }

    public Block(Block block){
        super(block.getCoordinates(), block.board);
        this.isFree = block.isFree();
        this.fullImageName = block.getFullImageName();
        this.relativeBlock = block.getRelativeBlock();
        this.state = block.state;

    }

    @Override
    public void update() {
        this.getState().updateState();
        updateGraphic();
    }








    public boolean isFree() {
        return isFree;
    }

    public void setFree() {
        isFree = true;
    }

    public void setFree(Board board){
        isFree = true;
    }


    public BaseBlockState getState() {
        return state;
    }

    public void setState(BaseBlockState state) {
        this.state = state;
    }

    @Override
    public void setDeleted(boolean deleted) {
        super.setDeleted(deleted);
        this.relativeBlock.setFree();
        Gdx.app.log("Info:" , "Set relative block free!");
    }

    public Block getRelativeBlock() {
        return relativeBlock;
    }

    public void setRelativeBlock(Block relativeBlock) {
        this.relativeBlock = relativeBlock;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public String getFullImageName() {
        return fullImageName;
    }

    public void setFullImageName(String fullImageName) {
        this.fullImageName = fullImageName;
    }


    @Override
    public void updateGraphic() {
        if (isDeleted){
            this.setFullImageName(getImageName() + "Deleted");
        }
    }
}
