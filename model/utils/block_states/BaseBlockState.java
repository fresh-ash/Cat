package com.mygdx.game.model.utils.block_states;

import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Block;

public abstract class BaseBlockState {

    Block block;


    public BaseBlockState(Block block){
        this.block = block;
    }


    public abstract void updateState();

}
