package com.mygdx.game.model.entity;

import com.mygdx.game.model.Board;
import com.mygdx.game.model.Color;
import com.mygdx.game.model.utils.Point;

public class Virus extends BaseObject {

    boolean isAngree;
    String fullImageName;

    public Virus(Point coordinates, Board board) {
        super(coordinates, board);
        this.isAngree = false;
        this.fullImageName = getImageName() + "Virus";
    }



    @Override
    public void update() {
        if (isDeleted){
            this.board.getObjectManager().addToWillBeDeletedObj(this);
            this.board.getObjectManager().delFromRenderedObj(this);
        }
        else updateGraphic();
    }

    @Override
    public void updateGraphic() {

        if (isAngree){
             this.setFullImageName(this.getImageName() + "VirusAngree");
             this.setAngree(false);
        }
        else {

            this.setFullImageName(this.getImageName() + "Virus");
            this.setAngree(true);
        }

        if (isDeleted){
            this.setFullImageName(this.getImageName() + "Deleted");
        }
    }

    public boolean isAngree() {
        return isAngree;
    }

    public void setAngree(boolean angree) {
        isAngree = angree;
    }

    @Override
    public String getFullImageName() {
        return this.fullImageName;
    }

    public void setFullImageName(String fullImageName) {
        this.fullImageName = fullImageName;
    }
}
