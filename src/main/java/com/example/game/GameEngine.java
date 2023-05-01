package com.example.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.*;

import static java.lang.Math.sqrt;

public class GameEngine implements Runnable {
    GraphicsContext gc;
    private Set<KeyCode> keysPressed = new HashSet<>();
    private Set<Sprite> activeSprites = new HashSet<>();

    public GameEngine(GraphicsContext gc) {
        this.gc = gc;
    }

    void spawnSprite(Sprite s) {
        activeSprites.add(s);
    }
    void deleteSprite(Sprite s) {
        activeSprites.remove(s);
    }
    @Override
    public void run () {
        while (true) {
            gc.clearRect(0,0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

            // energy, lives .setValue todo

            handleCollisions();

            // call move todo
            // call render
            for(Sprite s:activeSprites) {
                s.render(gc);
            }

            //**************************
            //System.out.println(keysPressed);
            //***************************

            try {
                Thread.sleep(1000/60);
                //System.out.println(keysPressed);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean tryLoadLevel(String levelname) {
        // todo later
        spawnSprite(new Piranha(50,500));
        return true;
    }

    //KeyListener methods
    public void addKeyToSet (KeyCode key){
        keysPressed.add (key);
    }
    public void removeKeyFromSet (KeyCode keyrem){
        keysPressed.remove(keyrem);
    }

    private void handleCollisions() {
        //HashMap<Sprite, java.lang.Boolean> collisionsMap = new HashMap<Sprite, Boolean>();
        // todo optimize
        for (Sprite s: activeSprites) {
            if(s instanceof ColliderObject) {
                for (Sprite s2 : activeSprites) {
                    if (s2 instanceof ColliderObject) {
                        double distance = sqrt((s2.positionX-s.positionX)^2+(s2.positionY-s.positionY)^2);
                        if (distance <= ((ColliderObject) s).radius + ((ColliderObject) s2).radius) {
                            ((ColliderObject) s).collide((ColliderObject) s2);
                            ((ColliderObject) s2).collide((ColliderObject) s);
                        }
                    }
                }
            }
        }
    }
}
