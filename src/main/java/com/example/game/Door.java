package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Door extends ColliderObject{
    GameEngine engine;
    Image doorClosed = new Image("door1.png");

    boolean opened = false;
    public Door( int positionX, int positionY, GameEngine engine) {
        super(new ArrayList<Image>(), 1, positionX, positionY,  25, engine.getEngineConfig());
        this.engine = engine;
        textures.add(doorClosed);
    }


    @Override
    public void collisionEnter (ColliderObject other){
        if(other instanceof Player && engine.countEnemies()==0){
            opened=true;
            System.out.println(((Player) other).enemiz.size());
            engine.nextLevel();
        }
    }
}
