package com.mygdx.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameWorld extends Game {

    SpriteBatch batch;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.setScreen(new GameScreen(this));
    }

    public void render(){
        super.render();
    }

    public void dispose() {
        batch.dispose();

    }
}
