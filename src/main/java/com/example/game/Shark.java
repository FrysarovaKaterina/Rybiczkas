package com.example.game;

import javafx.geometry.HPos;
import javafx.scene.image.Image;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Shark extends Alive {
    private Sprite target;
    private PlayerDamager damager = new PlayerDamager(60);

    public Shark(int positionX, int positionY, Sprite trg, EngineConfig engineConfig) {
        super(2, positionX, positionY, 400, 0, 3, engineConfig);
        textures.add(new Image("shark3.png"));
        textures.add(new Image("shark4.png"));
        this.target = trg;
    }

    private int damage = 3;
    private int speed = 2;

    /**
     * hurts player if colliding
     *
     * @param other Applies only for collision with player
     */
    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player)
            damager.max();
    }

    /**
     * updates collisions
     * moves accordingly to player, always follows player
     */
    @Override
    public void update() {
        damager.update(collisions, 3);

        //movement
        if (target.positionX > positionX) {
            positionX++;
            facing = Side.RIGHT;
        } else if (target.positionX < positionX) {
            positionX--;
            facing = Side.LEFT;
        }
        if (target.positionY > positionY) {
            positionY++;
        } else if (target.positionY < positionY) {
            positionY--;
        }
    }
}

