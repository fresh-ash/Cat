package com.mygdx.game.model.utils.pill_states;

import com.mygdx.game.model.entity.Pill;

public class UnshowedPillState extends PillState {


    public UnshowedPillState(Pill pill) {
        super(pill);
    }

    @Override
    public void turn() {
        if (pill.getBoard().getObjectManager().getFreeElements().isEmpty()){
            pill.getBoard().getObjectManager().addToRenderedObj(pill.getA());
            pill.getBoard().getObjectManager().addToRenderedObj(pill.getB());
            pill.changeState(new FirstPillState(pill));
        }
    }
}
