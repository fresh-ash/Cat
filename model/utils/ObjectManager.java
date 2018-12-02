package com.mygdx.game.model.utils;

import com.mygdx.game.interfaces.Rendered;
import com.mygdx.game.interfaces.Updatable;
import com.mygdx.game.model.Board;

import java.util.ArrayList;

public class ObjectManager {

    Board board;


    ArrayList<Rendered> renderedObjects;
    ArrayList<Updatable> willBeDeleted;
    ArrayList<Updatable> updatables;
    ArrayList<Updatable> willBeAddedToUpdatable;
    ArrayList<Updatable> freeElements;

    public ObjectManager(Board board) {
        this.board = board;
        this.renderedObjects = new ArrayList<Rendered>();
        this.updatables = new ArrayList<Updatable>();
        this.willBeDeleted = new ArrayList<Updatable>();
        this.willBeAddedToUpdatable = new ArrayList<Updatable>();
        this.freeElements = new ArrayList<Updatable>();
    }


    //Rendered Objects
    public void addToRenderedObj(Rendered obj) {
        if (!isInRenderedObj(obj)) this.renderedObjects.add(obj);
    }
    public void delFromRenderedObj(Rendered obj){
        this.renderedObjects.remove(obj);
    }
    public boolean isInRenderedObj(Rendered obj) {
        if (this.renderedObjects.indexOf(obj) >= 0){
            return true;
        }
        else return false;
    }




    //Updatable Objects

    public void addToUpdatableObj(Updatable obj) {
        this.updatables.add(obj);
    }
    public void addToUpdatableObj(ArrayList<Updatable> list) {
        this.updatables.addAll(list);
    }
    public void delFromUpdatableObj(Updatable obj) {
        this.updatables.remove(obj);
    }
    public void delFromUpdatableObj(ArrayList<Updatable> list) {
        this.updatables.removeAll(list);
    }
    public boolean isInUpdatableObj(Updatable obj){
        if (this.updatables.indexOf(obj) >= 0){
            return true;
        }
        else return false;
    }



    //Will be deleted Objects

    public void addToWillBeDeletedObj(Updatable obj){
        this.willBeDeleted.add(obj);
    }
    public void addToWillBeDeletedObj(ArrayList<Updatable> list){
        this.willBeDeleted.addAll(list);
    }
    public void delFromWillBeDeletedObj(Updatable obj){
        this.willBeDeleted.remove(obj);
    }
    public void delFromWillBeDeletedObj(ArrayList<Updatable> list){
        this.willBeDeleted.removeAll(list);
    }





    //Will be added to Updatable

    public void addToWillBeAddedToUpdatableObj(Updatable obj){
        this.willBeAddedToUpdatable.add(obj);
    }
    public void addToWillBeAddedToUpdatableObj(ArrayList<Updatable> list){
        this.willBeAddedToUpdatable.addAll(list);
    }
    public void delFromWillBeAddedToUpdatableObj(Updatable obj){
        this.willBeAddedToUpdatable.remove(obj);
    }
    public void delFromWillBeAddedToUpdatableObj(ArrayList<Updatable> list){
        this.willBeAddedToUpdatable.removeAll(list);
    }


    //Free Elements

    public void addToFreeElements(Updatable obj){
        if (!this.isInUpdatableObj(obj)){
            this.addToWillBeAddedToUpdatableObj(obj);
        }
        this.addToRenderedObj((Rendered) obj);
        this.freeElements.add(obj);
    }
    public void addToFreeElements(ArrayList<Updatable> list){
        this.freeElements.addAll(list);
    }
    public void delFromFreeElements(Updatable obj){
        this.freeElements.remove(obj);
    }
    public void delFromFreeElements(ArrayList<Updatable> list){
        this.freeElements.removeAll(list);
    }
    public boolean isInFreeElements(Updatable obj){
        if (this.freeElements.indexOf(obj) >= 0){
            return true;
        }
        else return false;
    }





    //Getters And Setters
    public ArrayList<Rendered> getRenderedObjects() {
        return renderedObjects;
    }

    public void setRenderedObjects(ArrayList<Rendered> renderedObjects) {
        this.renderedObjects = renderedObjects;
    }

    public ArrayList<Updatable> getWillBeDeleted() {
        return willBeDeleted;
    }

    public void setWillBeDeleted(ArrayList<Updatable> willBeDeleted) {
        this.willBeDeleted = willBeDeleted;
    }

    public ArrayList<Updatable> getUpdatables() {
        return updatables;
    }

    public void setUpdatables(ArrayList<Updatable> updatables) {
        this.updatables = updatables;
    }

    public ArrayList<Updatable> getWillBeAddedToUpdatable() {
        return willBeAddedToUpdatable;
    }

    public void setWillBeAddedToUpdatable(ArrayList<Updatable> willBeAddedToUpdatable) {
        this.willBeAddedToUpdatable = willBeAddedToUpdatable;
    }

    public ArrayList<Updatable> getFreeElements() {
        return freeElements;
    }

    public void setFreeElements(ArrayList<Updatable> freeElements) {
        this.freeElements = freeElements;
    }
}
