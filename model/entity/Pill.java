package com.mygdx.game.model.entity;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.interfaces.Controllable;
import com.mygdx.game.interfaces.Updatable;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.command.BaseCommand;
import com.mygdx.game.model.utils.pill_states.FirstPillState;
import com.mygdx.game.model.utils.pill_states.PillState;
import com.mygdx.game.model.utils.Point;
import com.mygdx.game.model.utils.pill_states.UnshowedPillState;

public class Pill implements Controllable, Updatable {

    Board board;
    PillState state;
    boolean showed;
    Block a,b;

    public Pill(Board board){
        this.board = board;
        int boardWidth = this.board.getBoardWidth();
        int boardHeidht = this.board.getBoardHeight();
        this.a = new Block(new Point(boardWidth/2 - 1 , boardHeidht - 1), false, board);
        this.b = new Block(new Point(boardWidth/2, boardHeidht - 1),  false, board);
        this.a.setRelativeBlock(b);
        this.b.setRelativeBlock(a);
        this.showed = false;
        this.state = new FirstPillState(this);
    }



    public void update() {

        if (this.getBoard().getObjectManager().getFreeElements().isEmpty() && showed == false){
            Gdx.app.log("I'm here", "AUUU");
            this.showed = true;
            this.getBoard().getObjectManager().addToRenderedObj(this.getA());
            this.getBoard().getObjectManager().addToRenderedObj(this.getB());

        }
        if (showed){
            Point a = new Point(this.getA().getCoordinates());
            Point b = new Point(this.getB().getCoordinates());
            a.setY(a.getY() - 1);
            b.setY(b.getY() - 1);
            if(board.isCellEmpty(a) && board.isCellEmpty(b)){
                this.getA().setCoordinates(a);
                this.getB().setCoordinates(b);
            }
            else {
                board.setBoardElement(this.getA());
                board.setBoardElement(this.getB());
                board.getObjectManager().addToWillBeDeletedObj(this);
                board.setCurrentpPill(board.getNextPill());
                board.setNextPill(board.createPill());
            }
        }
    }

    public void changeState(PillState state){
        this.state = state;
    }




    //Commands
    public static class DownCommand extends BaseCommand {

        @Override
        public void execute(Controllable controllable) {
            if (controllable instanceof Pill){
                Pill pill = (Pill) controllable;
                Point pointA = new Point(pill.getA().getCoordinates());
                Point pointB = new Point(pill.getB().getCoordinates());
                pointA.setY(pointA.getY() - 1);
                pointB.setY(pointB.getY() - 1);
                if (pill.getBoard().isCellEmpty(pointA) && pill.getBoard().isCellEmpty(pointB)){
                    pill.getA().setCoordinates(pointA);
                    pill.getB().setCoordinates(pointB);
                }
            }

        }
    }

    public static class Leftcommand extends BaseCommand {

        @Override
        public void execute(Controllable controllable) {
            if (controllable instanceof Pill){
                Pill pill = (Pill) controllable;
                Point pointA = new Point(pill.getA().getCoordinates());
                Point pointB = new Point(pill.getB().getCoordinates());
                pointA.setX(pointA.getX() - 1);
                pointB.setX(pointB.getX() - 1);
                if (pill.getBoard().isCellEmpty(pointA) && pill.getBoard().isCellEmpty(pointB)){
                    pill.getA().setCoordinates(pointA);
                    pill.getB().setCoordinates(pointB);
                }
            }
        }
    }


    public static class RightCommand extends BaseCommand {

        @Override
        public void execute(Controllable controllable) {
            if (controllable instanceof Pill){
                Pill pill = (Pill) controllable;
                Point pointA = new Point(pill.getA().getCoordinates());
                Point pointB = new Point(pill.getB().getCoordinates());
                pointA.setX(pointA.getX() + 1);
                pointB.setX(pointB.getX() + 1);
                if (pill.getBoard().isCellEmpty(pointA) && pill.getBoard().isCellEmpty(pointB)){
                    pill.getA().setCoordinates(pointA);
                    pill.getB().setCoordinates(pointB);
                }
            }
        }
    }

    public static class UpCommand extends BaseCommand {

        @Override
        public void execute(Controllable controllable) {

            if (controllable instanceof Pill){
                ((Pill) controllable).getState().turn();
            }

        }
    }


    //Getters and Setters
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



    public PillState getState() {
        return state;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setState(PillState state) {
        this.state = state;
    }




}
