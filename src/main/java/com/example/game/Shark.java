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
        super(new ArrayList<Image>(), 2, positionX, positionY, 100, 0, 3,engineConfig);
        textures.add(new Image("shark1.png"));
        textures.add(new Image("shark2.png"));
        this.target=trg;
    }

    private int damage = 3;
    private int speed = 2;
    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Player)
            damager.max();
    }

    @Override
    public void update() {
        damager.update(collisions, 3);

        //movement
        if(target.positionX> positionX){
            positionX++;
            facing=Side.RIGHT;
        }
        else if(target.positionX< positionX){
            positionX--;
            facing=Side.LEFT;
        }
        if (target.positionY > positionY){
            positionY++;
        } else if (target.positionY<positionY) {
            positionY--;
        }
    }
}

