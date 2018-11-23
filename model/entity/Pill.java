package com.mygdx.game.model.entity;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.interfaces.Controllable;
import com.mygdx.game.interfaces.Updatable;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Color;
import com.mygdx.game.model.command.BaseCommand;
import com.mygdx.game.model.utils.states.FirstPillState;
import com.mygdx.game.model.utils.states.PillState;
import com.mygdx.game.model.utils.Point;

public class Pill implements Updatable, Controllable{


    PillState state;
    Block a,b;

    public Pill(Board board){
        int boardWidth = board.getBoardWidth();
        int boardHeidht = board.getBoardHeight();
        this.a = new Block(new Point(boardWidth/2 - 1 , boardHeidht - 2), false);
        this.b = new Block(new Point(boardWidth/2, boardHeidht - 2),  false);
        this.a.setRelativeBlock(b);
        this.b.setRelativeBlock(a);
        this.state = new FirstPillState(board);
    }


    @Override
    public void update(Board board) {
        Point a = this.getA().coordinates;
        Point b = this.getB().coordinates;
        a.setY(a.getY() - 1);
        b.setY(b.getY() - 1);
        if(board.isCellEmpty(a) && board.isCellEmpty(b)){
                this.getA().setCoordinates(a);
                this.getB().setCoordinates(b);
        }
        else {
            board.update();
        }
        }


    public Block getA() {
        return a;
    }

    public void setA(Block a) {
        this.a = a;
    }

    public Block getB() {
        return b;
    }

    public void setB(Block b) {
        this.b = b;
    }

    public void changeState(PillState state){
        this.state = state;
    }

    public PillState getState() {
        return state;
    }

    public static class DownCommand extends BaseCommand {


        @Override
        public void execute(Board board) {

        }
    }

    public static class Leftcommand extends BaseCommand {

        @Override
        public void execute(Board board) {
            Pill pill = board.getCurrentpPill();
            Point point = pill.getA().getCoordinates();
            point.setX(point.getX() - 1);
            if (board.isCellEmpty(point)){
                pill.getB().setCoordinates(pill.getA().getCoordinates());
                pill.getA().setCoordinates(point);
            }
        }
    }


    public static class RightCommand extends BaseCommand {

        @Override
        public void execute(Board board) {
            Pill pill = board.getCurrentpPill();
            Point point = pill.getB().getCoordinates();
            point.setX(point.getX() + 1);
            if (board.isCellEmpty(point)){
                pill.getA().setCoordinates(pill.getB().getCoordinates());
                pill.getB().setCoordinates(point);
            }
        }
    }

    public static class UpCommand extends BaseCommand {

        @Override
        public void execute(Board board) {
            board.getCurrentpPill().getState().turn();
        }
    }


}
