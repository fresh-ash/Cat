package com.mygdx.game.model.utils.pill_states;

import com.mygdx.game.model.entity.Pill;
import com.mygdx.game.model.utils.Point;


public class FirstPillState extends PillState{

    public FirstPillState(Pill pill) {
        super(pill);

    }

    @Override
    public void turn() {
        Point point = new Point(pill.getA().getCoordinates());
        point.setY(point.getY() + 1);
        if (pill.getBoard().isCellEmpty(point)){
            pill.getB().setCoordinates(point);
            pill.changeState(new SecondPillState(pill));
        }
    }
}
