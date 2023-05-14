package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Collectable extends ColliderObject {
    boolean equipped = false;
    public Collectable(List<Image> textures,int animationFPS, int positionX, int positionY, int radius, EngineConfig engineConfig) {
        super(new ArrayList<Image>(), animationFPS = 1, positionX, positionY, radius = 25, engineConfig);
        textures.add(new Image("key.png"));
    }

    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player) {
            //if (this.name.equals("Key")) {
                Key key = new Key();
                equipped = true;
                ((Player) other).inventory.add(key);
                ((Player) other).provisoryInventory.add("K");


            // }
        }
    }
}
