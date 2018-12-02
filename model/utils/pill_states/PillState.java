package com.mygdx.game.model.utils.pill_states;

import com.mygdx.game.model.Board;
import com.mygdx.game.model.entity.Pill;

public abstract class PillState {

    Pill pill;

    public PillState(Pill pill){
        this.pill = pill;
    }

    public abstract void turn();


}
