package com.mygdx.game.model.utils.states;

import com.mygdx.game.model.Board;
import com.mygdx.game.model.utils.Point;


public class FirstPillState extends PillState {


    public FirstPillState(Board board) {
        super(board);
    }

    @Override
    public void turn() {
        Point point = pill.getA().getCoordinates();
        point.setY(point.getY() + 1);
        if (board.isCellEmpty(point)){
            pill.getB().setCoordinates(point);
            pill.changeState(new SecondPillState(board));
        }

    }


}
