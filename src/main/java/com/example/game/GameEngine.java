package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class GameEngine implements Runnable {
    GraphicsContext gc;
    private Set<KeyCode> keysPressed = new HashSet<>();
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
            // Check if the up arrow key is pressed


            // energy, lives .setValue todo
            // detect collisions todo
            // call move todo
            // call render todo


            System.out.println(keysPressed);

            try {
                Thread.sleep(1000/60);
                //System.out.println(keysPressed);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addKeyToSet (KeyCode key){
        keysPressed.add (key);
    }
    public void removeKeyFromSet (KeyCode keyrem){
        keysPressed.remove(keyrem);
    }


}
