package com.example.game;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bubble extends Alive {
    boolean right = false;
    public Bubble(int positionX, int positionY, Side facing, EngineConfig engineConfig) {
        super(new ArrayList<Image>(), 1, positionX, positionY, 30, 0, 1, engineConfig);
        textures.add(new Image("bubble.png"));
        if (facing==Side.RIGHT){
            right=true;
        }
    }

    private int damage = 1;
    private int speed = 19;

    @Override
    public void collisionEnter(ColliderObject other) {
        if (other instanceof Alive && !(other instanceof Player) && !(other instanceof Bubble)) {
            ((Alive) other).energy--;
            this.energy--;
        }
    }

    @Override
    public void update() {
        if (positionX < -100 || positionX > 2000 || positionY < -100 || positionY > 1500)
            energy = 0;
        if(right){
            positionX += speed;
        } else{
            positionX -= speed;
        }
    }
}
