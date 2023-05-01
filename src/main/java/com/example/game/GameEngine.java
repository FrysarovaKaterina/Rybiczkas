package com.example.game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.*;

import static java.lang.Math.sqrt;

public class GameEngine implements Runnable {
    private GraphicsContext gc;
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
    public void endGame(boolean failed) {
        //todo
        System.out.println(failed ? "You hilariously failed you dumbo" : "huh u win ok");
        Platform.exit();
    }
    public boolean tryLoadLevel(String levelname) {
        // todo later

        spawnSprite(new Piranha(50,500));
        Player plr = new Player(50,500,this);
        spawnSprite(plr);

        spawnSprite(new Lives(plr, 1750, 10));
        spawnSprite(new Energy(plr, 1750, 75));
        return true;
    }

    //KeyListener methods
    public void addKeyToSet (KeyCode key){
        keysPressed.add (key);
    }
    public void removeKeyFromSet (KeyCode keyrem){
        keysPressed.remove(keyrem);
    }
    public boolean isKeyPressed(KeyCode kc) {
        return keysPressed.contains(kc);
    }

    private void handleCollisions() {
        //HashMap<Sprite, java.lang.Boolean> collisionsMap = new HashMap<Sprite, Boolean>();
        // todo optimize
        for (Sprite s: activeSprites) {
            if(s instanceof ColliderObject) {
                for (Sprite s2 : activeSprites) {
                    if (s == s2) continue;
                    if (s2 instanceof ColliderObject) {
                        double distanceSquared = (s2.positionX-s.positionX)*(s2.positionX-s.positionX)+(s2.positionY-s.positionY)*(s2.positionY-s.positionY);
                        if (distanceSquared <= (((ColliderObject) s).radius + ((ColliderObject) s2).radius)*(((ColliderObject) s).radius + ((ColliderObject) s2).radius)) {
                            if (!((ColliderObject) s).collisions.contains(s2)) {
                                ((ColliderObject) s).collisions.add((ColliderObject) s2);
                                ((ColliderObject) s).collisionEnter((ColliderObject) s2);
                            }
                        } else {
                            if (((ColliderObject) s).collisions.contains(s2)) {
                                ((ColliderObject) s).collisions.remove((ColliderObject) s2);
                                ((ColliderObject) s).collisionLeave((ColliderObject) s2);
                            }
                        }
                            /*((ColliderObject) s).collisionEnter((ColliderObject) s2);
                            if( ((ColliderObject) s).collisionCounter %60 ==0){
                                ((ColliderObject) s2).collisionLeave((ColliderObject) s);
                                ((ColliderObject) s).collisionCounter=0;

                            }*/
                    }
                }
            }
        }
    }
}
