package com.example.game;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.*;

public class GameEngine implements Runnable {
    private boolean stopGame = false;
    public void stopMainloop() { stopGame = true; }
    private GraphicsContext gc;
    private Set<KeyCode> keysPressed = new HashSet<>();
    private Set<Sprite> activeSprites = new HashSet<>();
    private Set<Sprite> toSpawn = new HashSet<Sprite>();
    private Set<Sprite> toDelete = new HashSet<Sprite>();

    public GameEngine(GraphicsContext gc) {
        this.gc = gc;
    }

    void spawnSprite(Sprite s) {
        toSpawn.add(s);
    }
    void deleteSprite(Sprite s) {
        toDelete.add(s);
    }
    @Override
    public void run () {
        while (!stopGame) {
            gc.clearRect(0,0,gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

            // energy, lives .setValue todo

            handleCollisions();

            // call move todo
            // call render
            for (Sprite s:activeSprites){
                if (s instanceof Alive && ((Alive) s).energy<=0 && ((Alive) s).lives <= 0) {
                    deleteSprite(s);
                    continue;
                }

                s.render(gc);
            }
            for (Sprite s : toSpawn) {
                activeSprites.add(s);
            }
            toSpawn.clear();
            for (Sprite s : toDelete) {
                activeSprites.remove(s);
            }
            toDelete.clear();

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
        stopMainloop();
        Platform.exit();
    }
    public boolean tryLoadLevel(String levelname) {
        // todo later

        spawnSprite(new Piranha(50,500));
        Player plr = new Player(450,500,this);
        spawnSprite(new Shark(1200, 850, plr));
        spawnSprite(new Squid(500, 500));
        spawnSprite(new JellyFish(1000, 800, plr));
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

    private double square(double x) { return x*x; }
    private void handleCollisions() {
        //HashMap<Sprite, java.lang.Boolean> collisionsMap = new HashMap<Sprite, Boolean>();
        // todo optimize
        for (Sprite s: activeSprites) {
            if(s instanceof ColliderObject) {
                for (Sprite s2 : activeSprites) {
                    if (s == s2) continue;
                    if (s2 instanceof ColliderObject) {
                        double distanceSquared = square(s2.positionX-s.positionX)+square(s2.positionY-s.positionY);
                        if (distanceSquared <= square(((ColliderObject) s).radius + ((ColliderObject) s2).radius)) {
                            if (!((ColliderObject) s).collisions.contains(s2)) {
                                ((ColliderObject) s).collisions.add((ColliderObject) s2);
                                ((ColliderObject) s).collisionEnter((ColliderObject) s2);
                            }
                        } else if (((ColliderObject) s).collisions.contains(s2)) {
                            ((ColliderObject) s).collisions.remove((ColliderObject) s2);
                            ((ColliderObject) s).collisionLeave((ColliderObject) s2);
                        }
                    }
                }
            }
        }
    }
}