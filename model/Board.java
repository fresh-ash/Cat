package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Pill;
import com.mygdx.game.model.entity.Virus;
import com.mygdx.game.model.utils.Point;

import java.util.ArrayList;

public class Board {

    ArrayList<BaseObject> freeElements;
    BaseObject[][] board;
    ArrayList<BaseObject> renderedObjects;
    int boardWidth, boardHeight;

    Pill currentpPill;
    Pill nextPill;

    public Board(int boardWidth, int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.board = new BaseObject[boardWidth][boardHeight];
        this.currentpPill = createPill();
        this.nextPill = createPill();
        this.freeElements = new ArrayList<BaseObject>();
        this.renderedObjects = new ArrayList<BaseObject>();
    }


    public void update(){



        this.setCurrentpPill(this.getNextPill());
        this.setNextPill(this.createPill());
    }

    public void setBoardElement(BaseObject object){
        Point point = object.getCoordinates();
        if (this.isCellEmpty(point)){
            board[point.getX()][point.getY()] = object;
            this.renderedObjects.add(object);
        }
    }

    public boolean isCellEmpty(Point point){
        Gdx.app.debug("What Point was recivede:", point.toString());

        int x = point.getX();
        int y = point.getY();

        if ((x >= 0) && (y >= 0) && (x < this.boardWidth) && (y < boardHeight) && board[x][y] == null){
            return true;
        }
        else return false;
    }



    public boolean isInFreeElementsList(BaseObject object){
        if (freeElements.indexOf(object) >= 0){
            return true;
        }
        else return false;
    }

    public Pill createPill(){
        Pill pill = new Pill(this);
        return pill;
    }

    public void addFreeElement(BaseObject object){
        this.freeElements.add(object);
    }

    public void deleteFreeElement(BaseObject object){
        this.freeElements.remove(object);
    }

    public Pill getCurrentpPill() {
        return currentpPill;
    }

    public void setCurrentpPill(Pill currentpPill) {
        this.currentpPill = currentpPill;
    }

    public Pill getNextPill() {
        return nextPill;
    }

    public void setNextPill(Pill nextPill) {
        this.nextPill = nextPill;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public ArrayList<BaseObject> getRenderedObj(){
        for (int x = 0; x < this.boardWidth; x++) {
            for (int y = 0; y < this.boardHeight; y++) {
                BaseObject object = board[x][y];
                if (object != null) {
                    this.renderedObjects.add(object);

                }
            }
        }
        return this.renderedObjects;
    }

    public ArrayList<BaseObject> getFreeElements() {
        return freeElements;
    }

    public void setFreeElements(ArrayList<BaseObject> freeElements) {
        this.freeElements = freeElements;
    }

    public void fillBoard(int level){
        int viruses = level * 10;
        Point point;
        while (viruses != 0){
            point = Point.getRandomPoint(this.boardWidth - 1, this.boardHeight/2 + level);
            if (this.isCellEmpty(point)){
                this.setBoardElement(new Virus(point));
                viruses -= 1;
            }

        }
    }


    class Checker {

        ArrayList<BaseObject> elements;

        void checkBoard(){

        }

        void checkVerticalLines(){

        }

        void checkHorisontalLines(){

        }



    }
}
