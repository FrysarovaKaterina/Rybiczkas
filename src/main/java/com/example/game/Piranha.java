package com.example.game;

import javafx.scene.image.Image;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Piranha extends Alive {
    public Piranha(int positionX, int positionY) {
        super(new ArrayList<Image>(), 2, positionX, positionY, 5, 1, 1);
        textures.add(new Image("piranha1.png"));
        textures.add(new Image("piranha2.png"));
    }

    private int damage = 1;
    private int speed = 3;



    // override collide, hurt if collided w player

    @Override
    public void collide(ColliderObject other) {
        //if (other instanceof Player) {
            //todo
        //}
    }

    @Override
    public void update() {
        positionX += facing == Side.RIGHT ? speed : -speed;
        if (positionX >= 1920)
            facing = Side.LEFT;
        else if (positionX <= 0)
            facing = Side.RIGHT;
    }
}
