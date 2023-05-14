package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class KeyObj extends ColliderObject{

    public KeyObj(int positionX, int positionY, EngineConfig engineConfig) {
        super(new ArrayList<Image>(), 1, positionX, positionY,25, engineConfig);
        textures.add(new Image("key.png"));
    }
    boolean equipped = false;
    @Override
    public void collisionEnter (ColliderObject other){
        if (other instanceof Player ){
            equipped=true;
            textures.remove("key.png");
            Key key = new Key();
            ((Player) other).inventory.add(key);
            ((Player) other).provisoryInventory.add("K");

        }
    }
}
