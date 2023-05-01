package com.example.game;

import javafx.scene.image.Image;

import java.util.List;

public class Player extends Alive {
    private GameEngine engine;
    public Player(int positionX, int positionY, GameEngine engine) {
        super(textures, animationFPS, positionX, positionY, radius, lives, energy);
        this.engine = engine;
    }

    @Override
    public void update() {
        // engine.getPressedKeys OR engine.isKeyPressed
    }
}
