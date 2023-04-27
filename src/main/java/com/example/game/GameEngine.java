package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class GameEngine implements Runnable {
    GraphicsContext gc;
    // todo list of pressed keys

    public GameEngine(GraphicsContext gc) {
        this.gc = gc;
    }

    void SpawnSprite(Sprite s) {
        //todo
    }
    void DeleteSprite(Sprite s) {
        //todo
    }
    @Override
    public void run () {
        while (true) {
            gc.clearRect(0,0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

            // energy, lives .setValue todo
            // detect collisions todo
            // call move todo
            // call render todo

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
