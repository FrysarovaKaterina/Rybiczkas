package com.example.game;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.util.*;

public class Player extends Alive {
    private int bubbleCounter = 5;
    private int shieldCounter = 1000;
    boolean shieldActive = false;
    ArrayList<Item> inventory = new ArrayList<Item>();
    private GameEngine engine;
    boolean bubbleAvailable = true;
    private int speed = 7;
    private int bubbleFrameCounter = 0;
    Image pl1, pl2, pl3, pl4, shield1, shield2, shield3, shield4;

    public Player(int positionX, int positionY, GameEngine engine) {
        super(2, positionX, positionY, 100, 1, 5, engine.getEngineConfig());
        this.engine = engine;
    }

    /**
     * updates textures
     * provides bubble throwing (so multiple bubbles don't spawn too early after  each other
     * decreases lives after reaching 0 energy
     * runs animation if player is moving left/right
     */
    @Override
    public void update() {
        if (shieldActive) {
            shieldCounter = shieldCounter - 2;
        }
        if (shieldCounter <= 0) {
            shieldActive = false;
            textures.clear();
            textures.add(pl1);
            textures.add(pl2);
            textures.add(pl3);
            textures.add(pl4);
            shieldCounter = 1000;
        }
        bubbleFrameCounter++;
        if (bubbleFrameCounter == 20) {
            bubbleAvailable = true;
            bubbleFrameCounter = 0;
        }
        if (energy <= 0) {
            lives--;
            energy = 4;
            if (lives <= 0)
                engine.endGame(true);
        }
        animationDisabled = true;
        if (engine.isKeyPressed(KeyCode.RIGHT)) {
            positionX += speed;
            facing = Side.RIGHT;
            animationDisabled = false;
        }
        if (engine.isKeyPressed(KeyCode.E) && hasItem("shield")) {
            removeItems("shield");
            textures.add(shield1);
            textures.add(shield2);
            textures.add(shield3);
            textures.add(shield4);

            textures.remove(pl1);
            textures.remove(pl2);
            textures.remove(pl3);
            textures.remove(pl4);
            shieldActive = true;
        }
        if (engine.isKeyPressed(KeyCode.LEFT)) {
            positionX -= speed;
            facing = Side.LEFT;
            animationDisabled = false;
        }
        if (engine.isKeyPressed(KeyCode.UP)) {
            positionY -= speed;
            animationDisabled = false;
        }
        if (engine.isKeyPressed(KeyCode.DOWN)) {
            positionY += speed;
        }
        if (engine.isKeyPressed(KeyCode.SPACE) && bubbleCounter != 0 && bubbleAvailable) {
            engine.spawnSprite(new Bubble(positionX, positionY, facing, engineConfig));
            bubbleAvailable = false;
            bubbleCounter--;
        }
        if (positionX < 0)
            positionX = 0;
        else if (positionX > engineConfig.Width)
            positionX = engineConfig.Width;
        if (positionY > engineConfig.Height)
            positionY = engineConfig.Height;
        else if (positionY < 0)
            positionY = 0;
        if (positionY <= 30 && engine.isKeyPressed(KeyCode.Q)) {
            bubbleCounter = 5;
        }
    }

    public boolean hasItem(String name) {
        for (Item i : inventory)
            if (i.name == name)
                return true;
        return false;
    }

    public void removeItems(String name) {
        var toDelete = new HashSet<Item>();
        for (Item i : inventory)
            if (i.name == name)
                toDelete.add(i);
        for (Item i : toDelete)
            inventory.remove(i);
    }

    @Override
    public void loadTextures() {
        pl1 = new Image("playa1.png");
        pl2 = new Image("playa2.png");
        pl3 = new Image("playa3.png");
        pl4 = new Image("playa4.png");

        shield1 = new Image("shield1.png");
        shield2 = new Image("shield2.png");
        shield3 = new Image("shield3.png");
        shield4 = new Image("shield4.png");
        textures.add(pl1);
        textures.add(pl2);
        textures.add(pl3);
        textures.add(pl4);
    }

}
