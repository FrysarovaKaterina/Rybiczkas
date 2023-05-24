package com.example.game;

import javafx.scene.image.Image;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Piranha extends Alive {
    /**
     * Piranha is the most basic enemy - just moves right/left on the screen and hurts the player if collision occurs.
     */
    private final PlayerDamager damager = new PlayerDamager(60);

    public Piranha(int positionX, int positionY, EngineConfig engineConfig) {
        super(2, positionX, positionY, 100, 0, 1, engineConfig);
    }

    @Override
    public void loadTextures() {
        textures.add(new Image("piranha1.png"));
        textures.add(new Image("piranha2.png"));
    }

    private int damage = 2;
    private int speed = 15;

    /**
     * Decreasing player's energy if colliding.
     */
    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player)
            damager.max();
    }

    /**
     * Moving left/right on the screen and setting facing accordingly.
     */
    @Override
    public void update() {
        damager.update(collisions, damage);
        positionX += facing == Side.RIGHT ? speed : -speed;
        if (positionX >= 1920)
            facing = Side.LEFT;
        else if (positionX <= 0)
            facing = Side.RIGHT;
    }
}
