package com.mygdx.game.model.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.model.Color;
import com.mygdx.game.model.entity.BaseObject;
import com.mygdx.game.model.entity.Block;
import com.mygdx.game.model.entity.Virus;

import java.util.HashMap;

public class BlockSpriteManager extends Sprite {

    HashMap<String, Sprite> textureHashMap = new HashMap<String, Sprite>();
    TextureAtlas atlas;

    public BlockSpriteManager(TextureAtlas atlas) {
        this.atlas = atlas;
        this.createHashMap();
    }

    public Sprite update(BaseObject object) {
        int x = object.getCoordinates().getX();
        int y = object.getCoordinates().getY();
        if (object instanceof Block){
            Sprite sprite = textureHashMap.get(object.getFullImageName());
            sprite.setBounds(x,y,1,1);
            return sprite;
        }
        else if (object instanceof Virus){
            Sprite sprite = textureHashMap.get(object.getFullImageName());
            sprite.setBounds(x,y,1,1);
            return sprite;
        }
        else return null;
    }

    public void createHashMap(){
        Array<Sprite> sprites = atlas.createSprites();
        String[] keys = new String[]{"blueBlock", "blueDeleted", "blueVirus", "blueVirusAngree"
                                        , "redBlock","redDeleted", "redVirus", "redVirusAngree"
                                        , "yellowBlock","yellowDeleted", "yellowVirus", "yellowVirusAngree"};

        for (int i = 0; i < keys.length; i++){
            textureHashMap.put(keys[i], sprites.get(i));
        }

    }


}
