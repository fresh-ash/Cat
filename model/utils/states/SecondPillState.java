package com.mygdx.game.model.utils.states;

import com.mygdx.game.model.Board;
import com.mygdx.game.model.entity.Block;
import com.mygdx.game.model.utils.Point;

public class SecondPillState extends PillState {


    public SecondPillState(Board board) {
        super(board);
    }

    @Override
    public void turn() {
        Point point = pill.getA().getCoordinates();
        point.setX(point.getX() + 1);
        Block c = pill.getA();
        if((point.getX() < board.getBoardWidth()) && board.isCellEmpty(point)){
            pill.setA(pill.getB());
            c.setCoordinates(point);
            pill.setB(c);
            pill.changeState(new FirstPillState(board));
        }

        else if ((point.getX() >= board.getBoardWidth()) || (!board.isCellEmpty(point))){
            point.setX(point.getX() - 2);
            if (board.isCellEmpty(point)){
                pill.getB().setCoordinates(point);
                pill.setA(pill.getB());
                pill.setB(c);
                pill.changeState(new FirstPillState(board));
            }
        }


    }
}
