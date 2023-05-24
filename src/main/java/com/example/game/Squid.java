package com.example.game;

import javafx.scene.image.Image;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Squid extends Alive {

    Image squid1 = new Image("squid1.png");
    Image squid2 = new Image("squid2.png");
    Image squid3 = new Image("squid3.png");
    Image squid4 = new Image("squid4.png");
    Image squid5 = new Image("squid5.png");
    Image squid6 = new Image("squid6.png");

    int counter = 0;
    private final PlayerDamager damager = new PlayerDamager(60);

    public Squid(int positionX, int positionY, EngineConfig engineConfig) {
        super(2, positionX, positionY, 100, 0, 6, engineConfig);
        facing = Side.RIGHT;
        textures.add(squid1);
        textures.add(squid2);
    }

    private int damage = 1;
    private int speedX = 2;
    private int speedY = 2;

    /**
     * hurts player if colliding
     *
     * @param other If other is player, decreases player's energy
     */
    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player)
            damager.max();
    }

    /**
     * counts frames, after 300, 300 and 400 frames changes texture and diameter
     * (simulation of squid producing harmful cloud of ink)
     * moves diagonally, bounces off the edges
     */
    @Override
    public void update() {
        counter++;
        counter = counter % 1000;
        if (counter == 0) {
            damage = 1;
            radius = 100;
            textures.clear();
            textures.add(squid1);
            textures.add(squid2);
        } else if (counter == 300) {
            damage = 2;
            radius = 300;
            textures.clear();
            textures.add(squid3);
            textures.add(squid4);
        } else if (counter == 600) {
            damage = 3;
            radius = 500;
            textures.clear();
            textures.add(squid5);
            textures.add(squid6);
        }

        damager.update(collisions, damage);
        positionX += speedX;
        positionY += speedY;
        if (positionX >= 1920) {
            speedX = -Math.abs(speedX);
            facing = Side.LEFT;
        } else if (positionX <= 0) {
            facing = Side.RIGHT;
            speedX = Math.abs(speedX);
        }
        if (positionY >= 1080) {
            speedY = -Math.abs(speedY);
        } else if (positionY <= 0) {
            speedY = Math.abs(speedY);
        }
    }
}