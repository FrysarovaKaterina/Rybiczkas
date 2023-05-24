package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Energy extends Sprite {
    private Alive alive;

    public Energy(Alive alive, int posx, int posy, EngineConfig engineConfig) {
        super(1, posx, posy, engineConfig);
        textures.add(new Image("energy1.png"));
        this.alive = alive;
    }

    /**
     * Simple method for displaying player's energy. There is an isolated icon for each point of energy, the icons render next to each other.
     */
    @Override
    public void render(GraphicsContext gc) {
        int count = alive.energy;
        for (int i = 0; i < count; i++) {
            gc.drawImage(textures.get(0), positionX - i * 65, positionY);
        }
    }
}

