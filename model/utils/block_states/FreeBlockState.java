package com.mygdx.game.model.utils.block_states;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Block;
import com.mygdx.game.model.utils.Point;

public class FreeBlockState extends BaseBlockState {


    public FreeBlockState(Block block) {
        super(block);
    }

    @Override
    public void updateState() {
        Point point = new Point(block.getCoordinates());
        point.setY(point.getY() - 1);
        if (!block.getBoard().isCellEmpty(point)){
            block.getBoard().setBoardElement(block);
            block.getBoard().getObjectManager().delFromFreeElements(block);
            block.setState(new RelativeBlockState(block));
        }
    }
}

