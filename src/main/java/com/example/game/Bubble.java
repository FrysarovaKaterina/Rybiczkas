package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bubble extends Alive {
    /**
     * Bubble that is thrown by player to kill the enemies (works like a bullet)
     * Setting speed and damage (how much it hurts the enemies)
     */
    boolean right = false;

    public Bubble(int positionX, int positionY, Side facing, EngineConfig engineConfig) {
        super(1, positionX, positionY, 30, 0, 1, engineConfig);
        if (facing == Side.RIGHT) {
            right = true;
        }
    }

    @Override
    public void loadTextures() {
        textures.add(new Image("bubble1.png"));
    }

    private int damage = 1;
    private int speed = 19;

    /**
     * When bubble hits enemy, it hurts the enemy and disappears
     */
    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Alive && !(other instanceof Player) && !(other instanceof Bubble)) {
            ((Alive) other).energy--;
            this.energy--;
        }
    }

    /**
     * Just updating the bubble (moving left/right accordingly to which side is it "facing" (in which direction the player has thrown the bubble)
     * If the bubble gets out of the screen borders, it disappears (energy=0 ==> the bubble will be deleted as a dead Sprite))
     */
    @Override
    public void update() {
        if (positionX < -100 || positionX > 2000 || positionY < -100 || positionY > 1500)
            energy = 0;
        if (right) {
            positionX += speed;
        } else {
            positionX -= speed;
        }
    }
}
