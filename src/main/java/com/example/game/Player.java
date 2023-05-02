package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class Player extends Alive {
    private int bubbleCounter =5;
    private GameEngine engine;
    boolean bubbleAvailable = true;
    private int speed = 7;
    private int counter =0;
    public Player(int positionX, int positionY, GameEngine engine) {
        super(new ArrayList<Image>(), 2, positionX, positionY, 100, 3, 5);
        textures.add(new Image("playa1.png"));
        textures.add(new Image("playa2.png"));
        textures.add(new Image("playa3.png"));
        textures.add(new Image("playa4.png"));

        this.engine = engine;
    }

    @Override
    public void update() {
        counter++;
        if (counter==40){
            bubbleAvailable=true;
            counter=0;
        }
        if (energy <= 0) {
            lives--;
            energy = 5;
            if (lives <= 0)
                engine.endGame(true);
        }
        animationDisabled = true;
        if (engine.isKeyPressed(KeyCode.RIGHT)){
            positionX +=speed;
            facing = Side.RIGHT;
            animationDisabled = false;
        }
        if (engine.isKeyPressed(KeyCode.LEFT)){
            positionX -=speed;
            facing = Side.LEFT;
            animationDisabled = false;
        }
        if (engine.isKeyPressed(KeyCode.UP)){
            positionY -=speed;
            animationDisabled = false;
        }
        if (engine.isKeyPressed(KeyCode.DOWN)){
            positionY +=speed;
        }
        if (engine.isKeyPressed(KeyCode.SPACE) && bubbleCounter!=0 && bubbleAvailable){
            engine.spawnSprite(new Bubble(positionX, positionY, facing));
            bubbleAvailable=false;
            bubbleCounter--;
        }
        if(positionX < 0)
            positionX = 0;
        else if(positionX > 1920)
            positionX = 1920;
        if(positionY > 1080)
            positionY = 1080;
        else if (positionY<0)
            positionY = 0;
        if(positionY<=30){
            bubbleCounter=5;
        }
    }
}
