package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Energy extends Sprite{
    private Alive alive;
    public Energy(Alive alive, int posx, int posy) {
        super(new ArrayList<Image>(), 1, posx, posy);
        textures.add(new Image("energy.png"));
        this.alive = alive;
    }

    @Override
    public void render(GraphicsContext gc) {
        int count = alive.energy;
        for (int i=0; i<count; i++){
            gc.drawImage(textures.get(0), positionX-i*65, positionY);
        }
    }
}

