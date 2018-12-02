package com.mygdx.game.model.utils.block_states;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Block;
import com.mygdx.game.model.utils.Point;

public class RelativeBlockState extends BaseBlockState {


    public RelativeBlockState(Block block) {
        super(block);
    }

    @Override
    public void updateState() {
        Point point = new Point(block.getCoordinates());
        point.setY(point.getY() - 1);
        if (block.isFree() && block.getBoard().isCellEmpty(point)){
            block.getBoard().getObjectManager().addToFreeElements(block);
            block.getBoard().setBoardElementAsEmpty(block);
            block.setCoordinates(point);
            block.setState(new FreeBlockState(block));
        }
    }
}
