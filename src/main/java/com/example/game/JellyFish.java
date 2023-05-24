package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class JellyFish extends Alive {

    /*setting textures, speed, damage etc.*/
    int frameCounter = 0;
    boolean electricShockAvailable = true;
    private Sprite target;
    Image jf1, jf2, jf3, jf4;

    public JellyFish(int positionX, int positionY, Sprite trg, EngineConfig engineConfig) {
        super(2, positionX, positionY, 60, 0, 4, engineConfig);
        this.target = trg;
    }

    @Override
    public void loadTextures() {
        jf1 = new Image("jellyfish1.png");
        jf2 = new Image("jellyfish2.png");
        jf3 = new Image("jellyfish3.png");
        jf4 = new Image("jellyfish4.png");
        textures.add(jf1);
        textures.add(jf2);
    }

    private int damage = 3;
    private int speed = 1;

    /**
     * Time bt time jellyfish does not have the electric shock available -> it is safe to pass by (indicated by friendly texture) and it will not hurt the player
     * Most of the time jellyfish has angry texture (electric shock is available
     * If the electric shock is available and player is on the same vertical/horizontal level with the jellyfish, the jellyfish decreases player's energy (it gives electric shock)
     * The jellyfish's movement is just moving up and down on the same X position of the screen
     */
    @Override
    public void update() {
        frameCounter++;
        if (frameCounter == 170) {
            electricShockAvailable = true;
        } else if (frameCounter == 800) {
            electricShockAvailable = false;
            frameCounter = 0;
            textures.remove(jf3);
            textures.remove(jf4);
            textures.add(jf1);
            textures.add(jf2);
        }

        if (electricShockAvailable && !(textures.contains(jf3))) {
            textures.add(jf3);
            textures.add(jf4);
            textures.remove(jf1);
            textures.remove(jf2);
        }

        //damage
        if ((target instanceof Player && ((Player) target).shieldActive != true && target.positionX <= positionX + radius && target.positionX >= positionX - radius) && electricShockAvailable) {
            ((Player) target).energy -= damage;
            electricShockAvailable = false;
            textures.remove(jf3);
            textures.remove(jf4);
            textures.add(jf1);
            textures.add(jf2);


        } else if (target instanceof Player && ((Player) target).shieldActive != true && target.positionY <= positionY + radius && target.positionY >= positionY - radius && electricShockAvailable) {
            ((Player) target).energy -= damage;
            electricShockAvailable = false;
            textures.remove(jf3);
            textures.remove(jf4);
            textures.add(jf1);
            textures.add(jf2);


        }

        //movement
        if (positionY >= 1080)
            speed = -speed;
        else if (positionY <= 0)
            speed = -speed;

        positionY += speed;
    }

    public int getDamage() {
        return damage;
    }
}