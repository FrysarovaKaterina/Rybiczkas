package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Chest extends ColliderObject{
    Image chest1 = new Image("box_full.png");

    public Chest(int positionX, int positionY,  EngineConfig engineConfig) {
        super(new ArrayList<Image>(), 1, positionX, positionY,25, engineConfig);
        textures.add(chest1);
    }
    boolean opened = false;
    @Override
    public void collisionEnter (ColliderObject other){
        if (other instanceof Player && (((Player) other).inventory.contains(Key.class) || ((Player) other).provisoryInventory.contains("K"))){
            opened=true;
            textures.remove(chest1);
            textures.add(new Image("box_empty2.png"));
            Shield shield = new Shield();
            ((Player) other).inventory.add(shield);
            ((Player) other).provisoryInventory.add("S");
        }
    }
}
