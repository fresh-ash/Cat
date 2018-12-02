package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Listener.GameEventListener;
import com.mygdx.game.interfaces.Rendered;
import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Pill;
import com.mygdx.game.model.utils.BlockSpriteManager;
import java.util.ArrayList;

public class GameScreen implements Screen {

    GameWorld gameWorld;
    OrthographicCamera camera;
    BlockSpriteManager sprite;
    TextureAtlas atlas;
    Pill.DownCommand downCommand;
    Pill.UpCommand upCommand;
    Pill.Leftcommand leftCommand;
    Pill.RightCommand rightCommand;
    Board board;
    long lastRenderTime = TimeUtils.nanoTime();


    public GameScreen(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        this.camera = new OrthographicCamera(12, 22);
        camera.translate(camera.viewportWidth / 2 - 1, camera.viewportHeight / 2 - 1, 0);
        this.atlas = new TextureAtlas("sprites.txt");

        sprite = new BlockSpriteManager(atlas);
        board = new Board(11,20);
        board.fillBoard(4);
        this.downCommand = new Pill.DownCommand();
        this.upCommand = new Pill.UpCommand();
        this.leftCommand = new Pill.Leftcommand();
        this.rightCommand = new Pill.RightCommand();

        Gdx.input.setInputProcessor(new GameEventListener(new GameEventListener.GameListener() {
            @Override
            public void onDown() {
                downCommand.execute(board.getCurrentpPill());
            }

            @Override
            public void onUp() {
                upCommand.execute(board.getCurrentpPill());
            }

            @Override
            public void onLeft() {
                leftCommand.execute(board.getCurrentpPill());
            }

            @Override
            public void onRight() {
                rightCommand.execute(board.getCurrentpPill());
            }
        }));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        gameWorld.batch.setProjectionMatrix(camera.combined);
        long currentTime = TimeUtils.nanoTime();

        ArrayList<Rendered> objects = board.objectManager.getRenderedObjects();
        if ((currentTime - lastRenderTime) > 1000000000) {
            lastRenderTime = TimeUtils.nanoTime();
            board.update();
            }

        gameWorld.batch.begin();
        for (Rendered object : objects) {
            sprite.update((BaseObject) object).draw(gameWorld.batch);
        }
        gameWorld.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
