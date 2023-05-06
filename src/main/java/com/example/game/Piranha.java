package com.example.game;

import javafx.scene.image.Image;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Piranha extends Alive {
    private final PlayerDamager damager = new PlayerDamager(60);
    public Piranha(int positionX, int positionY, EngineConfig engineConfig) {
        super(new ArrayList<Image>(), 2, positionX, positionY, 100, 0, 1, engineConfig);
        textures.add(new Image("piranha1.png"));
        textures.add(new Image("piranha2.png"));
    }

    private int damage = 1;
    private int speed = 15;

    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player)
            damager.max();
    }

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
