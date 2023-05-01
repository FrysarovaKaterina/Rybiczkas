package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Lives extends Sprite {
    private Alive alive;
    public Lives(Alive alive, int posx, int posy) {
        super(new ArrayList<Image>(), 1, posx, posy);
        textures.add(new Image("heart.png"));
        this.alive = alive;
    }

    @Override
    public void render(GraphicsContext gc) {
        int count = alive.lives;
        for (int i=0; i<count; i++){
            gc.drawImage(textures.get(0), positionX-i*65, positionY);
        }
    }
}
