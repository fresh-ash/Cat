package com.mygdx.game.model.utils.pill_states;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.entity.Block;
import com.mygdx.game.model.entity.Pill;
import com.mygdx.game.model.utils.Point;

public class SecondPillState extends PillState {


    public SecondPillState(Pill pill) {

        super(pill);
    }

    @Override
    public void turn() {
        Point point = new Point(pill.getA().getCoordinates());
        point.setX(point.getX() + 1);

        if( pill.getBoard().isCellEmpty(point)){
            changeBlocks();
            pill.getA().setCoordinates(pill.getB().getCoordinates());
            pill.getB().setCoordinates(point);
            pill.changeState(new FirstPillState(pill));

        }

        else {
            point.setX(point.getX() - 2);
            if (pill.getBoard().isCellEmpty(point)){
                changeBlocks();
                pill.getB().setCoordinates(pill.getA().getCoordinates());
                point.setY(point.getY() + 1);
                pill.getA().setCoordinates(point);
                pill.changeState(new FirstPillState(pill));
            }

        }

    }

    void changeBlocks(){
        Block c = pill.getA();
        pill.setA(pill.getB());
        pill.setB(c);
        Gdx.app.log("I:", pill.getA().getColor().toString() + pill.getB().getColor().toString());

    }


}
