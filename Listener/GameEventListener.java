package com.mygdx.game.Listener;


import com.badlogic.gdx.input.GestureDetector;

public class GameEventListener extends GestureDetector {

    public interface GameListener {

        void onDown();

        void onUp();

        void onLeft();

        void onRight();
    }

    public GameEventListener(GameListener listener) {
        super(new DirectionListener(listener));
    }

    private static class DirectionListener extends GestureAdapter{
        GameListener gameListener;

        public DirectionListener(GameListener gameListener){
            this.gameListener = gameListener;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            if(Math.abs(velocityX)>Math.abs(velocityY)){
                if(velocityX>0){
                    gameListener.onRight();
                }else{
                    gameListener.onLeft();
                }
            }else{
                if(velocityY>0){
                    gameListener.onDown();
                }else{
                    gameListener.onUp();
                }
            }
            return super.fling(velocityX, velocityY, button);
        }

        }
    }

