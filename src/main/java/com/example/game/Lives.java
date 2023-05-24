package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Lives extends Sprite {
    private Alive alive;

    public Lives(Alive alive, int posx, int posy, EngineConfig engineConfig) {
        super(1, posx, posy, engineConfig);
        textures.add(new Image("heart.png"));
        this.alive = alive;
    }

    /**
     * Simple method for displaying player's lives. There is an isolated icon for each point of life, the icons render next to each other.
     * Lives and energy are cooperating (not really used in default setting but could be used to make game harder/easier later...
     */
    @Override
    public void render(GraphicsContext gc) {
        int count = alive.lives;
        for (int i = 0; i < count; i++) {
            gc.drawImage(textures.get(0), positionX - i * 65, positionY);
        }
    }
}
