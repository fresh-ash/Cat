package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Listener.GameEventListener;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Color;
import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Block;
import com.mygdx.game.model.entity.Pill;
import com.mygdx.game.model.utils.Point;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture blueBlock;
	Texture redBlock;
	Texture yellowBlock;
	Board board;
	OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		blueBlock = new Texture("blueBlock.png");
		redBlock = new Texture("redBlock.png");
		yellowBlock = new Texture("yellowBlock.png");
		board = new Board(10,14);
		float width = 12;
		float height = 20;
		camera = new OrthographicCamera(width,height);
		camera.translate(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

		board.addFreeElement(new Block(new Point(8,10), true));
		board.addFreeElement(new Block(new Point(7,7), true));
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
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Sprite sprite = new Sprite();
		if(!board.getFreeElements().isEmpty()){
			for(BaseObject object : board.getFreeElements()){
				if (object instanceof Block){
					object.update(board);
				}
			}
		}
		batch.begin();
		for (BaseObject object : board.getFreeElements()){
            sprite.setBounds(object.getCoordinates().getX(), object.getCoordinates().getY(), 1, 1);
		    if(object instanceof Block){
				if (object.getColor() == Color.BLUE){
				    sprite.setTexture(blueBlock);

				}
				else if (object.getColor() == Color.RED){
                    sprite.setTexture(redBlock);

				}
				else {
					sprite.setTexture(yellowBlock);
				}
                sprite.draw(batch);
			}
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
