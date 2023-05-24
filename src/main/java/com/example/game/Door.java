package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Door extends ColliderObject {
    GameEngine engine;
    Image doorClosed;

    boolean opened = false;

    public Door(int positionX, int positionY, GameEngine engine) {
        super(1, positionX, positionY, 25, engine.getEngineConfig());
        this.engine = engine;
    }

    /**
     * The door opens only if all the enemies from current level are killed.
     * When the door is opened, player can collide it and next level is loaded.
     */
    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player && engine.countEnemies() == 0) {
            logger.info("door opened.");
            opened = true;
            engine.nextLevel();
        }
    }

    @Override
    public void loadTextures() {
        doorClosed = new Image("door1.png");
        textures.add(doorClosed);
    }

    public boolean getOpened() {
        return opened;
    }
}
