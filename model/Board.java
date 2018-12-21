package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.interfaces.Rendered;
import com.mygdx.game.interfaces.Updatable;
import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Block;
import com.mygdx.game.model.entity.Pill;
import com.mygdx.game.model.entity.Virus;
import com.mygdx.game.model.utils.ObjectManager;
import com.mygdx.game.model.utils.Point;

import java.util.ArrayList;
import java.util.HashSet;

public class Board {


    Color[][] board;

    ObjectManager objectManager;
    int boardWidth, boardHeight;
    Checker checker;

    Pill currentpPill;
    Pill nextPill;

    public Board(int boardWidth, int boardHeight){
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.board = new Color[boardWidth][boardHeight];
        this.objectManager = new ObjectManager(this);
        this.checker = new Checker();
        setCurrentpPill(createPill());
        this.nextPill = createPill();
        //this.objectManager.addToFreeElements(new Block(new Point(boardWidth - 4,boardHeight -1), true, this));
    }


    public void update(){
        if (!(currentpPill.isShowed()) && objectManager.getFreeElements().isEmpty()){
            checker.checkBoard();
        }
        this.objectManager.addToUpdatableObj(this.objectManager.getWillBeAddedToUpdatable());
        this.objectManager.delFromUpdatableObj(this.objectManager.getWillBeDeleted());
        this.objectManager.getWillBeAddedToUpdatable().clear();
        this.objectManager.getWillBeDeleted().clear();
        ArrayList<Updatable> updateList = this.objectManager.getUpdatables();
        for (Updatable updatable : updateList) {
            updatable.update();
        }

    }

    public void setBoardElement(BaseObject object){
            board[object.getCoordinates().getX()][object.getCoordinates().getY()] = object.getColor();
            prinBoard();
        }

    public void setBoardElementAsEmpty(BaseObject object) {
        this.board[object.getCoordinates().getX()][object.getCoordinates().getY()] = null;
    }

    public boolean isCellEmpty(Point point){
        int x = point.getX();
        int y = point.getY();

        if (((x >= 0) && (y >= 0) && (x < this.boardWidth) && (y < this.boardHeight)) && board[x][y] == null){

            return true;
        }
        else return false;
    }

    public Pill createPill(){
        Pill pill = new Pill(this);
        return pill;
    }


    public Pill getCurrentpPill() {
        return currentpPill;
    }

    public void setCurrentpPill(Pill currentpPill) {
            this.currentpPill = currentpPill;
            this.objectManager.addToWillBeAddedToUpdatableObj(this.currentpPill);
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public void setObjectManager(ObjectManager objectManager) {
        this.objectManager = objectManager;
    }

    public Checker getChecker() {
        return checker;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
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


    public void fillBoard(int level){
        int viruses = level * 10;
        Point point;
        while (viruses != 0){
            point = Point.getRandomPoint(this.boardWidth - 1, this.boardHeight/2 + level);
            if (this.isCellEmpty(point)){
                this.setBoardElement(new Virus(point, this));
                viruses -= 1;
            }

        }
    }

    void prinBoard(){
        String message = " ";
        for (int x = 0; x < this.boardHeight; x++) {
            message +="\n";
            for (int y = 0; y < this.boardWidth; y++) {
                    if (board[y][x] != null) {
                        message += " [" + board[y][x].toString() + "] ";
                    }
                    else message += " [null] ";
            }
            }

        Gdx.app.log("board", message);
    }


    class Checker {

        HashSet<Point> elements;
        ArrayList<Point> tempElements;
        Color currentColor = null;
        public Checker(){
            this.elements = new HashSet<Point>();
            this.tempElements = new ArrayList<Point>();
        }


       public void checkBoard(){
            checkHorisontalLines();
            checkVerticalLines();
            ArrayList<Updatable> list = objectManager.getUpdatables();
            for (Point point : elements){
                   for (Updatable obj : list){
                       if (!(obj instanceof Pill)){
                           if (point.getX() == ((BaseObject) obj).getCoordinates().getX() &&
                                   point.getY() == ((BaseObject) obj).getCoordinates().getY()){
                               ((BaseObject) obj).setDeleted(true);
                           }
                       }
                   }
                }
            this.elements.clear();
            }


        void checkVerticalLines(){
            for (int x = 0; x < boardWidth; x++){
                for (int y = 0; y < boardHeight; y++){
                    if (board[x][y] != null && board[x][y] == currentColor){
                        this.tempElements.add(new Point(x,y));
                        if (this.tempElements.size() >= 4){
                            this.elements.addAll(this.tempElements);
                            for (Point point : this.tempElements){
                                board[point.getX()][point.getY()] = null;
                                Gdx.app.log("Del:" , tempElements.toString());
                            }
                        }
                    }
                    else {
                        currentColor = board[x][y];
                        tempElements.clear();
                        tempElements.add(new Point(x,y));
                    }
                }
            }
        }

        void checkHorisontalLines(){
            for (int y = 0; y < boardHeight; y++){
                for (int x = 0; x < boardWidth; x++){
                    if (board[x][y] != null && board[x][y] == currentColor){
                        this.tempElements.add(new Point(x,y));
                        if (this.tempElements.size() >= 4){
                            this.elements.addAll(this.tempElements);
                            Gdx.app.log("Del:" , tempElements.toString());
                        }
                    }
                    else {
                        currentColor = board[x][y];
                        tempElements.clear();
                        tempElements.add(new Point(x,y));

                    }
                }
            }
        }



    }
}
