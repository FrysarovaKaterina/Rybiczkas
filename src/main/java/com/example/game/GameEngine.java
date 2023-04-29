package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.awt.event.KeyListener;
import java.util.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameEngine implements Runnable {
    MyObject player = new MyObject();
    /* PROVISORY ADDING OF PLAYER OBJECT*******************************/

    GraphicsContext gc;
    private Set<KeyCode> keysPressed = new HashSet<>();
    private Set<Sprite> activeSprites = new HashSet<>();
    int renderingCounter=0;

    public GameEngine(GraphicsContext gc) {
        this.gc = gc;
    }

    void SpawnSprite(Sprite s) {
        activeSprites.add(s);
    }
    void DeleteSprite(Sprite s) {
        activeSprites.remove(s);
        }
    @Override
    public void run () {
        while (true) {
            renderingCounter++;
            gc.clearRect(0,0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());



            // energy, lives .setValue todo
            // detect collisions
            HashMap<Sprite, java.lang.Boolean> collisionsMap = new HashMap<Sprite, Boolean>();
            for (Sprite s: activeSprites){
                if(s instanceof MyObject){
                    boolean collided =((MyObject)s).Collide(player, (MyObject) s);
                    collisionsMap.put(s,collided);
                }
            }

            // call move todo
            // call render
            for(Sprite s:activeSprites){
                s.render(gc,renderingCounter);
            }

            //**************************
            System.out.println(keysPressed);
            //***************************

            try {
                Thread.sleep(1000/60);
                //System.out.println(keysPressed);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //KeyListener methods
    public void addKeyToSet (KeyCode key){
        keysPressed.add (key);
    }
    public void removeKeyFromSet (KeyCode keyrem){
        keysPressed.remove(keyrem);
    }


}
