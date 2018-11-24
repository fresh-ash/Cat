package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.Listener.GameEventListener;
import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Block;
import com.mygdx.game.model.utils.BlockSpriteManager;
import com.mygdx.game.model.utils.Point;

import java.util.ArrayList;

public class GameScreen implements Screen {

    GameWorld gameWorld;
    OrthographicCamera camera;
    Sprite red, blue, yellow, redVirus, blueVirus, yellowVirus;
    BlockSpriteManager sprite;
    TextureAtlas atlas;
    Board board;

    public GameScreen(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        this.camera = new OrthographicCamera(12, 22);
        camera.translate(camera.viewportWidth / 2 - 1, camera.viewportHeight / 2 - 1, 0);
        this.atlas = new TextureAtlas("sprites.txt");
        blue = atlas.createSprite("blueBlock");
        red = atlas.createSprite("redBlock");
        yellow = atlas.createSprite("yellowBlock");
        blueVirus = atlas.createSprite("blueVirus");
        redVirus = atlas.createSprite("redVirus");
        yellowVirus = atlas.createSprite("yellowVirus");
        sprite = new BlockSpriteManager(red, blue, yellow, redVirus, blueVirus, yellowVirus);
        board = new Board(11,14);
        board.fillBoard(4);
        board.setBoardElement(new Block(Point.getRandomPoint(board.boardWidth - 1, board.boardHeight - 1), true));
        Gdx.input.setInputProcessor(new GameEventListener(new GameEventListener.GameListener() {
            @Override
            public void onDown() {
                Gdx.app.log("Info", "It works!!!");
            }

            @Override
            public void onUp() {

            }

            @Override
            public void onLeft() {

            }

            @Override
            public void onRight() {

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
        ArrayList<BaseObject> objects = board.getRenderedObj();

        gameWorld.batch.begin();
        for (BaseObject object : objects){
            sprite.update(object).draw(gameWorld.batch);
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
