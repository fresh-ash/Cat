package com.mygdx.game.model.utils.states;

import com.mygdx.game.model.Board;
import com.mygdx.game.model.entity.Pill;

public abstract class PillState {

    Board board;
    Pill pill;

    public PillState(Board board){
        this.board = board;
        this.pill = this.board.getCurrentpPill();
    }

    public abstract void turn();


}
