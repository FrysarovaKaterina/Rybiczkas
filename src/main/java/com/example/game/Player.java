package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class Player extends Alive {
    private GameEngine engine;
    private int speed = 7;
    public Player(int positionX, int positionY, GameEngine engine) {
        super(new ArrayList<Image>(), 2, positionX, positionY, 10, 3, 5);
        textures.add(new Image("piranha2.png"));
        textures.add(new Image("piranha1.png"));
        this.engine = engine;
    }

    @Override
    public void update() {
        if (engine.isKeyPressed(KeyCode.RIGHT)){
            positionX +=speed;
            facing = Side.RIGHT;
        }
        if (engine.isKeyPressed(KeyCode.LEFT)){
            positionX -=speed;
            facing = Side.LEFT;
        }
        if (engine.isKeyPressed(KeyCode.UP)){
            positionY -=speed;
        }
        if (engine.isKeyPressed(KeyCode.DOWN)){
            positionY +=speed;
        }
        if(positionX < 0)
            positionX = 0;
        else if(positionX > 1920)
            positionX = 1920;
        if(positionY > 1080)
            positionY = 1080;
        else if (positionY<0)
            positionY = 0;
    }
}
