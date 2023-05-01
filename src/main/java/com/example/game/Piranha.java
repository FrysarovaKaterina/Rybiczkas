package com.example.game;

import javafx.scene.image.Image;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Piranha extends Alive {
    public Piranha(int positionX, int positionY) {
        super(new ArrayList<Image>(), 2, positionX, positionY, 100, 1, 1);
        textures.add(new Image("piranha1.png"));
        textures.add(new Image("piranha2.png"));
    }

    private int damage = 1;
    private int speed = 3;
    private int framesSinceLastHurt = 0;

    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player)
            framesSinceLastHurt = 99999;
    }

    @Override
    public void update() {
        framesSinceLastHurt = (framesSinceLastHurt + 1) % Integer.MAX_VALUE;
        for (ColliderObject other:collisions) {
            if (other instanceof Player && framesSinceLastHurt >= 60) {
                ((Player) other).energy--;
                framesSinceLastHurt = 0;
            }
        }
        positionX += facing == Side.RIGHT ? speed : -speed;
        if (positionX >= 1920)
            facing = Side.LEFT;
        else if (positionX <= 0)
            facing = Side.RIGHT;
    }
}
