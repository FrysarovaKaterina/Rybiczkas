package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;

public abstract class Alive extends ColliderObject {
    public int lives;
    public int energy;

    public enum Side {LEFT, RIGHT}

    ;
    public Side facing = Side.RIGHT;

    public Alive(int animationFPS, int positionX, int positionY, int radius, int lives, int energy, EngineConfig engineConfig) {
        super(animationFPS, positionX, positionY, radius, engineConfig);
        this.lives = lives;
        this.energy = energy;
    }

    public abstract void update();

    /**
     * Calling render - updating state, flipping if needed and rendering.
     */
    @Override
    public void render(GraphicsContext gc) {
        update();
        flipped = facing == Side.RIGHT ? false : true;
        super.render(gc);
    }
}
